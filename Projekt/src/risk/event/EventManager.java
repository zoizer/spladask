package risk.event;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import risk.util.Delegate;
import risk.util.ErrorHandler;

/**
 * EventManager a allows for a simple way to handle events.
 * 
 * @author 		Filip Törnqvist
 * @version 	20/2
 */
public class EventManager {
	protected static EventManager eventManager = null;
	private Map<Integer, List<Delegate>> listeners; // Integer is EventType, ArrayList<Delegate> is listeners.
	private EventQueue[] eventQueues; // REMEMBER THIS IS NOT SYNCHRONIZED LIKE BEFORE.
	private final int queueCount;
	private int writingQueue; // also know as active queue, but writing queue is more clear, since the queue you're reading from (and clearing) could also be called active.
	
	public EventManager() {
		listeners = Collections.synchronizedMap(new ConcurrentHashMap<Integer, List<Delegate>>());
		queueCount = 2;
		writingQueue = 0;
		eventQueues = new EventQueue[queueCount];
		eventQueues[0] = new EventQueue(); 
		eventQueues[1] = new EventQueue();
	}
	
	public static final void CreateGlobalEventManager() {
		ErrorHandler.ASSERT(eventManager == null);
		eventManager = new EventManager();
	}
	
	public static final EventManager Get() {
		ErrorHandler.ASSERT(eventManager != null);
		return eventManager;
	}
	
	public void AttachListener(Delegate listener, int eventType) {
		synchronized(listeners) {
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
	}

	@Deprecated // ERROR, i think. listener.remove(List<Deletage>) seems very wrong.
	public void DetachListener(Delegate listener, int eventType) {
		synchronized(listeners) {
			List<Delegate> delegates = listeners.get(eventType);
			if(delegates == null || !delegates.remove(listener)) { // list of listeners not found
				ErrorHandler.WARNING("Tried to unregister a non-existing listener of type: " + eventType);
				return;
			} else if(delegates.isEmpty()) listeners.remove(delegates); // SUCCESS. clear up memory if empty.
		}
	}

	public void TriggerEvent(IEvent event) {
		boolean wasHandled = false;
		synchronized(listeners) {
			List<Delegate> delegates = listeners.get(event.GetEventType());
			if(delegates != null) {
				for(Delegate d : delegates) {
					d.Execute(new Object[] {event});
					wasHandled = true;
				}
			}
		}
		
		if(!wasHandled) ErrorHandler.WARNING("Forced event trigger wasnt handled. EventType: " + event.GetEventType());
	}

	public void QueueEvent(IEvent event) {
		synchronized(eventQueues[writingQueue]) {
			eventQueues[writingQueue].add(event);
		}
	}

	public void AbortLastEventOfType(int eventType) {
		synchronized(eventQueues[writingQueue]) {
			ListIterator<IEvent> li = eventQueues[writingQueue].listIterator(eventQueues[writingQueue].size());
			while(li.hasPrevious()) {
				IEvent e = li.previous();
				if(e.GetEventType() == eventType) {
					li.remove();
					return;
				}
			}
		}
		
		ErrorHandler.WARNING("Tried to abort last event of type: " + eventType + " but none was found.");
	}

	public void AbortAllOfEvent(int eventType) {
		synchronized(eventQueues[writingQueue]) {
			ListIterator<IEvent> it = eventQueues[writingQueue].listIterator();
			while(it.hasNext()) {
				IEvent event = it.next();
				if(event.GetEventType() == eventType) it.remove();
			}
		}
	}

	public void Update() {
		final int updateQueue = writingQueue;
		if(writingQueue == (queueCount - 1)) writingQueue = 0;
		else ++writingQueue;
		
		synchronized(eventQueues[updateQueue]) {
			ListIterator<IEvent> it = eventQueues[updateQueue].listIterator();
			while(it.hasNext()) {
				IEvent event = it.next();
				
				// Execute the events.
				synchronized(listeners) {
					List<Delegate> delegates = listeners.get(event.GetEventType());
					if(delegates != null) 
						for(Delegate d : delegates) 
							d.Execute(new Object[] {event});
				}
				
				it.remove();
			}
		}
	}
}
