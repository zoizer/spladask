package risk.game.logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import risk.gameview.*;
import risk.game.*;

public final class Core implements Serializable {
	private static final long serialVersionUID = -3048794182105208641L;
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
		views.put(view.GetID(), view);
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
	
	public void Save(String saveName) {
		try {
        	FileOutputStream fileOut = new FileOutputStream(saveName + ".save");
        	ObjectOutputStream out = new ObjectOutputStream(fileOut);
        	out.writeObject(this);
        	out.close();
        	fileOut.close();
        } catch(Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void Load(String fileName) {
		// REMEMBER TO ALSO LOAD GRAPHICS. PROBABLY THROUGH A NEW EVENT.
		try {
			FileInputStream fileIn = new FileInputStream(fileName + ".save"); // TODO: CHECK IF THE FILENAME AUTOMATICALLY ENDS WITH .SAVE
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.Load(((Core)in.readObject()));
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void Load(Core core2) {
		this.views = core2.views;
		this.zones = core2.zones;
	}
}
