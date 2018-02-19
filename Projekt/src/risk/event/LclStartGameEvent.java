package risk.event;

public class LclStartGameEvent extends AEvent {
	public final String mapName;
	public final boolean host;
	public final boolean multiplayer;
//	public final String hostAddr;
//	public final int hostPort;
	
	public LclStartGameEvent(String mapName, boolean host, boolean multiplayer) {
		super(IEvent.EventType.LclStartGameEvent);
		this.mapName = mapName;
		this.host = host;
		this.multiplayer = multiplayer;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return  this.getClass().getSimpleName() + " Map: " + mapName + ", Host: " + host + ", Multiplayer: " + multiplayer;
	}
}
