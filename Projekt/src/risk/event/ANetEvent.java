package risk.event;

import java.io.Serializable;

/**
 * ANetEvent is the base for all events which should be sent accross the network.
 * Not strictly necessary but still there for clarity.
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public abstract class ANetEvent extends AEvent implements Serializable {
	private static final long serialVersionUID = -4632366655812185878L;
	
	/**
	 * Constructor
	 * 
	 * @param e EventType of Impl.
	 */
	public ANetEvent(EventType e) {
		super(e);
	}
}
