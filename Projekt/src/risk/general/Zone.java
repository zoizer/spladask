package risk.general;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.List;

import risk.util.ErrorHandler;

public class Zone implements Serializable, Cloneable { // has no ID, the id is the location in the vector in map.
	private static final long serialVersionUID = -6371719250707334678L;
	private final String name;
	private final Polygon area;
	private final Rectangle outline;
	private final List<Integer> neighbours;
	private int production;
	private String owner;
	private int army;
	
	public Zone(String name, Polygon area, List<Integer> neighbours, int production) {
		this.name = name;
		this.area = area;
		this.neighbours = neighbours;
		this.production = production;
		this.army = 0;
		owner = null;
		outline = area.getBounds();
	}
	
	@Override
	public Zone clone() {
		try {
			return (Zone)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			ErrorHandler.ASSERT(false);
			return null;
		}
	}
	
	public boolean equals(Object zone) {
		if (!(zone instanceof Zone)) return false;
		Zone z = (Zone) zone;
		return z.name.equals(name) && z.area.equals(area) && z.neighbours.equals(neighbours) && z.production == production && z.owner.equals(owner);
	}
	
	public boolean contains(Point point) {
		return outline.contains(point) && area.contains(point);
	}
	
	public Rectangle getOutline() {
		return outline;
	}
	
	public List<Integer> getNeighbours() {
		return neighbours;
	}
	
	public Polygon getPolygon() {
		return area;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOwner() {
		return owner == null ? "Rebel" : owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public int getArmy() {
		return army;
	}
	
	public void setArmy(int army) {
		this.army = army;
	}
	
	public boolean hasOwner() {
		return owner != null;
	}
	
	public int getProduction() {
		return production;
	}
}


/*

Jag har en bra tanke.
när du startar ett game i multiplayer så får spelarna alla zoner med sitt namn på.
Om sitt namn inte matchar något får man en ny zon.
och de andra zonerna släpps till null kanske.
På så sätt behöver man inte kolla om man har rätt namn utan det fungerar ändå.

*/