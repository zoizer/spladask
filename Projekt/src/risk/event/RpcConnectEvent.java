package risk.event;

import risk.net.NetPlayer;

public class RpcConnectEvent extends ANetEvent {
	private static final long serialVersionUID = 5906433813262202491L;
	public final NetPlayer player;
	
	public RpcConnectEvent(NetPlayer p) {
		super(IEvent.EventType.RpcConnectEvent);
		this.player = p;
	}
	
	
}
