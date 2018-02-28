package risk.event;

/**
 * Sent by client to attempt to modify zone
 * (Sent by any to server)
 * 
 * @see EventType#RpcUpdateZoneEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class RpcUpdateZoneEvent extends ANetEvent {
	private static final long serialVersionUID = 2190857037639721866L;
	
	/**
	 * Zone to be updated
	 */
	public final int zoneId;
	
	/**
	 * the new zone owner
	 */
	public final String player;
	
	/**
	 * The unit change in the zone
	 */
	public final int unitChange;
	
	/**
	 * 
	 * @param zoneId The id of the zone to be updated
	 * @param player the player which owns the zone
	 * @param unitChange the unit change in the zone
	 */
	public RpcUpdateZoneEvent(int zoneId, String player, int unitChange) {
		super(EventType.RpcUpdateZoneEvent);
		this.zoneId = zoneId;
		this.player = player;
		this.unitChange = unitChange;
		System.out.println("EVENT CREATED: " + toString());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Player: " + player + " trained 1x unit at " + zoneId + " (id)";
	}
}
