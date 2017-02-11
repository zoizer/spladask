package risk.general.event;

import java.util.*;

import risk.general.util.Delegate;
import risk.general.util.ErrorHandler;

public class EventManager implements IEventManager {
	protected static EventManager eventManager = null;
	private Map<Integer, ArrayList<Delegate>> listeners; // Integer is EventType, ArrayList<Delegate> is listeners.
	private List<IEvent> eventQueue; // Event queue, containing the Event interface.
	
	public EventManager() {
		listeners = Collections.synchronizedMap(new HashMap<Integer, ArrayList<Delegate>>());
		eventQueue = Collections.synchronizedList(new ArrayList<IEvent>());
	}
	
	public static final void CreateGlobalEventManager() {
		ErrorHandler.ASSERT(eventManager == null);
		eventManager = new EventManager();
	}
	
	public static final IEventManager Get() {
		ErrorHandler.ASSERT(eventManager != null);
		return eventManager;
	}
	
	@Override
	public void AttachListener(Delegate listener, int eventType) {
		synchronized(listeners) {
			ArrayList<Delegate> delegates = listeners.get(eventType);
			if(delegates == null) { // list of listeners not found
				delegates = new ArrayList<Delegate>();
				listeners.put(eventType, delegates);
			} else if(delegates.contains(listener)) { // list of listeners should now exist.
				ErrorHandler.WARNING("Double registering of listeners for eventType: " + eventType); // is the listener already registered?
				return;
			}
			
			delegates.add(listener); // listener is now added.
		}
	}

	@Override
	public void DetachListener(Delegate listener, int eventType) {
		synchronized(listeners) {
			ArrayList<Delegate> delegates = listeners.get(eventType);
			if(delegates == null || !delegates.remove(listener)) { // list of listeners not found
				ErrorHandler.WARNING("Tried to unregister a non-existing listener of type: " + eventType);
				return;
			} else if(delegates.isEmpty()) listeners.remove(delegates); // SUCCESS. clear up memory if empty.
		}
	}

	@Override
	public void TriggerEvent(IEvent event) {
		boolean wasHandled = false;
		synchronized(listeners) {
			ArrayList<Delegate> delegates = listeners.get(event.GetEventType());
			if(delegates != null) {
				for(Delegate d : delegates) {
					d.Execute(new Object[] {event});
					wasHandled = true;
				}
			}
		}
		
		if(!wasHandled) ErrorHandler.WARNING("Forced event trigger wasnt handled. EventType: " + event.GetEventType());
	}

	@Override
	public void QueueEvent(IEvent event) {
		synchronized(eventQueue) {
			eventQueue.add(event);
		}
	}

	@Override
	public void AbortLastEventOfType(int eventType) {
		synchronized(eventQueue) {
			ListIterator<IEvent> li = eventQueue.listIterator(eventQueue.size());
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

	@Override
	public void AbortAllOfEvent(int eventType) {
		synchronized(eventQueue) {
			ListIterator<IEvent> it = eventQueue.listIterator();
			while(it.hasNext()) {
				IEvent event = it.next();
				if(event.GetEventType() == eventType) it.remove();
			}
		}
	}

	@Override
	public void Update() {
		synchronized(eventQueue) {
			ListIterator<IEvent> it = eventQueue.listIterator();
			while(it.hasNext()) {
				IEvent event = it.next();
				
				// Execute the events.
				synchronized(listeners) {
					ArrayList<Delegate> delegates = listeners.get(event.GetEventType());
					if(delegates != null) 
						for(Delegate d : delegates) 
							d.Execute(new Object[] {event});
				}
				
				it.remove();
			}
		}
	}
}
