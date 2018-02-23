package risk.model;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.LclGenerateMap;
import risk.event.SvrNextTurnEvent;
import risk.general.Zone;
import risk.util.Delegate;
import risk.util.ErrorHandler;

public class GameInterfaceModel extends AEventSystem {
	// should contain all relevant user interface data.
	// like Zone X selected atm,
	// 3 Units selected,
	// etc.
	private Zone selectedZone;
	private String player;
	private String playersTurn;
	
	public GameInterfaceModel(String player) {
		this.selectedZone = null;
		this.player = player;
		this.playersTurn = null;
		attachListeners();
	}

	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
	}
	
	public void destroy() {
		detachListeners();
	}
	
	public void svrNextTurn(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrNextTurnEvent);
		SvrNextTurnEvent e = (SvrNextTurnEvent) ev;
		
		playersTurn = e.playersTurn.name;
	}
}
