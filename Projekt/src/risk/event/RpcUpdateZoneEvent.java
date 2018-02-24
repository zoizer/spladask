package risk.event;

public class RpcUpdateZoneEvent extends ANetEvent {
	private static final long serialVersionUID = 2190857037639721866L;
	public final int zoneId;
	public final String player;
	public final int unitChange;
	
	public RpcUpdateZoneEvent(int zoneId, String player, int unitChange) {
		super(EventType.RpcUpdateZoneEvent);
		this.zoneId = zoneId;
		this.player = player;
		this.unitChange = unitChange;
		System.out.println("EVENT CREATED: " + toString());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " Player: " + player + " trained 1x unit at " + zoneId + " (id)";
	}
}
