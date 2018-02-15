package risk.event;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import risk.util.Delegate;
import risk.util.ErrorHandler;

/**
 * EventManager a allows for a simple way to handle events.
 * 
 * @author 		Filip Törnqvist
 * @version 	20/2
 */
public class EventManager implements IEventManager {
	protected static EventManager eventManager = null;
	private Map<IEvent.EventType, List<Delegate>> listeners;
	private List<IEvent>[] eventQueues;
	private AtomicInteger writingQueue;
	private Object writeLock;
	
	@SuppressWarnings("unchecked") // might be an error.
	protected EventManager() {
		listeners = new ConcurrentHashMap<IEvent.EventType, List<Delegate>>();
		writingQueue.set(0);
		eventQueues = new List[2];
		eventQueues[0] = Collections.synchronizedList(new ArrayList<IEvent>()); 
		eventQueues[1] = Collections.synchronizedList(new ArrayList<IEvent>()); 
		writeLock = new Object();
	}
	
	public static final void create() {
		ErrorHandler.ASSERT(eventManager == null);
		eventManager = new EventManager();
	}
	
	public static final EventManager get() {
		ErrorHandler.ASSERT(eventManager != null);
		return eventManager;
	}
	
	public void attachListener(Delegate listener, IEvent.EventType eventType) {
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

	public void detachListener(Delegate listener, IEvent.EventType eventType) {
		List<Delegate> delegates = listeners.get(eventType);
		if (delegates != null && delegates.remove(listener)) {
			listeners.replace(eventType, delegates);
		} else ErrorHandler.WARNING("Tried to unregister a non-existing listener of type: " + eventType);
	}

	public void queueEvent(IEvent event) {
		synchronized(writeLock) {
			eventQueues[writingQueue.get()].add(event); // NOT THREAD SAFE.
		}
	}

	public void triggerQueue() {
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
					for(Delegate d : delegates) d.Execute(new Object[] {event});
				}
			}

			eventQueues[oldQueue].remove(0);
		}
	}
}
