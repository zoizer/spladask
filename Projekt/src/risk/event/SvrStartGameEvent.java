package risk.event;

import java.util.List;

import risk.general.Map;
import risk.net.NetPlayer;

/**
 * Sent by server to actually start the game.
 * (Sent from server to all)
 * 
 * @see EventType#SvrStartGameEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class SvrStartGameEvent extends ANetEvent {
	private static final long serialVersionUID = 9076965650139810351L;
	
	/**
	 * The game map
	 */
	public final Map map;
	
	/**
	 * The players which will play
	 */
	public final List<NetPlayer> players;
	
	/**
	 * Army strength people place at the start of the game.
	 */
	public final int startingStrength;
	
	// should probably contain info about if multiplayer etc.
	
	/**
	 * 
	 * @param map The map which will be played
	 * @param players The players which will play
	 */
	public SvrStartGameEvent(Map map, List<NetPlayer> players) {
		super(EventType.SvrStartGameEvent);
		this.map = map;
		this.players = players;
		startingStrength = 5;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " multiplayer: " + (players.size() != 1);
	}
}
