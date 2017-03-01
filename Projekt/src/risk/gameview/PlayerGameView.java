package risk.gameview;

import risk.event.InputEventManager;
import risk.general.event.EventManager;
import risk.ui.*;

public class PlayerGameView extends GameView {
	private UI ui;
	@SuppressWarnings("unused") // GUI and UI can merge, or UI can own GUI. Prefer the latter.
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
