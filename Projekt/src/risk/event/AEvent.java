package risk.event;

import java.io.Serializable;

/**
 * Event standard implementation of IEvent.
 * 
 * @author 		Filip Törnqvist
 * @version 	11/02
 */
public abstract class AEvent implements IEvent, Serializable {
	private static final long serialVersionUID = -9142614644901196571L;
	protected final EventType type;
	
	public AEvent(EventType type) {
		this.type = type;
	}
	

	@Override
	public String toString() {
		return  this.getClass().getSimpleName();
	}
	
	@Override
	public EventType getEventType() {
		return type;
	}
}
