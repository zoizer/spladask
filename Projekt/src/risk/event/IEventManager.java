package risk.event;

import risk.util.Delegate;

/**
 * IEventManager is the base for a event manager.
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	11/02
 */
public interface IEventManager {
	public void attachListener(Delegate eventdel, EventType eventType);
	public void detachListener(Delegate eventdel, EventType eventType);
//	public void triggerEvent(IEvent event);
	public void queueEvent(IEvent event); // remember thread safety.
//	public void abortLastEventOfType(EventType eventType);
//	public void abortAllOfEvent(EventType eventType);
	public void triggerQueue();
}
