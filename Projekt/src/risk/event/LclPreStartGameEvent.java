package risk.event;

public class LclPreStartGameEvent extends AEvent {
	private static final long serialVersionUID = -5116629377624281181L;
	public final String mapName;
	public final boolean host;
	public final boolean multiplayer;
//	public final String hostAddr;
//	public final int hostPort;
	
	public LclPreStartGameEvent(String mapName, boolean host, boolean multiplayer) {
		super(EventType.LclPreStartGameEvent);
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
