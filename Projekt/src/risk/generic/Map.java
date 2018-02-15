package risk.generic;

import java.awt.Point;
import java.io.Serializable;
import java.util.Vector;

public class Map implements Serializable {
	private static final long serialVersionUID = 3553692940163187630L;
	private Vector<Zone> zones;
	
	public Map() {
		zones = null;
	}
	
	public void loadMap(String name) {
		// load map.
	}
	
	public void saveMap(String name) {
		// save current map.
	}
	
	public void generateMap(int setupId) {
		// create and save map.
		
	}
	
	public Zone getZone(int index) {
		return zones.get(index);
	}
	
	public Zone getZone(Point p) {
		synchronized (zones) {
			for (Zone z : zones) {
				if (z.contains(p)) return z;
			}
		}
		
		return null;
	}
}
