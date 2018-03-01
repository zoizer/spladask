package risk.util;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;

/**
 * A Timed event, which will be sent after specified time or when stopped and ran immediately by some other event.
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-03-01
 *
 */
public class TimedEvent extends AEventSystem {
	private AtomicReference<Integer> waitSec;
	private AtomicReference<IEvent> sendEvent;
	private AtomicReference<EventType> stopper;
	private Object mutex;
	private AtomicBoolean run;
	private TimerRun timer;
	
	/**
	 * 
	 * @param waitSec Seconds to wait until event is sent
	 * @param sendEvent Event to send when time runs out or when signaled.
	 * @param stopper Event which signals the event should be sent immediately
	 */
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
	
	/*
	 * (non-Javadoc)
	 * @see risk.event.IEventSystem#attachListeners()
	 */
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "stopFunc"), stopper.get());
	}

	/*
	 * (non-Javadoc)
	 * @see risk.event.IEventSystem#detachListeners()
	 */
	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "stopFunc"), stopper.get());
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Stops the clock and runs the event immediately
	 * 
	 * @param e the event which was listened to
	 */
	public void stopFunc(IEvent e) {
		synchronized (mutex) {
			run.set(false);
			mutex.notify();
		}
	}
	
	/**
	 * The internal timer thread
	 * 
	 * @author 		Filip Törnqvist
	 * @version 	2018-03-01
	 *
	 */
	private class TimerRun implements Runnable {
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
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