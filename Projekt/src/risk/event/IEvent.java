package risk.event;

/**
 * Base Event
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	11/02
 */


public interface IEvent {
	public String toString();
	public EventType getEventType();
}