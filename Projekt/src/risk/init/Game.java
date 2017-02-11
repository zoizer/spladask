package risk.init;

import risk.event.InputEventManager;
import risk.event.RiskEvent;
import risk.gameview.GameView;
import risk.gameview.PlayerGameView;
import risk.general.event.EventManager;
import risk.general.event.IEvent;
import risk.general.util.Delegate;
import risk.ui.*;

public class Game {
	private PlayerGameView pgv;
	
// Test ....
	public Game() {
		EventManager.CreateGlobalEventManager();
		pgv = new PlayerGameView(1, GameView.GAME_VIEW_TYPE_PLAYER);
		
		// om PGV tar hand om både ui och gui kanske man kan få spelet att spela mot datorer utan fönster.

		AttachListeners();
		InitGame();

		while(true) {
			pgv.Update();
			EventManager.Get().Update();
		}
	}
	
	
	private void AttachListeners() {
		EventManager.Get().AttachListener(new Delegate(this, "RequestNewGame"), RiskEvent.EVENT_NEW_GAME_REQUEST);
		EventManager.Get().AttachListener(new Delegate(this, "RequestNewGame"), RiskEvent.EVENT_NEW_GAME_PRE);
		EventManager.Get().AttachListener(new Delegate(this, "RequestNewGame"), RiskEvent.EVENT_NEW_GAME);
		EventManager.Get().AttachListener(new Delegate(this, "RequestNewGame"), RiskEvent.EVENT_NEW_GAME_POST);
	}


	private void InitGame() {
	}
	
	public void RequestNewGame(IEvent event) {
		//gui.StartMap();
		
		if(event.GetEventType() == RiskEvent.EVENT_NEW_GAME_REQUEST) { // Allow the request.
			EventManager.Get().QueueEvent(new RiskEvent(0.0f, RiskEvent.EVENT_NEW_GAME_PRE));
		} else if (event.GetEventType() == RiskEvent.EVENT_NEW_GAME_PRE) { // Queue the next event.
			EventManager.Get().QueueEvent(new RiskEvent(0.0f, RiskEvent.EVENT_NEW_GAME));
		} else if (event.GetEventType() == RiskEvent.EVENT_NEW_GAME) {
			EventManager.Get().QueueEvent(new RiskEvent(0.0f, RiskEvent.EVENT_NEW_GAME_POST));
		} else if (event.GetEventType() == RiskEvent.EVENT_NEW_GAME_POST) {
			// Do nothing.
		}
	}
	
}
