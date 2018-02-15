package risk.model;


import java.util.Arrays;

import risk.event.AEventSystem;
import risk.event.IEvent;
import risk.event.RpcPreStartGameEvent;
import risk.event.SvrStartGameEvent;
import risk.generic.Map;
import risk.util.Delegate;
import risk.util.ErrorHandler;


public class InstanceModel extends AEventSystem {
	IGameModel gameModel;
	
	public InstanceModel() {
		gameModel = null;
		attachListeners();
		//queueEvent(new CreateInstanceEvent("Risk: The Game")); // this is stupid. should be done in the view only.
		
		
		
	}
	
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "preStartGame"), IEvent.EventType.RpcPreStartGameEvent);
		attachListener(new Delegate(this, "startGame"), IEvent.EventType.SvrStartGameEvent);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "preStartGame"), IEvent.EventType.RpcPreStartGameEvent);
		attachListener(new Delegate(this, "startGame"), IEvent.EventType.SvrStartGameEvent);
	}
	
	public void preStartGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof RpcPreStartGameEvent);
		RpcPreStartGameEvent e = (RpcPreStartGameEvent) ev;
		// is it multiplayer ? ???? then wait for connection i guess
		// this is local now though.
		Map m = new Map();
		m.loadMap(e.mapName);
		queueEvent(new SvrStartGameEvent(m, false, true, Arrays.asList("General Host"))); // OK, start game.
	}
	
	public void startGame(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrStartGameEvent);
		SvrStartGameEvent e = (SvrStartGameEvent) ev;
		if (e.host) {
			gameModel = new ServerGameModel(e.map);
		} else {
			gameModel = new ClientGameModel(); // dummy class as there should be no model for remote games.
		}
	}
}
