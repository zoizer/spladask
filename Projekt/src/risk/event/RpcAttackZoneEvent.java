package risk.event;

public class RpcAttackZoneEvent extends ANetEvent {
	private static final long serialVersionUID = -6334244161417816924L;
	public final int fromid;
	public final int toid;
	public final String player;
	public RpcAttackZoneEvent(int from, int to, String player) {
		super(EventType.RpcAttackZoneEvent);
		fromid = from;
		toid = to;
		this.player = player;
		System.out.println("EVENT CREATED: " + toString());
	}
	
}
