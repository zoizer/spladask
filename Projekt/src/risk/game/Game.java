package risk.game;

import risk.event.InputEventManager;
import risk.event.RiskEvent;
import risk.event.RiskGameEvent;
import risk.event.RiskZoneEvent;
import risk.game.logic.GeneralGameEvents;
import risk.general.event.*;
import risk.general.util.Delegate;
import risk.ui.GUI;
import risk.ui.UI;

public class Game {
	@SuppressWarnings("unused")
	public Game() {
		EventManager.CreateGlobalEventManager();
		EventManager inputEventManager = new InputEventManager();

		UI ui = new UI(inputEventManager);
		GUI gui = new GUI(ui);
		
		AttachListeners();
		
		while(true) {
			inputEventManager.Update();
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
		EventManager.Get().AttachListener(new Delegate(this, "RequestNewGame"), RiskGameEvent.EVENT_NEW_GAME);
		EventManager.Get().AttachListener(new Delegate(this, "RequestNewGame"), RiskEvent.EVENT_NEW_GAME_POST);
		EventManager.Get().AttachListener(new Delegate(GeneralGameEvents.Get(), "LoadMap"), RiskGameEvent.EVENT_NEW_GAME);
		EventManager.Get().AttachListener(new Delegate(GeneralGameEvents.Get(), "SelectZone"), RiskZoneEvent.EVENT_SELECT_ZONE);
	}
	
	public void RequestNewGame(IEvent event) {
		if (event.GetEventType() == RiskEvent.EVENT_NEW_GAME_PRE) { // Queue the next event.
			EventManager.Get().QueueEvent(new RiskGameEvent(0.0f, RiskGameEvent.EVENT_NEW_GAME, "sistariskcolored"));
		} else if (event.GetEventType() == RiskGameEvent.EVENT_NEW_GAME) {
			EventManager.Get().QueueEvent(new RiskEvent(0.0f, RiskEvent.EVENT_NEW_GAME_POST));
		} else if (event.GetEventType() == RiskEvent.EVENT_NEW_GAME_POST) {
			// The new game have been created.
		}
	}
	
}
