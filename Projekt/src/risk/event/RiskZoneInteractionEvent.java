package risk.event;

public class RiskZoneInteractionEvent extends RiskZoneEvent {
	public final static int ZONE_INTERACTION_EVENT_ID = 3000;
	public final static int EVENT_ATTACK_ZONE = 1 + ZONE_INTERACTION_EVENT_ID; // maybe an unnecessary event

	public RiskZoneInteractionEvent(float timestamp, int eventType, int dst, int src) {
		super(timestamp, eventType, dst);
		this.src = src;
	}

	public int GetSrc() {
		return src;
	}
	
	private int src;
}
