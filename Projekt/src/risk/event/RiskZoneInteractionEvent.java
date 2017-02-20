package risk.event;

import risk.game.Zone;

public class RiskZoneInteractionEvent extends RiskZoneEvent {

	public RiskZoneInteractionEvent(float timestamp, int eventType) {
		super(timestamp, eventType);
		// TODO Auto-generated constructor stub
	}

	public Zone GetSrc() {
		return src;
	}
	
	private Zone src;
}
