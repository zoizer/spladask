package risk.event;

import risk.general.event.Event;

/**
 * RiskEvent is the base event, which may make Event and IEvent unnecessary.
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	28/02
 */
public class RiskEvent extends Event {
	public final static int EVENT_ID = 1000;
	public final static int EVENT_NEW_GAME_PRE		= 2 + EVENT_ID;
	public final static int EVENT_NEW_GAME_POST		= 3 + EVENT_ID;
	
	public RiskEvent(float timestamp, int eventType) {
		super(timestamp, eventType);
		// TODO Auto-generated constructor stub
	}

}
