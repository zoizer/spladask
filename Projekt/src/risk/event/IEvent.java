package risk.event;

/**
 * Base interface for all events
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	2017-02-11
 */
public interface IEvent {
	
	/**
	 * should return some string which identifies the event
	 * @return string representing this class
	 */
	public String toString();
	
	/**
	 * should return a unique EventType for all specific implementations
	 * @return unique EventType for all specific implementations
	 */
	public EventType getEventType();
}