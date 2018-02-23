package risk.model;

import risk.event.AEventSystem;
import risk.general.Zone;

public class GameInterfaceModel extends AEventSystem {
	// should contain all relevant user interface data.
	// like Zone X selected atm,
	// 3 Units selected,
	// etc.
	private Zone selectedZone;
	private String player;
	
	public GameInterfaceModel(String player) {
		this.selectedZone = null;
		this.player = player;
		attachListeners();
	}

	@Override
	public void attachListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detachListeners() {
		// TODO Auto-generated method stub
		
	}
	
	public void destroy() {
		detachListeners();
	}
}
