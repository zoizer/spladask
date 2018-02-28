package risk.event;

/**
 * sent to end current game.
 * (used by any)
 * 
 * @see EventType#LclEndGameEvent
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class LclEndGameEvent extends AEvent {
	private static final long serialVersionUID = 7299317372326141415L;

	/**
	 * 
	 */
	public LclEndGameEvent() {
		super(EventType.LclEndGameEvent);
		System.out.println("EVENT CREATED: " + toString());
	}

}
