package risk.controller;

import java.awt.Point;

import risk.event.AEventSystem;
import risk.generic.Map;
import risk.generic.Zone;

public class LocalPlayerController extends AEventSystem implements IPlayerController {
	Map map;
	
	@Override
	public void attachListeners() {
		// should remain empty for Controllers
	}

	@Override
	public void detachListeners() {
		// should remain empty for Controllers
	}
	
	public void leftClick(Point p) {
		Zone z = map.getZone(p);
	}
	
	public void rightClick(Point p) {
		Zone z = map.getZone(p);
	}
}
