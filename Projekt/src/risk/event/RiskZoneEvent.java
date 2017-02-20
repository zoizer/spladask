package risk.event;

import risk.game.Zone;

public class RiskZoneEvent extends RiskEvent {

	public RiskZoneEvent(float timestamp, int eventType) {
		super(timestamp, eventType);
		// TODO Auto-generated constructor stub
	}

	public Zone GetDst() {
		return dst;
	}
	
	private Zone dst;
}
