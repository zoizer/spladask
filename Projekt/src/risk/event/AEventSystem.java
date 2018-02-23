package risk.event;

import risk.util.Delegate;

public abstract class AEventSystem implements IEventSystem {
	public void queueEvent(IEvent e) {
		EventManager.get().queueEvent(e);
	}
	
	protected void attachListener(Delegate d, EventType eventType) {
		EventManager.get().attachListener(d, eventType);
	}
	
	protected void detachListener(Delegate d, EventType eventType) {
		EventManager.get().detachListener(d, eventType);
	}
}
