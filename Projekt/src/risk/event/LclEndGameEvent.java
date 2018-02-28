package risk.event;

public class LclEndGameEvent extends AEvent {
	private static final long serialVersionUID = 7299317372326141415L;

	public LclEndGameEvent() {
		super(EventType.LclEndGameEvent);
		System.out.println("EVENT CREATED: " + toString());
	}

}
