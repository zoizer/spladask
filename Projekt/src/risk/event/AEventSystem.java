package risk.event;

import risk.util.Delegate;

public abstract class AEventSystem implements IEventSystem {
	protected void queueEvent(IEvent e) {
		EventManager.get().queueEvent(e);
	}
	
	protected void attachListener(Delegate d, IEvent.EventType eventType) {
		EventManager.get().attachListener(d, eventType);
	}
	
	protected void detachListener(Delegate d, IEvent.EventType eventType) {
		EventManager.get().attachListener(d, eventType);
	}
}
