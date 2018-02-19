package risk.event;

import risk.net.NetPlayer;

public class LclHostGameEvent extends AEvent {
	public final NetPlayer host;
	
	public LclHostGameEvent(NetPlayer host) {
		super(IEvent.EventType.LclHostGameEvent);
		this.host = host;
	}
}
