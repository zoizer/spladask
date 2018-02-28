package risk.event;

/**
 * Sent to initiate start server / join server / create local game
 * (Sent by any locally)
 * 
 * @see EventType#LclStartGameEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class LclStartGameEvent extends AEvent {
	private static final long serialVersionUID = 5769059546842263426L;
	
	/**
	 * The name of the map to be played
	 */
	public final String mapName;
	
	/**
	 * The name of the local player
	 */
	public final String player;
	
	/**
	 * the address of the host
	 */
	public final String hostAddr;
	
	/**
	 * true if local player is the host, else false
	 */
	public final boolean host;
	
	/**
	 * true if game is multiplayer, else false
	 */
	public final boolean multiplayer;
	
	/**
	 * The default port for the game
	 */
	public static final int hostPort = 24446;
	
	/**
	 * 
	 * @param mapName The name of the map to be played
	 * @param player The name of the local player
	 * @param hostAddr The address of the host
	 * @param host True if local player is host, else false
	 * @param multiplayer True if multiplayer, else false
	 */
	public LclStartGameEvent(String mapName, String player, String hostAddr, boolean host, boolean multiplayer) {
		super(EventType.LclStartGameEvent);
		this.mapName = mapName;
		this.player = player;
		this.hostAddr = hostAddr;
		this.host = host;
		this.multiplayer = multiplayer;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return  this.getClass().getSimpleName() + " Map: " + mapName + ", Player: " + player + ", Addr: " + hostAddr + ", Host: " + host + ", Multiplayer: " + multiplayer;
	}
}
