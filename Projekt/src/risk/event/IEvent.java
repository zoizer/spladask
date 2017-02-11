package risk.event;

/*public abstract class Event {
	// see EventManager.h 
	// page 309
	
	
}*/

public interface IEvent {
	public float GetTimeStamp();
	public int GetEventType();
	public String ToString();
}