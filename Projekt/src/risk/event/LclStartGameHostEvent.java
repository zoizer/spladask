package risk.event;

public class LclStartGameHostEvent extends AEvent {
	private static final long serialVersionUID = 9099160062546885887L;

	public LclStartGameHostEvent() {
		super(IEvent.EventType.LclStartGameHostEvent);
	}

}
