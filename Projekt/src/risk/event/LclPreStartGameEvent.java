package risk.event;

/**
 * Sent locally before attempting to start, join or host a new game
 * (Sent by any locally)
 * 
 * @see EventType#LclPreStartGameEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class LclPreStartGameEvent extends AEvent {
	private static final long serialVersionUID = -5116629377624281181L;
	
	/**
	 * The name of the map to be played
	 */
	public final String mapName;
	
	/**
	 * if the local player is the host
	 */
	public final boolean host;
	
	/**
	 * if the game is multiplayer
	 */
	public final boolean multiplayer;
//	public final String hostAddr;
//	public final int hostPort;
	
	/**
	 * 
	 * @param mapName The name of the map to be played
	 * @param host true if local player is host, false if not
	 * @param multiplayer true if the game is multiplayer, false if not
	 */
	public LclPreStartGameEvent(String mapName, boolean host, boolean multiplayer) {
		super(EventType.LclPreStartGameEvent);
		this.mapName = mapName;
		this.host = host;
		this.multiplayer = multiplayer;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return  this.getClass().getSimpleName() + " Map: " + mapName + ", Host: " + host + ", Multiplayer: " + multiplayer;
	}
}
