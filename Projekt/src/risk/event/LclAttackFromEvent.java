package risk.event;

/**
 * Sent to create visuals of where you can attack.	
 * (used by any)
 * 
 * @see EventType#LclAttackFromEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class LclAttackFromEvent extends AEvent {
	private static final long serialVersionUID = -5473953340741087186L;
	
	/**
	 * origin zone id of the attempted attack
	 */
	public final int zoneId;
	
	/**
	 * @param zoneid the origin of the attack
	 */
	public LclAttackFromEvent(int zoneid) {
		super(EventType.LclAttackFromEvent);
		zoneId = zoneid;
		System.out.println("EVENT CREATED: " + toString());
	}

}
