package risk.init;

import risk.event.InputEventManager;
import risk.event.RiskEvent;
import risk.general.event.EventManager;
import risk.general.event.IEvent;
import risk.general.util.Delegate;
import risk.ui.*;

public class Game {
	private UI ui;
	private GUI gui;
	private EventManager inputEventManager;

// Test ....
	public Game() {
		EventManager.CreateGlobalEventManager();
		inputEventManager = new InputEventManager();
		ui = new UI(inputEventManager);
		gui = new GUI(ui);
		
		InitGame();

		while(true) {
			inputEventManager.Update();
			EventManager.Get().Update();
		}
	}
	
	
	private void InitGame() {
		EventManager.Get().AttachListener(new Delegate(this, "RequestNewGame"), RiskEvent.EVENT_REQUEST_NEW_GAME);
	}
	
	public void RequestNewGame(IEvent event) {
		gui.StartMap();
	}
	
}
