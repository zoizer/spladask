package risk.event;

/**
 * Sent from any to server in order to attack
 * (Sent by any to server)
 * 
 * @see EventType#RpcAttackZoneEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class RpcAttackZoneEvent extends ANetEvent {
	private static final long serialVersionUID = -6334244161417816924L;
	
	/**
	 * Source of the attack
	 */
	public final int fromid;
	
	/**
	 * Destination of the attack
	 */
	public final int toid;
	
	/**
	 * Player that attacked
	 */
	public final String player;
	
	/**
	 * 
	 * @param from Source of the attack
	 * @param to Destination of the attack
	 * @param player Player that attacked
	 */
	public RpcAttackZoneEvent(int from, int to, String player) {
		super(EventType.RpcAttackZoneEvent);
		fromid = from;
		toid = to;
		this.player = player;
		System.out.println("EVENT CREATED: " + toString());
	}
	
}
