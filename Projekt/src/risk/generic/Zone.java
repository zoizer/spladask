package risk.generic;

import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;
import java.util.ArrayList;

public class Zone implements Serializable { // has no ID, the id is the location in the vector in map.
	private static final long serialVersionUID = -6371719250707334678L;
	private final String name;
	private final Polygon area;
	private final ArrayList<Zone> neighbours;
	private int production;
	private String owner;
	
	public Zone(String name, Polygon area, ArrayList<Zone> neighbours, int production) {
		this.name = name;
		this.area = area;
		this.neighbours = neighbours;
		this.production = production;
		owner = null;
	}
	
	public boolean equals(Object zone) {
		if (!(zone instanceof Zone)) return false;
		Zone z = (Zone) zone;
		return z.name.equals(name) && z.area.equals(area) && z.neighbours.equals(neighbours) && z.production == production && z.owner.equals(owner);
	}
	
	public boolean contains(Point point) {
		return area.contains(point);
	}
}


/*

Jag har en bra tanke.
när du startar ett game i multiplayer så får spelarna alla zoner med sitt namn på.
Om sitt namn inte matchar något får man en ny zon.
och de andra zonerna släpps till null kanske.
På så sätt behöver man inte kolla om man har rätt namn utan det fungerar ändå.

*/