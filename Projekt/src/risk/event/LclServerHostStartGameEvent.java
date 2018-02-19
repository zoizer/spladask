package risk.event;

import java.util.List;

import risk.net.ServerClient;

public class LclServerHostStartGameEvent extends AEvent {
	public final List<ServerClient> remotePlayers;
	public LclServerHostStartGameEvent(List<ServerClient> remotePlayers) {
		super(IEvent.EventType.LclServerHostStartGameEvent);
		this.remotePlayers = remotePlayers;
	}

}
