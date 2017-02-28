package risk.event;

import risk.game.Zone;

public class RiskGameEvent extends RiskEvent {
	public final static int EVENT_ID = 5000;
	public final static int EVENT_NEW_GAME = 1 + EVENT_ID;
	public final static int EVENT_LOAD_GAME = 2 + EVENT_ID;
	
	private String msg;
	
	public RiskGameEvent(float timestamp, int eventType, String msg) {
		super(timestamp, eventType);
		this.msg = msg;
	}

	@Override
	public String ToString() {
		return msg;
	}
}
