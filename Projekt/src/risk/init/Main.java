package risk.init;

/**
 * Main. the startup
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	28/02
 */

import risk.model.InstanceModel;
import risk.view.InstanceView;
import risk.controller.InstanceController;
import risk.event.EventManager;

public final class Main {
	public static void main(String[] args) {
		EventManager.create();	// must be created to allow event registrations.
		
		
		
		InstanceController 	c = new InstanceController();
		@SuppressWarnings("unused") 
		InstanceView 		v = new InstanceView(c, c);
		@SuppressWarnings("unused") 
		InstanceModel 		m = new InstanceModel();
		
		while (true) {
			EventManager.get().triggerQueue();
		}
	}
}


/*
 * When singleplayer
 *  LocalPlayerController	- Input from host player
 *  ServerGameModel			- The game
 *  LocalGameView			- visuals for host player (from server)
 *  
 * When multiplayer (n) HOST
 *  LocalPlayerController	- Input from host player
 *  ServerGameModel			- The game
 *  LocalGameView			- visuals for host player (from server)
 *  RemotePlayerControllers	- input sent from client(s) to host
 *  RemoteGameViews			- output sent from host to client(s)
 *  
 * When multiplayer (n) CLIENT
 *  LocalPlayerController	- Input from client player
 *  ClientGameModel			- Forward relevant messages to server, and receive from server.
 *  LocalGameView			- visuals for client player (from server)
 * 
 * No command that affects the game should directly do anything with view or controller, they should first get interpreted by the GameModel, THEN sent out, ex, PreStartGameEvent, then StartGameEvent.
 * local specific data will probably be kept in the LocalGameView. This includes Player name, what the player has selected etc.
 * This will also probably be duplicated in the LocalPlayerController.
 */