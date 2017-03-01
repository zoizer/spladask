package risk.event;

public class RiskZoneEvent extends RiskEvent {
	public final static int ZONE_EVENT_ID = 2000;
	public final static int EVENT_NEW_ZONE = 1 + ZONE_EVENT_ID;
	public final static int EVENT_SELECT_ZONE = 2 + ZONE_EVENT_ID;
	
	public RiskZoneEvent(float timestamp, int eventType, int dst) {
		super(timestamp, eventType);
		this.dst = dst;
	}

	public int GetDst() {
		return dst;
	}
	
	private int dst;
}
