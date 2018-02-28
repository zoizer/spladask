package risk.event;

import risk.net.NetPlayer;

/**
 * Sent by Model to server to start listening for clients.
 * (Sent by multiplayer host locally)
 * 
 * @see EventType#LclHostGameEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class LclHostGameEvent extends AEvent {
	private static final long serialVersionUID = 8493059126250174534L;
	
	/**
	 * The local host player
	 */
	public final NetPlayer host;
	
	/**
	 * 
	 * @param host The local host player
	 */
	public LclHostGameEvent(NetPlayer host) {
		super(EventType.LclHostGameEvent);
		this.host = host;
		System.out.println("EVENT CREATED: " + toString());
	}
}
