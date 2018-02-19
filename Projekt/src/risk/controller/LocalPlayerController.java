package risk.controller;

import java.awt.Point;

import risk.event.AEventSystem;
import risk.event.LclActionEvent;
import risk.event.LclSelectEvent;
import risk.general.Map;
import risk.general.Zone;

public class LocalPlayerController extends AEventSystem implements IPlayerController {
	Map map;
	String player;
	
	public LocalPlayerController(Map map, String player) {
		this.map = map;
		this.player = player;
		attachListeners();
	}
	
	@Override
	public void attachListeners() {

	}

	@Override
	public void detachListeners() {

	}
	
	public void leftClick(Point p) {
		Zone z = map.getZone(p);
		if (z != null) {
			queueEvent(new LclSelectEvent(z));
		}
	}
	
	public void rightClick(Point p) {
		Zone z = map.getZone(p);
		if (z != null) {
			queueEvent(new LclActionEvent(z));
		}
	}
}
