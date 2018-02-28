package risk.event;

/**
 * Used to tell hosting server to stop searching for players and start.
 * (Sent by server locally)
 * 
 * @see EventType#LclStartGameHostEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class LclStartGameHostEvent extends AEvent {
	private static final long serialVersionUID = 9099160062546885887L;

	/**
	 * 
	 */
	public LclStartGameHostEvent() {
		super(EventType.LclStartGameHostEvent);
		System.out.println("EVENT CREATED: " + toString());
	}

}
