package risk.event;

public class RpcTrainEvent extends ANetEvent {
	private static final long serialVersionUID = 2190857037639721866L;
	public final int zoneId;
	public final String player;
	
	public RpcTrainEvent(int zoneId, String player) {
		super(EventType.RpcTrainEvent);
		this.zoneId = zoneId;
		this.player = player;
		System.out.println("EVENT CREATED: " + toString());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Player: " + player + " trained 1x unit at " + zoneId + " (id)";
	}
}
