package risk.game;

import risk.event.EventManager;
import risk.event.IEvent;
import risk.event.InputEventManager;
import risk.event.RiskEvent;
import risk.event.RiskGameEvent;
import risk.event.RiskZoneEvent;
import risk.game.logic.Core;
import risk.ui.GUI;
import risk.ui.UI;
import risk.util.Delegate;

/**
 * Game is the Game. contains the UI, GUI, Timer etc.
 * Runs the main loop reading the events.
 * 
 * 
 * @author 		Filip T�rnqvist
 * @version 	04/03
 */
public class Game {
	@SuppressWarnings("unused")
	public Game() { //
		EventManager.create();
		EventManager inputEventManager = new InputEventManager();

		UI ui = new UI(inputEventManager);
		GUI gui = new GUI(ui);
		Timer t = new Timer(); // Threaded.
		
		attachListeners();
		
		while(true) {
			inputEventManager.triggerQueue();
			EventManager.get().triggerQueue();
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
	
	private void attachListeners() {
		EventManager.get().attachListener(new Delegate(this, "RequestNewGame"), RiskEvent.EVENT_NEW_GAME_PRE);
		EventManager.get().attachListener(new Delegate(this, "RequestNewGame"), RiskGameEvent.EVENT_NEW_GAME);
		EventManager.get().attachListener(new Delegate(this, "RequestNewGame"), RiskEvent.EVENT_NEW_GAME_POST);
		EventManager.get().attachListener(new Delegate(Core.get(), "LoadMap"), RiskGameEvent.EVENT_NEW_GAME);
		EventManager.get().attachListener(new Delegate(Core.get(), "SelectZone"), RiskZoneEvent.EVENT_SELECT_ZONE);
	}
	
	public void RequestNewGame(IEvent event) {
		if (event.GetEventType() == RiskEvent.EVENT_NEW_GAME_PRE) { // Queue the next event.
			EventManager.get().queueEvent(new RiskGameEvent(0.0f, RiskGameEvent.EVENT_NEW_GAME, "sistariskcolored"));
		} else if (event.GetEventType() == RiskGameEvent.EVENT_NEW_GAME) {
			EventManager.get().queueEvent(new RiskEvent(0.0f, RiskEvent.EVENT_NEW_GAME_POST));
		} else if (event.GetEventType() == RiskEvent.EVENT_NEW_GAME_POST) {
			// The new game have been created.
		}
	}
	
}
