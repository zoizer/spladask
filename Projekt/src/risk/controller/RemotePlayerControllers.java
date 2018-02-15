package risk.controller;

import risk.event.AEventSystem;

public class RemotePlayerControllers extends AEventSystem implements IPlayerController {

	@Override
	public void attachListeners() {
		// should remain empty for Controllers
	}

	@Override
	public void detachListeners() {
		// should remain empty for Controllers
	}
	
	
	// BOTH PLAYERS WILL PLAY AT THE SAME TIME, HENCE MULTITHREADED. TURN ENDS WHEN BOTH STOP.
	// Probably a bad idea.
}
