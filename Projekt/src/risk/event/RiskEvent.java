package risk.event;

import risk.general.event.Event;

public class RiskEvent extends Event {
	public final static int RISK_EVENT_ID = 1000;
	public final static int EVENT_REQUEST_NEW_GAME = 1 + RISK_EVENT_ID;
	
	public RiskEvent(float timestamp, int eventType) {
		super(timestamp, eventType);
		// TODO Auto-generated constructor stub
	}

}
