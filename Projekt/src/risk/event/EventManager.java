package risk.event;

import java.util.ArrayList;
import java.util.HashMap;

import risk.util.Delegate;
import risk.util.ErrorHandler;

public class EventManager implements IEventManager {
	protected static EventManager eventManager = null;
	private HashMap<Integer, ArrayList<Delegate>> listeners; // Integer is EventType, ArrayList<Delegate> is listeners.
	private ArrayList<IEvent> eventQueue; // Event queue, containing the Event interface.
	
	public EventManager() {
		
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
	public void AttachListener(Delegate eventdel, int eventType) {
		// TODO Auto-generated method stub
		ArrayList<Delegate> delegates = listeners.get(eventType);
		if(delegates == null) {
			// add new
		} else {
			
		}
	}

	@Override
	public void DetachListener(Delegate eventdel, int eventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void TriggerEvent(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void QueueEvent(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AbortLastEventOfType(int eventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AbortAllOfEvent(int eventType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(int time) {
		// TODO Auto-generated method stub
		
	}
}
