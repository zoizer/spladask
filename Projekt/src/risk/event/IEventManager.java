package risk.event;

import risk.util.Delegate;

/**
 * IEventManager is the base interface for event managers.
 * 
 * @author 		Filip Törnqvist
 * @version 	2017-02-11
 */
public interface IEventManager {
	
	/**
	 * Attaches a listener for a specific EventType
	 * @param eventdel the listener for the event
	 * @param eventType the event to listen for
	 */
	public void attachListener(Delegate eventdel, EventType eventType);
	
	/**
	 * Detaches a specific listener from a specific EventType
	 * @param eventdel the listener which will be removed
	 * @param eventType the event which the listener listened for
	 */
	public void detachListener(Delegate eventdel, EventType eventType);
//	public void triggerEvent(IEvent event);
	
	/**
	 * Queues a specific event which will be called at some point in the near future.
	 * @param event The event to queue
	 */
	public void queueEvent(IEvent event); // remember thread safety.
	
//	public void abortLastEventOfType(EventType eventType);
//	public void abortAllOfEvent(EventType eventType);
	
	/**
	 * Processes all current events, will return when those events are processed. The queue may contain entries directly after the processQueue function has returned.
	 * Events queued during the processing of the queue will be processed during the next call to processQueue
	 * 
	 */
	public void processQueue();
}
