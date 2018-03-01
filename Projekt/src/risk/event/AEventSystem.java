package risk.event;

import risk.util.Delegate;

/**
 * Simplification of event handling, not really necessary, but helpful to reduce code and increase readability.
 * Could be simplified further, but that's too much work atm.
 * Knows and uses the global EventManager
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public abstract class AEventSystem implements IEventSystem {
	
	/**
	 * Queues the event into the EventManager
	 * 
	 * @param e event to queue
	 */
	public void queueEvent(IEvent e) {
		EventManager.get().queueEvent(e);
	}
	
	/**
	 * Attaches the listener delegate to all events of type eventType
	 * @param d The delegate to be called when event is processed
	 * @param eventType The event type which should signal the call to the delegate
	 */
	protected void attachListener(Delegate d, EventType eventType) {
		EventManager.get().attachListener(d, eventType);
	}
	
	/**
	 * Detaches the listener delegate from all events of type eventType
	 * @param d The delegate which was registered
	 * @param eventType The EventType to which the delegate was registered
	 */
	protected void detachListener(Delegate d, EventType eventType) {
		EventManager.get().detachListener(d, eventType);
	}
}
