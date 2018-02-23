package risk.model;

import risk.event.IEventSystem;

public interface IGameModel extends IEventSystem {
	
	public void destroy();
}
