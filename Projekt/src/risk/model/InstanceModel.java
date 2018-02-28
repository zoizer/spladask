package risk.model;


import java.util.ArrayList;
import java.util.List;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.LclGenerateMap;
import risk.event.LclHostGameEvent;
import risk.event.LclStartGameSentEvent;
import risk.event.LclStartGameEvent;
import risk.event.SvrStartGameEvent;
import risk.general.Map;
import risk.net.Client;
import risk.net.NetPlayer;
import risk.net.Server;
import risk.util.Delegate;
import risk.util.ErrorHandler;
import risk.util.IResetable;

/**
 * The instance model contains mainly setup data and logic, which will coordinate what other game models will be created.
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 *
 */
public class InstanceModel extends AEventSystem implements IResetable {
	private IGameModel gameModel;
	private String player;
	private List<NetPlayer> players;
	private Server server;
	private Client client;
	
	public InstanceModel() {
		gameModel = null;
		player = null;
		players = null;
		server = null;
		client = null;
		
		attachListeners();
	}
	
	/**
	 * 
	 */
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "lclStartGameSent"), EventType.LclStartGameSentEvent);
		attachListener(new Delegate(this, "lclStartGame"), EventType.LclStartGameEvent);
		attachListener(new Delegate(this, "svrStartGame"), EventType.SvrStartGameEvent);
		attachListener(new Delegate(this, "generateMap"), EventType.LclGenerateMap);
		attachListener(new Delegate(this, "lclEndGame"), EventType.LclEndGameEvent);
	}

	/**
	 * 
	 */
	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "lclStartGameSent"), EventType.LclStartGameSentEvent);
		detachListener(new Delegate(this, "lclStartGame"), EventType.LclStartGameEvent);
		detachListener(new Delegate(this, "svrStartGame"), EventType.SvrStartGameEvent);
		detachListener(new Delegate(this, "generateMap"), EventType.LclGenerateMap);
		detachListener(new Delegate(this, "lclEndGame"), EventType.LclEndGameEvent);
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Handles the generation of the map and saving of the map.
	 * 
	 * @param ev the event which was listened to
	 */
	public void generateMap(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclGenerateMap);
		LclGenerateMap e = (LclGenerateMap) ev;
		Map.createMap(e.name);
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Handles some game initialization.
	 * 
	 * @param ev the event which was listened to
	 */
	public void lclStartGameSent(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclStartGameSentEvent);
		LclStartGameSentEvent e = (LclStartGameSentEvent) ev;
		
		// TODO LEAVE CURRENT GAME IF IN ONE.
		
		player = e.player;
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Handles the start game request by either starting a local / host / client game
	 * 
	 * @param ev the event which was listened to
	 */
	public void lclStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclStartGameEvent);
		LclStartGameEvent e = (LclStartGameEvent) ev;
		
		reset();
		
		ErrorHandler.ASSERT(e.player.equals(player));
		
		if (!e.multiplayer) { // SINGLEPLAYER
			players = new ArrayList<NetPlayer>();
			players.add(new NetPlayer(e.player, true));
			Map m = Map.loadMap(e.mapName);
			queueEvent(new SvrStartGameEvent(m, players)); // OK, start game.
		} else if (e.host) { // HOST
			Map m = Map.loadMap(e.mapName);
			server = new Server(LclStartGameEvent.hostPort, m, new NetPlayer(e.player, e.host));
			(new Thread(server)).start();
			queueEvent(new LclHostGameEvent(new NetPlayer(e.player, e.host))); // BEGIN LISTEN FOR CLIENTS
		} else if (!e.host) { // CLIENT
			// TRY TO CONNECT TO HOST
			// IF SUCCESS SEND LclJoinGameEvent
			client = new Client(e.hostAddr, LclStartGameEvent.hostPort, e.player);
			if (client.initialize()) {
				Thread t = new Thread(client);
				t.start();
			} else {
				client.stopConnection(); // destroy will undo too much, stopConnection is enough.
				client = null;	// Bad design, but I can't be bothered to fix it ;)
			}
			
		}
		
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * The purpose of this function is to actually start the game.
	 * 
	 * @param ev the event which was listened to
	 */
	public void svrStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrStartGameEvent);
		SvrStartGameEvent e = (SvrStartGameEvent) ev;
		
		ErrorHandler.ASSERT(gameModel == null);
		
		String playerName = this.player;
		NetPlayer player = null;
		for (int i = 0; i < e.players.size(); i++) if (e.players.get(i).name.equals(playerName)) player = e.players.get(i);
		ErrorHandler.ASSERT(player != null);
		
		if (player.host) {
			gameModel = new ServerGameModel(e.map, e.players, e.startingStrength);
		} else {
		//	gameModel = new ClientGameModel(); // dummy class as there should be no model for remote games.
		}
		
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * The purpose of this is to reset the game.
	 * 
	 * @param ev the event which was listened to
	 */
	public void lclEndGame(IEvent ev) {
		reset();
	}

	/**
	 * Reset function will reset this class so it can be reused as if new.
	 */
	@Override
	public void reset() {
		if (server != null) {
			server.destroy();
			server = null;
		}
		
		if (client != null) {
			client.destroy();
			client = null;
		}
		
		if (gameModel != null) {
			gameModel.destroy();
			gameModel = null;
		}
		
		players = null;
	}
}
