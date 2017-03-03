package risk.event;

import risk.general.event.Event;

public class TimeEvent extends Event {
	public final static int EVENT_ID = 6000;
	public final static int EVENT_UPDATE_TIME		= 2 + EVENT_ID;
	
	private final int time;
	private final Status status;
	private final String msg;
	
	public enum Status {
		UNTIL_TURN,
		GRACE,
		WAIT,
	}

	public TimeEvent(float timestamp, int time, Status status, String msg) {
		super(timestamp, EVENT_UPDATE_TIME);
		this.time = time;
		this.status = status;
		this.msg = msg;
	}

	public Status GetStatus() {
		return status;
	}
	
	public String GetMsg() {
		return msg;
	}
	
	public int GetTime() {
		return time;
	}
}
