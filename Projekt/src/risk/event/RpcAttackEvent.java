package risk.event;

public class RpcAttackEvent extends ANetEvent {
	private static final long serialVersionUID = 251285284871148556L;

	public RpcAttackEvent() {
		super(IEvent.EventType.RpcAttackEvent);
		System.out.println("EVENT CREATED: " + toString());
	}
}
