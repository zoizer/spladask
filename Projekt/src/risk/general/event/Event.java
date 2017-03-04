package risk.general.event;

/**
 * Event standard implementation of IEvent.
 * 
 * @author 		Filip Törnqvist
 * @version 	11/02
 */
public class Event implements IEvent {
	protected final float timestamp;
	protected final int eventType;
	
	public Event(float timestamp, int eventType) {
		this.timestamp = timestamp;
		this.eventType = eventType;
	}
	
	@Override
	public float GetTimeStamp() {
		return timestamp;
	}

	@Override
	public int GetEventType() {
		// TODO Auto-generated method stub
		return eventType;
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return null; // need static array of all events, bound to their int id. possibly a hashmap.
	}
}
