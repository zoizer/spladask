package risk.event;

public class LclAttackFromEvent extends AEvent {
	private static final long serialVersionUID = -5473953340741087186L;
	public final int zoneId;
	public LclAttackFromEvent(int zoneid) {
		super(EventType.LclAttackFromEvent);
		zoneId = zoneid;
	}

}
