package risk.event;

public class SvrAttackEvent extends ANetEvent {
	private static final long serialVersionUID = 7721204788687252074L;
	
	public SvrAttackEvent() {
		super(EventType.SvrAttackEvent);
		System.out.println("EVENT CREATED: " + toString());
	}
	
}
