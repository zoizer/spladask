package risk.event;

/**
 * TimeEvent is an event containing time for the TimerButton.
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	04/03
 */
public class TimeEvent extends Event {
	public final static int EVENT_ID = 6000;
	public final static int EVENT_UPDATE_TIME		= 2 + EVENT_ID;
	
	private final int time;
	private final Status status;
	
	public enum Status {
		UNTIL_TURN,
		WAIT,
	}

	public TimeEvent(float timestamp, int time, Status status) {
		super(timestamp, EVENT_UPDATE_TIME);
		this.time = time;
		this.status = status;
	}

	public Status GetStatus() {
		return status;
	}
	
	public int GetTime() {
		return time;
	}
}
