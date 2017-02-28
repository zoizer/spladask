package risk.game.logic;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

import risk.event.RiskGameEvent;
import risk.game.Zone;
import risk.general.event.EventManager;
import risk.general.event.IEvent;
import risk.general.util.Delegate;

public final class GeneralGameEvents {
	public GeneralGameEvents() {
		EventManager.Get().AttachListener(new Delegate(this, "LoadMap"), RiskGameEvent.EVENT_NEW_GAME);
	}

	@SuppressWarnings("unchecked")
	public static void LoadMap(IEvent event) {
		try {
			FileInputStream fileIn = new FileInputStream(event.ToString() + ".dat");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Core.Get().InitZones(((HashMap<Integer, Zone>)in.readObject()));
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			// SHOULD GIVE WARNING HERE THAT MAP WASNT FOUND.
		}
	}
}
