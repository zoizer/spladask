package risk.event;

import risk.util.Delegate;

public interface IEventManager {
	public abstract void AttachListener(Delegate eventdel, int eventType);
	public abstract void DetachListener(Delegate eventdel, int eventType);
	public abstract void TriggerEvent(IEvent event);
	public abstract void QueueEvent(IEvent event); // remember thread safety.
	public abstract void AbortLastEventOfType(int eventType);
	public abstract void AbortAllOfEvent(int eventType);
	public abstract void Update(int time);
}
