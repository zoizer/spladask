package risk.event;

import risk.general.Zone;
import risk.model.Phase;

/**
 * Sent by server to confirm attack and results.
 * (Sent from server to all)
 * 
 * @see EventType#SvrAttackZoneEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class SvrAttackZoneEvent extends ANetEvent {
	private static final long serialVersionUID = -4641731324689810567L;
	
	/**
	 * Source of attack (new version of zone)
	 */
	public final Zone src;
	
	/**
	 * Destination of attack (new version of zone)
	 */
	public final Zone dst;
	
	/**
	 * Source of attack (id of zone)
	 */
	public final int srcid;
	
	/**
	 * Destination of attack (id of zone)
	 */
	public final int dstid;
	
	/**
	 * Units the attacker lost
	 */
	public final int lostArmySrc;
	
	/**
	 * Units the defender lost
	 */
	public final int lostArmyDst;
	
	/**
	 * If it was an attack (true) or if it was a move (false)
	 */
	public final boolean attack;
	
	/**
	 * If a player won the game, this will be the name of that player, else null.
	 */
	public final String winner;
	
	/**
	 * The current phase of the game.
	 */
	public final Phase phase;
	
	/**
	 * 
	 * @param src Zone source of the attack
	 * @param dst Zone destination of the attack
	 * @param attack If it is an attack then true, else if its a move then false.
	 * @param srcid Zone ID of the source of the attack
	 * @param dstid Zone ID of the destination of the attack
	 * @param lostArmySrc Army lost by the attacker
	 * @param lostArmyDst Army lost by the defender
	 * @param winner If someone won the game, this should contain their name, else null.
	 * @param phase The current phase of the game.
	 */
	public SvrAttackZoneEvent(Zone src, Zone dst, boolean attack, int srcid, int dstid, int lostArmySrc, int lostArmyDst, String winner, Phase phase) {
		super(EventType.SvrAttackZoneEvent);
		this.src = src;
		this.dst = dst;
		this.srcid = srcid;
		this.dstid = dstid;
		this.lostArmySrc = lostArmySrc;
		this.lostArmyDst = lostArmyDst;
		this.attack = attack;
		this.winner = winner;
		this.phase = phase;
		System.out.println("EVENT CREATED: " + toString());
	}

}
