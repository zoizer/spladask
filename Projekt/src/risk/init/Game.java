package risk.init;

import risk.event.EventManager;
import risk.ui.*;

public class Game {
	private UI ui;
	private GUI gui;
	private EventManager inputEventManager;
	
	// en ny kommentar.
	public Game() {
		inputEventManager = new EventManager();
		ui = new UI(inputEventManager);
		gui = new GUI(ui);
		
	}
	
	
}
