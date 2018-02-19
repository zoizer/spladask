package risk.net;

import java.io.Serializable;

public class NetPlayer implements Serializable {
	private static final long serialVersionUID = 2979545678167362364L;
	public final String name;
	public final boolean host;
	
	public NetPlayer(String name, boolean host) {
		this.name = name;
		this.host = host;
	}
}