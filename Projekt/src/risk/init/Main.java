package risk.init;

/**
 * Main. the startup
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	28/02
 */

import risk.model.InstanceModel;
import risk.util.Delegate;
import risk.view.InstanceView;
import risk.controller.InstanceController;
import risk.event.EventManager;
import risk.event.IEvent;
import risk.event.LclGenerateMap;

public final class Main {
	
	@SuppressWarnings("unused") 
	public static void main(String[] args) {
		EventManager.create();	// must be created to allow event registrations.
		
		/*EventManager m = EventManager.get();
		Main ma = new Main();
		
		m.queueEvent(new LclGenerateMap("Test"));
		m.attachListener(new Delegate(ma, "testF"), IEvent.EventType.LclGenerateMap);
		m.attachListener(new Delegate(ma, "testF"), IEvent.EventType.LclGenerateMap);
		m.queueEvent(new LclGenerateMap("Test"));
		m.attachListener(new Delegate(ma, "testA"), IEvent.EventType.LclGenerateMap);
		m.attachListener(new Delegate(ma, "testB"), IEvent.EventType.LclGenerateMap);
		m.attachListener(new Delegate(ma, "testC"), IEvent.EventType.LclGenerateMap);*/
		
		InstanceController 	c = new InstanceController();
		InstanceView 		v = new InstanceView(c.getMouseAdapter(), c.getWindowAdapter(), c.getActionListener());
		InstanceModel 		m = new InstanceModel();
		//int a = 0;
		while (true) {
			EventManager.get().triggerQueue();
			//if (a++ == 0) m.queueEvent(new LclGenerateMap("Test"));
		}
	}
	/*int x = 0;
	
	public void testF(IEvent e) {
		x++;
	}
	
	public void testA(IEvent e) {
		x++;
	}
	
	public void testB(IEvent e) {
		x++;
	}
	
	public void testC(IEvent e) {
		x++;
	}*/
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
 * 
 * 
 * RISK:
 * Each player goes in turn,
 * first train units (amount based on captured zones), then attack(any number), then move within borders(max 1 time), then pass turn. 
 * Min 1 unit in your zones.
 * 
 */