package risk.event;

public class LclStopAttackFromEvent extends AEvent {
	private static final long serialVersionUID = -5473953340741087186L;
	public LclStopAttackFromEvent() {
		super(EventType.LclStopAttackFromEvent);
		System.out.println("EVENT CREATED: " + toString());
	}

}
