package risk.game;

import risk.event.RiskGameEvent;
import risk.event.TimeEvent;
import risk.general.event.EventManager;
import risk.general.event.IEvent;

/**
 * Timer is meant to manage a timer that automatically switches turns when the time runs out.
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	03/03
 */
public class Timer implements Runnable {
	private boolean alive = true;
	private boolean active = false;
	private int countDown = 1;
	private int turnTime = 15;
	
	public Timer() {
		new Thread(this).start();
		EventManager.Get().QueueEvent(new TimeEvent(0.0f, 0, TimeEvent.Status.WAIT));
	}
	
	public void StartTimer(IEvent event) {
		// event contains desired wait time.
		countDown = turnTime;
		active = true;
	}
	
	public void terminate() {
		alive = false;
	}
	
	@Override
	public void run() {
		while(alive) {
			try {
				if(active) {
					EventManager.Get().QueueEvent(new TimeEvent(0.0f, countDown, TimeEvent.Status.UNTIL_TURN));
					if(--countDown == 0) EventManager.Get().QueueEvent(new RiskGameEvent(0.0f, RiskGameEvent.EVENT_NEW_TURN, ""));
					Thread.sleep(1000);
				} else Thread.sleep(250);
			} catch(InterruptedException e) {
				e.printStackTrace();
				alive = false;
			}
		}
	}
}
