package risk.event;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import risk.util.Delegate;
import risk.util.ErrorHandler;

/**
 * EventManager a allows for a simple way to handle events.
 * The registered delegates will be called at some point after an event of the desired type is posted.
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class EventManager implements IEventManager {
	protected static EventManager eventManager = null; // Singleton
	private Map<EventType, List<Delegate>> listeners;
	private List<IEvent>[] eventQueues;
	private AtomicInteger writingQueue;
	private Object writeLock;
	
	@SuppressWarnings("unchecked")
	protected EventManager() {
		listeners = new ConcurrentHashMap<EventType, List<Delegate>>();
		writingQueue = new AtomicInteger();
		writingQueue.set(0);
		eventQueues = new List[2];
		eventQueues[0] = Collections.synchronizedList(new ArrayList<IEvent>()); 
		eventQueues[1] = Collections.synchronizedList(new ArrayList<IEvent>()); 
		writeLock = new Object();
	}
	
	/**
	 * singleton create
	 */
	public static final void create() {
		ErrorHandler.ASSERT(eventManager == null);
		eventManager = new EventManager();
	}
	
	/**
	 * singleton getter
	 * @return the global EventManager
	 */
	public static final EventManager get() {
		ErrorHandler.ASSERT(eventManager != null);
		return eventManager;
	}
	
	/**
	 * Attaches a listener to be called when event of desired type is posted
	 * @param listener the delegate to be called.
	 * @param eventType the EventType the listener is listening for
	 */
	public void attachListener(Delegate listener, EventType eventType) {
		List<Delegate> delegates = listeners.get(eventType);
		if(delegates == null) { // list of listeners not found
			delegates = Collections.synchronizedList(new ArrayList<Delegate>());
			listeners.put(eventType, delegates);
		} else if(delegates.contains(listener)) { // list of listeners should now exist.
			ErrorHandler.WARNING("Double registering of listeners for eventType: " + eventType); // is the listener already registered?
			return;
		}
		
		delegates.add(listener); // listener is now added.
	}

	/**
	 * Detaches a listener
	 * (no-op when this delegate+eventType combo is unused, but there will be a warning)
	 * 
	 * @param listener the delegate which would be called.
	 * @param eventType the EventType the listener was listening for.
	 */
	public void detachListener(Delegate listener, EventType eventType) {
		List<Delegate> delegates = listeners.get(eventType);
		if (delegates != null && delegates.remove(listener)) {
			listeners.replace(eventType, delegates);
		} else ErrorHandler.WARNING("Tried to unregister a non-existing listener of type: " + eventType);
	}
	
	/**
	 * queueEvent queues an event which will be called sometime soon.
	 * 
	 * @param event The event which should be queued
	 */
	public void queueEvent(IEvent event) {
		synchronized(writeLock) {
			eventQueues[writingQueue.get()].add(event); // NOT THREAD SAFE.
		}
	}

	/**
	 * processes all events in the queue and then returns.
	 * (it is safe to add new events to the queue while this is happening)
	 */
	public void processQueue() {
		final int oldQueue;
		synchronized(writeLock) {
			oldQueue = writingQueue.get();
			if (!writingQueue.compareAndSet(1, 0)) writingQueue.set(1);
		}
		
		while(eventQueues[oldQueue].size() > 0) {
			IEvent event = eventQueues[oldQueue].get(0);

			List<Delegate> delegates = listeners.get(event.getEventType());
			if(delegates != null) {
				synchronized(delegates) { // must synchronize due to iteration.
					//for(Delegate d : delegates) 
					//	d.Execute(new Object[] {event});
					for (int i = 0; i < delegates.size(); i++)
						delegates.get(i).Execute(new Object[] {event});
				}
			}

			eventQueues[oldQueue].remove(0);
		}
	}
}
