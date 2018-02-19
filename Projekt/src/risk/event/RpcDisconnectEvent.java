package risk.event;

import risk.net.NetPlayer;

public class RpcDisconnectEvent extends ANetEvent {
	private static final long serialVersionUID = -5764433761607697926L;
	public final NetPlayer player;
	
	public RpcDisconnectEvent(NetPlayer p) {
		super(IEvent.EventType.RpcDisconnectEvent);
		this.player = p;
	}

}
