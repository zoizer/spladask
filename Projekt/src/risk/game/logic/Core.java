package risk.game.logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import risk.gameview.*;
import risk.event.EventManager;
import risk.event.IEvent;
import risk.event.RiskGameEvent;
import risk.event.RiskZoneEvent;
import risk.game.*;

/**
 * Core is the game core, contains standard game logic functions.
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	04/03
 */
public final class Core implements Serializable {
	private static final long serialVersionUID = -3048794182105208641L;
	private static Core core = new Core();
	public static Core get() { return core; }
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
	
	public int GetNextActiveView() {
		if(views.containsKey(activeGameView + 1)) return activeGameView + 1;
		return 0;
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
	
/////// GeneralGameEvents
	@SuppressWarnings("unchecked")
	public void LoadMap(IEvent event) { // THIS IS THE FUNCTION THAT STARTS A NEW GAME
		try {
			FileInputStream fileIn = new FileInputStream(event.ToString() + ".dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Core.get().InitZones(((HashMap<Integer, Zone>)in.readObject()));
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			// SHOULD GIVE WARNING HERE THAT MAP WASNT FOUND.
		}

		// Player always exists
		Core.get().AttachGameView(new PlayerGameView(1));
		Core.get().AttachGameView(new PlayerGameView(2));
		if(Core.get().SetActiveView(1)) System.out.println("Could set active view.");
		else System.out.println("Could not set active view.");
	}
	
	public void SelectZone(IEvent event) {
		RiskZoneEvent e = ((RiskZoneEvent)event);
		Core.get().SelectZone(e.GetDst());
		System.out.println("Selected Zone: " + e.GetDst());
	}
	
	
	public void TryEndTurn(IEvent event) {
		if(Core.get().GetActiveView() instanceof PlayerGameView) { // it is the players turn!
			EventManager.get().queueEvent(new RiskGameEvent(0.0f, RiskGameEvent.EVENT_NEW_TURN, "")); // IF OVER INTERNET, SEND NEW TURN MESSAGE HERE.
		}
	}
}
