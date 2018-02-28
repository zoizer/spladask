package risk.event;

/**
 * Sent to stop visuals from LclAttackFromEvent
 * (Sent by any locally)
 * 
 * @see EventType#LclStopAttackFromEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class LclStopAttackFromEvent extends AEvent {
	private static final long serialVersionUID = -5473953340741087186L;
	
	public LclStopAttackFromEvent() {
		super(EventType.LclStopAttackFromEvent);
		System.out.println("EVENT CREATED: " + toString());
	}

}
