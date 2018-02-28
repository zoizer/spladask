package risk.event;

import java.io.Serializable;

/**
 * Event standard implementation of IEvent.
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public abstract class AEvent implements IEvent, Serializable {
	private static final long serialVersionUID = -9142614644901196571L;
	protected final EventType type;
	
	/**
	 * Constructor
	 * 
	 * @param type The unique EventType for this specific event
	 */
	public AEvent(EventType type) {
		this.type = type;
	}
	
	/**
	 * Get name of this class impl.
	 */
	@Override
	public String toString() {
		return  this.getClass().getSimpleName();
	}
	
	/**
	 * Get EventType of this class impl.
	 */
	@Override
	public EventType getEventType() {
		return type;
	}
}
