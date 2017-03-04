package risk.general.event;

import risk.general.util.Delegate;

/**
 * IEventManager is the base for a event manager.
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	11/02
 */
public interface IEventManager {
	public abstract void AttachListener(Delegate eventdel, int eventType);
	public abstract void DetachListener(Delegate eventdel, int eventType);
	public abstract void TriggerEvent(IEvent event);
	public abstract void QueueEvent(IEvent event); // remember thread safety.
	public abstract void AbortLastEventOfType(int eventType);
	public abstract void AbortAllOfEvent(int eventType);
	public abstract void Update();
}
