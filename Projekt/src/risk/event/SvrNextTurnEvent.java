package risk.event;

import risk.general.Phase;
import risk.net.NetPlayer;

/**
 * Sent by server to say, next players turn. 
 * (Sent from server to all)
 * 
 * @see EventType#SvrNextTurnEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class SvrNextTurnEvent extends ANetEvent {
	private static final long serialVersionUID = 8003248686452798392L;
	
	/**
	 * The new players turn
	 */
	public final NetPlayer playersTurn;
	
	/**
	 * The new phase of the game
	 */
	public final Phase phase;
	
	/**
	 * 
	 * @param p The next players turn
	 * @param phase The phase of the next turn
	 */
	public SvrNextTurnEvent(NetPlayer p, Phase phase) {
		super(EventType.SvrNextTurnEvent);
		this.playersTurn = p;
		this.phase = phase;
		System.out.println("EVENT CREATED: " + toString());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Player: " + playersTurn.name;
	}
}
