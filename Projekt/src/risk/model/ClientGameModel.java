package risk.model;

import risk.event.AEventSystem;

public class ClientGameModel extends AEventSystem implements IGameModel {
	
	public ClientGameModel() {
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

	@Override
	public void destroy() {
		detachListeners();
	}

}
