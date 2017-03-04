package risk.game;

import risk.game.logic.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Zone is the game version of a zone.
 * 
 * 
 * @author 		Marco Zaarour & Filip Törnqvist
 * @version 	01/03
 */
public class Zone implements Serializable {
	private static final long serialVersionUID = -645992308047933435L;
	private final int production;
	ArrayList<Integer> neighbours;
	
	private int ownerid;
	
	public Zone(int production) {
		this.production = production;
		ownerid = 0;
		neighbours = new ArrayList<Integer>();
	}
	
	public Zone(int production, int ownerID) {
		this.production = production;
		this.ownerid = ownerID;
		neighbours = new ArrayList<Integer>();
	}
	
	public void setOwner(int ownerid) {
		this.ownerid = ownerid;
	}
	
	public int GetProduction() {
		return production;
	}
	
	public int GetOwnerID() {
		return ownerid;
	}
	
	public Zone GetNeighbour(int id) {
		if(neighbours.contains(id)) {
			return Core.Get().GetZone(id);
		} else return null;
	}
	
	public ArrayList<Integer> GetNeighbours() {
		return neighbours;
	}
	
	public void AddNeighbour(int id) {
		neighbours.add(id);
	}
	
	public void AddNeighbour(int ... ids) {
		for(int i = 0; i < ids.length; i++) AddNeighbour(ids[i]);
	}
}
