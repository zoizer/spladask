package risk.init;

import risk.general.event.EventManager;
import risk.ui.*;

public class Game {
	private UI ui;
	private GUI gui;
	private EventManager inputEventManager;

// Test ....
	public Game() {
		EventManager.CreateGlobalEventManager();
		inputEventManager = new EventManager();
		ui = new UI(inputEventManager);
		gui = new GUI(ui);
		
	}
	
	
}
