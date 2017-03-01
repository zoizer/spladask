package risk.gameview;

import risk.event.InputEventManager;
import risk.general.event.EventManager;
import risk.ui.*;

public class PlayerGameView extends GameView {
	private UI ui;
	@SuppressWarnings("unused") // GUI and UI can merge, or UI can own GUI. Prefer the latter.
	private GUI gui;

	public PlayerGameView(int id, EventManager inputEventManager) {
		super(id, GameView.GAME_VIEW_TYPE_PLAYER);
		ui = new UI(inputEventManager);
		gui = new GUI(ui);
	}
}
