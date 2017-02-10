package risk.event;

public class Event implements IEvent {
	private final float timestamp;
	
	public Event(float timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public float GetTimeStamp() {
		return timestamp;
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return null; // need static array of all events, bound to their int id. possibly a hashmap.
	}
	
}
