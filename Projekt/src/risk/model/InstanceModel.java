package risk.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import risk.event.AEventSystem;
import risk.event.IEvent;
import risk.event.LclGenerateMap;
import risk.event.LclHostGameEvent;
import risk.event.LclStartGameSentEvent;
import risk.event.RpcStartGameEvent;
import risk.event.SvrStartGameEvent;
import risk.general.Map;
import risk.net.NetPlayer;
import risk.util.Delegate;
import risk.util.ErrorHandler;


public class InstanceModel extends AEventSystem {
	IGameModel gameModel;
	GameInterfaceModel ui;
	String player;
	List<NetPlayer> players;
	
	public InstanceModel() {
		gameModel = null;
		ui = null;
		player = null;
		players = null;

		attachListeners();
	}
	
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "lclStartGameSent"), IEvent.EventType.LclStartGameSentEvent);
		attachListener(new Delegate(this, "rpcStartGame"), IEvent.EventType.RpcStartGameEvent);
		attachListener(new Delegate(this, "svrStartGame"), IEvent.EventType.SvrStartGameEvent);
		attachListener(new Delegate(this, "generateMap"), IEvent.EventType.LclGenerateMap);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "lclStartGameSent"), IEvent.EventType.LclStartGameSentEvent);
		detachListener(new Delegate(this, "rpcStartGame"), IEvent.EventType.RpcStartGameEvent);
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
	
	public void rpcStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof RpcStartGameEvent);
		RpcStartGameEvent e = (RpcStartGameEvent) ev;
		
		if (!e.multiplayer) { // SINGLEPLAYER
			ErrorHandler.ASSERT(e.player.equals(player));
			players = new ArrayList<NetPlayer>();
			players.add(new NetPlayer(e.player, true));
			Map m = Map.loadMap(e.mapName);
			queueEvent(new SvrStartGameEvent(m, players)); // OK, start game.
		} else if (e.host) { // HOST
			ErrorHandler.ASSERT(e.player.equals(player));
			players = new ArrayList<NetPlayer>();
			players.add(new NetPlayer(e.player, e.host));
			
			queueEvent(new LclHostGameEvent(players)); // BEGIN LISTEN FOR CLIENTS
		} else if (!e.host) { // CLIENT
			// TRY TO CONNECT TO HOST
		} // THIS MAY BE WRONG. THIS IS PROBABLY NOT WHERE I SHOULD DO THIS AS THE SERVER WILL GET THIS MESSAGE FROM ALL PLAYERS
		
	}
	
	public void svrStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrStartGameEvent);
		SvrStartGameEvent e = (SvrStartGameEvent) ev;
		
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
