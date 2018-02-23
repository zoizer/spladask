package risk.event;

public class LclStartGameEvent extends AEvent {
	private static final long serialVersionUID = 5769059546842263426L;
	public final String mapName;
	public final String player;
	public final String hostAddr;
	public final boolean host;
	public final boolean multiplayer;
	public static final int hostPort = 24446;
	
	public LclStartGameEvent(String mapName, String player, String hostAddr, boolean host, boolean multiplayer) {
		super(EventType.LclStartGameEvent);
		this.mapName = mapName;
		this.player = player;
		this.hostAddr = hostAddr;
		this.host = host;
		this.multiplayer = multiplayer;
		System.out.println("EVENT CREATED: " + toString());
	}
	
	@Override
	public String toString() {
		return  this.getClass().getSimpleName() + " Map: " + mapName + ", Player: " + player + ", Addr: " + hostAddr + ", Host: " + host + ", Multiplayer: " + multiplayer;
	}
}
