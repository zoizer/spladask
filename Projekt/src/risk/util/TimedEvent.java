package risk.util;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;

public class TimedEvent extends AEventSystem {
	private AtomicReference<Integer> waitSec;
	private AtomicReference<IEvent> sendEvent;
	private AtomicReference<EventType> stopper;
	private Object mutex;
	private AtomicBoolean run;
	private TimerRun timer;
	
	public TimedEvent(int waitSec, IEvent sendEvent, EventType stopper) {
		this.waitSec = new AtomicReference<Integer>(waitSec);
		this.sendEvent = new AtomicReference<IEvent>(sendEvent);
		this.stopper = new AtomicReference<EventType>(stopper);
		mutex = new Object();
		run = new AtomicBoolean(true);
		if(this.stopper.get() == EventType.ERROR_DO_NOT_USE) attachListeners();
		timer = new TimerRun();
		(new Thread(timer)).start();
	}
	
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "stopFunc"), stopper.get());
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "stopFunc"), stopper.get());
	}
	
	@SuppressWarnings("unused")
	public void stopFunc(IEvent e) {
		synchronized (mutex) {
			run.set(false);
			mutex.notify();
		}
	}
	
	private class TimerRun implements Runnable {
		
		@Override
		public void run() {
			try {
				long timeoutPre = System.currentTimeMillis() + (waitSec.get() * 1000);
				synchronized (mutex) {
					while (run.get()) {
						long waitMs = timeoutPre - System.currentTimeMillis();
						if (waitMs <= 0) break;
						mutex.wait(waitMs);
					}
					queueEvent(sendEvent.get());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				ErrorHandler.ASSERT(false);
			} finally {
				if(stopper.get() == EventType.ERROR_DO_NOT_USE) detachListeners();
			}
		}
	}
}