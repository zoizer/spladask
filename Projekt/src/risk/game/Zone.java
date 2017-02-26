package risk.game;

import risk.game.logic.*;
import java.util.ArrayList;

public class Zone {
	private final int uniqueID;
	private final int production;
	ArrayList<Integer> neighbours;
	
	private int ownerid;
	
	public Zone(int x, int y, int production) {
		//this.x = x;
		//this.y = y;
		uniqueID = 0;
		this.production = production;
		ownerid = 0;
	}
	
	public void setOwner(int ownerid) {
		this.ownerid = ownerid;
	}
	
	public int GetX() {
		//return x;
		return 0;
	}
	
	public int GetY() {
		//return y;
		return 0;
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
