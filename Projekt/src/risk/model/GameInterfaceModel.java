package risk.model;

import risk.general.Zone;

public class GameInterfaceModel {
	// should contain all relevant user interface data.
	// like Zone X selected atm,
	// 3 Units selected,
	// etc.
	private Zone selectedZone;
	private String player;
	
	public GameInterfaceModel(String player) {
		selectedZone = null;
		this.player = player;
	}
	
}
