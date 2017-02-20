package risk.gameview;

import risk.event.InputEventManager;
import risk.general.event.EventManager;
import risk.ui.GUI;
import risk.ui.UI;

public class PlayerGameView extends GameView {
	private UI ui;
	private GUI gui;
	private EventManager inputEventManager;

	public PlayerGameView(int id) {
		super(id, GameView.GAME_VIEW_TYPE_PLAYER);
		inputEventManager = new InputEventManager();
		ui = new UI(inputEventManager);
		gui = new GUI(ui);
	}
	
	
	public void Update() {
		inputEventManager.Update();
		
	}

}
