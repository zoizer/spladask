package risk.event;

import java.util.List;

import risk.net.NetPlayer;

public class LclHostGameEvent extends AEvent {
	List<NetPlayer> playerList;
	
	public LclHostGameEvent(List<NetPlayer> playerList) {
		super(IEvent.EventType.LclHostGameEvent);
		this.playerList = playerList;
	}
}
