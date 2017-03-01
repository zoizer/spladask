package risk.game.logic;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

import risk.event.RiskZoneEvent;
import risk.game.Zone;
import risk.gameview.GameView;
import risk.gameview.NetworkGameView;
import risk.gameview.PlayerGameView;
import risk.general.event.IEvent;

public final class GeneralGameEvents {
	private GeneralGameEvents() {}

	private static GeneralGameEvents gge = new GeneralGameEvents();
	public static GeneralGameEvents Get() {
		return gge;
	}
	
	

/////// GeneralGameEvents
	@SuppressWarnings("unchecked")
	public void LoadMap(IEvent event) { // THIS IS THE FUNCTION THAT STARTS A NEW GAME
		try {
			FileInputStream fileIn = new FileInputStream(event.ToString() + ".dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Core.Get().InitZones(((HashMap<Integer, Zone>)in.readObject()));
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			// SHOULD GIVE WARNING HERE THAT MAP WASNT FOUND.
		}

		// Player always exists
		Core.Get().AttachGameView(new NetworkGameView(2));
		Core.Get().SetActiveView(1);
	}
	
	public void SelectZone(IEvent event) {
		GameView gv = Core.Get().GetActiveView();
		RiskZoneEvent e = ((RiskZoneEvent)event);
		gv.SetSelectedZoneID(e.GetDst());
		System.out.println("Selected Zone: " + e.GetDst());
	}
}
