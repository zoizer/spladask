package risk.controller;

import java.util.List;

import risk.event.AEventSystem;
import risk.net.ServerClient;

public class RemotePlayerControllers extends AEventSystem implements IPlayerController {
	List<ServerClient> remotePlayers;
	
	public RemotePlayerControllers(List<ServerClient> remotePlayers) {
		this.remotePlayers = remotePlayers;
		
		for (ServerClient c : remotePlayers) {
			c.setController(this);
		}
		
		attachListeners();
	}
	
	@Override
	public void attachListeners() {
		
	}

	@Override
	public void detachListeners() {
		
	}
	
	public void shutdown() {
		detachListeners();
		for (ServerClient c : remotePlayers) {
			c.setController(null);
		}
	}
}
