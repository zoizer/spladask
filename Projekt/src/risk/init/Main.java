package risk.init;

import risk.model.InstanceModel;
import risk.view.InstanceView;
import risk.controller.InstanceController;
import risk.event.EventManager;

/**
 * Dummy class containing the main function
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 *
 */
public final class Main {
	
	/**
	 * The game entrypoint
	 * @param args Unused
	 */
	@SuppressWarnings("unused") 
	public static void main(String[] args) {
		EventManager.create();	// must be created to allow event registrations.
		
		InstanceController 	c = new InstanceController();
		InstanceView 		v = new InstanceView(c.getMouseAdapter(), c.getWindowAdapter(), c.getActionListener(), c.getIResponse());
		InstanceModel 		m = new InstanceModel();

		while (true) {
			EventManager.get().processQueue();
		}
	}
}

// TODO MAKE GOOD CLEANUP FOR SERVER AND CLIENT TO PREVENT CASCADING ERRORS.

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
 * 
 * 
 * RISK:
 * Each player goes in turn,
 * first train units (amount based on captured zones), then attack(any number), then move within borders(max 1 time), then pass turn. 
 * Min 1 unit in your zones.
 * 
 */





/*


 Current plan for multiplayer:
 RpcHost -> creates a local Matchmaker object that listens for connections
 RpcJoin -> Joins the matchmaker object

	Make all Rpc and Svr derive of some INetEvent

*/