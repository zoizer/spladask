package risk.event;

public class LclStartGameHostEvent extends AEvent {
	private static final long serialVersionUID = 9099160062546885887L;

	public LclStartGameHostEvent() {
		super(EventType.LclStartGameHostEvent);
		System.out.println("EVENT CREATED: " + toString());
	}

}
