package risk.event;

/**
 * Sent to confirm game is starting
 * (Sent by server locally)
 * 
 * @see EventType#LclStartGameSentEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class LclStartGameSentEvent extends AEvent {
	private static final long serialVersionUID = -3182974363634236442L;
	
	/**
	 * The name of the map to be played
	 */
	public final String mapName;
	
	/**
	 * The local player
	 */
	public final String player;
	
	/**
	 * True if local player is host, else false
	 */
	public final boolean host;
	
	/**
	 * True if game is multiplayer, else false
	 */
	public final boolean multiplayer;
	
	/**
	 * 
	 * @param mapName Name of the map to be played
	 * @param player Name of the local player
	 * @param host True if local player is host, else false
	 * @param multiplayer True if game is multiplayer, else false
	 */
	public LclStartGameSentEvent(String mapName, String player, boolean host, boolean multiplayer) {
		super(EventType.LclStartGameSentEvent);
		this.mapName = mapName;
		this.player = player;
		this.host = host;
		this.multiplayer = multiplayer;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return  this.getClass().getSimpleName() + " Map: " + mapName + ", Player: " + player;
	}
}
