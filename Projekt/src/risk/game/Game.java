package risk.game;

import risk.event.RiskEvent;
import risk.gameview.PlayerGameView;
import risk.general.event.*;
import risk.general.util.Delegate;
import risk.ui.*;

public class Game {
	private PlayerGameView pgv;
	
// Test ....
	public Game() {
		EventManager.CreateGlobalEventManager();
		pgv = new PlayerGameView(1);

		AttachListeners();
		InitGame();
		WorldMapInit(); // initierar mappen och knapparna

		while(true) {
			pgv.Update();
			EventManager.Get().Update();
		}
	}
	
	/*
	 * HUR SKA GRAFIKEN BINDAS?
	 * 
	 * Genom event självklart!
	 * Vid start: EVENT_NEW_ZONE (x,y,owner) etc.
	 * Vid click på zone: någon form av GET event kanske?
	 * 
	 */
	
	private void AttachListeners() {
		EventManager.Get().AttachListener(new Delegate(this, "RequestNewGame"), RiskEvent.EVENT_NEW_GAME_PRE);
		EventManager.Get().AttachListener(new Delegate(this, "RequestNewGame"), RiskEvent.EVENT_NEW_GAME);
		EventManager.Get().AttachListener(new Delegate(this, "RequestNewGame"), RiskEvent.EVENT_NEW_GAME_POST);
	}


	private void InitGame() {
	}
	
	public void RequestNewGame(IEvent event) {
		if (event.GetEventType() == RiskEvent.EVENT_NEW_GAME_PRE) { // Queue the next event.
			EventManager.Get().QueueEvent(new RiskEvent(0.0f, RiskEvent.EVENT_NEW_GAME));
		} else if (event.GetEventType() == RiskEvent.EVENT_NEW_GAME) {
			EventManager.Get().QueueEvent(new RiskEvent(0.0f, RiskEvent.EVENT_NEW_GAME_POST));
		} else if (event.GetEventType() == RiskEvent.EVENT_NEW_GAME_POST) {
			// The new game have been created.
		}
	}
	
}
