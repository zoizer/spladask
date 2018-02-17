package risk.model;

import risk.event.AEventSystem;
import risk.general.Map;

public class ServerGameModel extends AEventSystem implements IGameModel {
	private Map map;
	
	public ServerGameModel(Map map) {
		this.map = map;
	}
	
	@Override
	public void attachListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void detachListeners() {
		// TODO Auto-generated method stub
		
	}
}
