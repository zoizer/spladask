package risk.event;

import java.util.List;

import risk.net.ServerClient;

/**
 * Sent by Host Server before SvrStartGameEvent is sent, used to initialize some server specific objects.
 * (Sent by server locally)
 * 
 * @see EventType#LclServerHostStartGameEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class LclServerHostStartGameEvent extends AEvent {
	private static final long serialVersionUID = 5573382330399047807L;
	
	/**
	 * List of all remote players connections
	 */
	public final List<ServerClient> remotePlayers;
	
	/**
	 * 
	 * @param remotePlayers list of remote player connections
	 */
	public LclServerHostStartGameEvent(List<ServerClient> remotePlayers) {
		super(EventType.LclServerHostStartGameEvent);
		this.remotePlayers = remotePlayers;
		System.out.println("EVENT CREATED: " + toString());
	}

}
