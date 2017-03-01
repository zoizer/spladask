package risk.game.logic;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import risk.gameview.*;
import risk.game.*;

public final class Core {
	private static Core core = new Core();
	public static Core Get() { return core; }
	private Core() {
		views = new HashMap<Integer, GameView>();
		zones = new HashMap<Integer, Zone>();
	}
	
	//////// Views
	private Map<Integer, GameView> views;
	private int activeGameView = 0;
	
	public int AttachGameView(GameView view) {
		int rtv = ViewCount();
		views.put(rtv, view);
		return rtv;
	}
	
	public GameView FindView(int id) {
		return views.get(id);
	}
	
	public int ViewCount() { return views.size(); }
	
	// May return null.
	public GameView GetActiveView() {
		return views.get(activeGameView);
	}
	
	public boolean SetActiveView(int id) {
		if(views.containsKey(id)) {
			activeGameView = id;
			return true;
		} else return false;
	}

	//////// Zones
	private Map<Integer, Zone> zones;
	private int selectedZone = 0;
	
	public Zone GetZone(int id) {
		return zones.get(id);
	}
	
	public int GetSelectedZoneID() {
		return selectedZone;
	}
	
	public void SelectZone(int id) {
		selectedZone = id;
	}
	
	public boolean ZoneExists(int id) {
		return zones.containsKey(id);
	}
	
	public void InitZones(HashMap<Integer, Zone> map) {
		zones = map;
	}
	
	
}
