package risk.net;

import java.io.Serializable;

/**
 * This class represents a network player, containing some information about their role in the connection
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 *
 */
public class NetPlayer implements Serializable {
	private static final long serialVersionUID = 2979545678167362364L;
	
	/**
	 * Name of the player
	 */
	public final String name;
	
	/**
	 * Role of the player
	 */
	public final boolean host;
	
	/**
	 * 
	 * @param name Name of the player
	 * @param host Role of the player
	 */
	public NetPlayer(String name, boolean host) {
		this.name = name;
		this.host = host;
	}
}