package risk.controller;

import java.util.List;

import risk.event.AEventSystem;
import risk.net.ServerClient;

/**
 * RemotePlayerControllers handles all remote players input
 * (used on server during multiplayer only)
 * 
 * @author Filip Törnqvist
 * @version 2018-02-28
 *
 */
public class RemotePlayerControllers extends AEventSystem implements IPlayerController {
	private List<ServerClient> remotePlayers;
	
	/**
	 * Constructor
	 * 
	 * @param remotePlayers a list of all the clients connected to the game.
	 */
	public RemotePlayerControllers(List<ServerClient> remotePlayers) {
		this.remotePlayers = remotePlayers;
		
		for (ServerClient c : remotePlayers) {
			c.setController(this);
		}
		
		attachListeners();
	}
	
	/**
	 * unused
	 */
	@Override
	public void attachListeners() {
		
	}

	/**
	 * unused
	 */
	@Override
	public void detachListeners() {
		
	}
	
	/**
	 * destroy the RemotePlayerControllers to prevent unnecessary input.
	 */
	@Override
	public void destroy() {
		detachListeners();
		for (ServerClient c : remotePlayers) {
			c.setController(null);
		}
	}
}
