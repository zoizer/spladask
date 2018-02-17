package risk.controller;

import java.awt.Point;

import risk.event.AEventSystem;
import risk.general.Map;
import risk.general.Zone;

public class LocalPlayerController extends AEventSystem implements IPlayerController {
	Map map;
	
	@Override
	public void attachListeners() {

	}

	@Override
	public void detachListeners() {

	}
	
	public void leftClick(Point p) {
		Zone z = map.getZone(p);
		if (z != null) {
			// send event
		}
	}
	
	public void rightClick(Point p) {
		Zone z = map.getZone(p);
		if (z != null) {
			// send event
		}
	}
}
