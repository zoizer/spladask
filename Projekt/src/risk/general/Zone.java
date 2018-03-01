package risk.general;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.List;

import risk.util.ErrorHandler;

/**
 * The zone class represents one game zone.
 * Knows about the name, area, outline, neighbours, production, owner and army of the zone.
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class Zone implements Serializable, Cloneable { // has no ID, the id is the location in the vector in map.
	private static final long serialVersionUID = -6371719250707334678L;
	private final String name;
	private final Polygon area;
	private final Rectangle outline;
	private final List<Integer> neighbours;
	private int production;
	private String owner;
	private int army;
	
	/**
	 * 
	 * @param name The name of the zone
	 * @param area The area of the zone
	 * @param neighbours the ids of the neighbours
	 * @param production the production value of the zone
	 */
	public Zone(String name, Polygon area, List<Integer> neighbours, int production) {
		this.name = name;
		this.area = area;
		this.neighbours = neighbours;
		this.production = production;
		this.army = 0;
		owner = null;
		outline = area.getBounds();
	}
	
	/**
	 * 
	 */
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
	
	/**
	 * 
	 */
	public boolean equals(Object zone) {
		if (!(zone instanceof Zone)) return false;
		Zone z = (Zone) zone;
		return z.name.equals(name) && z.area.equals(area) && z.neighbours.equals(neighbours) && z.production == production && z.owner.equals(owner);
	}
	
	/**
	 * Check if the zone contains the point
	 * @param point Point to check if it is inside the zone
	 * @return Returns true if it is inside the zone, else false.
	 */
	public boolean contains(Point point) {
		return outline.contains(point) && area.contains(point);
	}
	
	/**
	 * Retrieves the outline of the zone
	 * @return The outline of the zone
	 */
	public Rectangle getOutline() {
		return outline;
	}
	
	/**
	 * Retrieves the neighbours of this zone
	 * @return This zones neighbours ids
	 */
	public List<Integer> getNeighbours() {
		return neighbours;
	}
	
	/**
	 * Retrieves the polygon area of this zone
	 * @return The area of the zone
	 */
	public Polygon getPolygon() {
		return area;
	}
	
	/**
	 * Gets the name of the zone
	 * @return The name of the zone
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the owner of the zone.
	 * @return The owner of the zone
	 */
	public String getOwner() {
		return owner == null ? "Rebel" : owner;
	}
	
	/**
	 * Sets the owner of the zone
	 * @param owner The new owner of the zone
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	/**
	 * Get the army count of the zone
	 * @return The army of the zone
	 */
	public int getArmy() {
		return army;
	}
	
	/**
	 * Set the army of the zone
	 * @param army The new army of the zone
	 */
	public void setArmy(int army) {
		this.army = army;
	}
	
	/**
	 * Get whether or not the zone has an owner
	 * @return true if it has an owner, false if not.
	 */
	public boolean hasOwner() {
		return owner != null;
	}
	
	/**
	 * Gets the production value of this zone
	 * @return The production value of this zone
	 */
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