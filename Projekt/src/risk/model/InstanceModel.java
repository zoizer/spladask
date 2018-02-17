package risk.model;


import java.util.Arrays;

import risk.event.AEventSystem;
import risk.event.IEvent;
import risk.event.LclGenerateMap;
import risk.event.RpcPreStartGameEvent;
import risk.event.SvrStartGameEvent;
import risk.general.Map;
import risk.util.Delegate;
import risk.util.ErrorHandler;


public class InstanceModel extends AEventSystem {
	IGameModel gameModel;
	GameInterfaceModel ui;
	
	public InstanceModel() {
		gameModel = null;
		ui = null;
		attachListeners();
		//queueEvent(new CreateInstanceEvent("Risk: The Game")); // this is stupid. should be done in the view only.
		
		
		
	}
	
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "preStartGame"), IEvent.EventType.RpcPreStartGameEvent);
		attachListener(new Delegate(this, "startGame"), IEvent.EventType.SvrStartGameEvent);
		attachListener(new Delegate(this, "generateMap"), IEvent.EventType.LclGenerateMap);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "preStartGame"), IEvent.EventType.RpcPreStartGameEvent);
		detachListener(new Delegate(this, "startGame"), IEvent.EventType.SvrStartGameEvent);
		detachListener(new Delegate(this, "generateMap"), IEvent.EventType.LclGenerateMap);
	}
	
	public void generateMap(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof LclGenerateMap);
		LclGenerateMap e = (LclGenerateMap) ev;
		Map.createMap(e.name);
	}
	
	public void preStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof RpcPreStartGameEvent);
		RpcPreStartGameEvent e = (RpcPreStartGameEvent) ev;
		// is it multiplayer ? ???? then wait for connection i guess
		// this is local now though.
		
		Map m = Map.loadMap(e.mapName);
		queueEvent(new SvrStartGameEvent(m, false, true, Arrays.asList("General Host"))); // OK, start game.
	}
	
	public void startGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrStartGameEvent);
		SvrStartGameEvent e = (SvrStartGameEvent) ev;
		ui = new GameInterfaceModel();
		if (e.host) {
			gameModel = new ServerGameModel(e.map);
		} else {
			gameModel = new ClientGameModel(); // dummy class as there should be no model for remote games.
		}
		
	}
}
