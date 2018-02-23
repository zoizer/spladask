package risk.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import risk.event.AEventSystem;
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


public class InstanceModel extends AEventSystem {
	IGameModel gameModel;
	GameInterfaceModel ui;
	String player;
	List<NetPlayer> players;
	Server server;
	Client client;
	
	public InstanceModel() {
		gameModel = null;
		ui = null;
		player = null;
		players = null;
		server = null;
		client = null;
		
		attachListeners();
	}
	
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "lclStartGameSent"), IEvent.EventType.LclStartGameSentEvent);
		attachListener(new Delegate(this, "lclStartGame"), IEvent.EventType.LclStartGameEvent);
		attachListener(new Delegate(this, "svrStartGame"), IEvent.EventType.SvrStartGameEvent);
		attachListener(new Delegate(this, "generateMap"), IEvent.EventType.LclGenerateMap);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "lclStartGameSent"), IEvent.EventType.LclStartGameSentEvent);
		detachListener(new Delegate(this, "lclStartGame"), IEvent.EventType.LclStartGameEvent);
		detachListener(new Delegate(this, "svrStartGame"), IEvent.EventType.SvrStartGameEvent);
		detachListener(new Delegate(this, "generateMap"), IEvent.EventType.LclGenerateMap);
	}
	
	public void generateMap(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclGenerateMap);
		LclGenerateMap e = (LclGenerateMap) ev;
		Map.createMap(e.name);
	}
	
	public void lclStartGameSent(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclStartGameSentEvent);
		LclStartGameSentEvent e = (LclStartGameSentEvent) ev;
		
		// TODO LEAVE CURRENT GAME IF IN ONE.
		
		player = e.player;
	}
	
	public void lclStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclStartGameEvent);
		LclStartGameEvent e = (LclStartGameEvent) ev;
		
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
		
		if (ui != null) {
			ui.destroy();
			ui = null;
		}
		
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
			
		} // THIS MAY BE WRONG. THIS IS PROBABLY NOT WHERE I SHOULD DO THIS AS THE SERVER WILL GET THIS MESSAGE FROM ALL PLAYERS
		
	}
	
	public void svrStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrStartGameEvent);
		SvrStartGameEvent e = (SvrStartGameEvent) ev;
		
		ErrorHandler.ASSERT(gameModel == null);
		ErrorHandler.ASSERT(ui == null);
		
		String playerName = this.player;
		NetPlayer player = null;
		for (int i = 0; i < e.players.size(); i++) if (e.players.get(i).name.equals(playerName)) player = e.players.get(i);
		ErrorHandler.ASSERT(player != null);
		
		ui = new GameInterfaceModel(player.name);
		if (player.host) {
			gameModel = new ServerGameModel(e.map);
		} else {
			gameModel = new ClientGameModel(); // dummy class as there should be no model for remote games.
		}
		
	}
}
