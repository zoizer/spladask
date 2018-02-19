package risk.event;

import risk.net.NetPlayer;

public class LclJoinGameEvent extends AEvent {
	public final NetPlayer player;
	public final String hostAddr;
	public final int port;
	
	public LclJoinGameEvent(NetPlayer player, String hostAddr, int port) {
		super(IEvent.EventType.LclJoinGameEvent);
		this.player = player;
		this.hostAddr = hostAddr;
		this.port = port;
	}

}
