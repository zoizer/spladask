package risk.game;

import risk.game.logic.*;
import java.util.ArrayList;

public class Zone {
	private final int uniqueID;
	private final int production;
	ArrayList<Integer> neighbours;
	
	private int ownerid;
	
	public Zone(int uniqueID, int production) {
		this.uniqueID = uniqueID;
		this.production = production;
		ownerid = 0;
	}
	
	public Zone(int uniqueID, int production, int ownerID) {
		this.uniqueID = uniqueID;
		this.production = production;
		this.ownerid = ownerID;
	}
	
	public void setOwner(int ownerid) {
		this.ownerid = ownerid;
	}
	
	public int GetID() {
		return uniqueID;
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
}
