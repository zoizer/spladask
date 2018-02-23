package risk.controller;

import java.awt.Point;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.LclActionEvent;
import risk.event.LclSelectEvent;
import risk.event.RpcTrainEvent;
import risk.event.SvrNextTurnEvent;
import risk.general.Map;
import risk.general.Zone;
import risk.util.Delegate;
import risk.util.ErrorHandler;

public class LocalPlayerController extends AEventSystem implements IPlayerController {
	private Map map;
	private String player;
	private boolean yourTurn;
	private boolean initPhase;
	
	public LocalPlayerController(Map map, String player) {
		this.map = map;
		this.player = player;
		this.yourTurn = false;
		this.initPhase = false;
		attachListeners();
	}
	
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
	}

	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
	}
	
	public void svrNextTurn(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrNextTurnEvent);
		SvrNextTurnEvent e = (SvrNextTurnEvent) ev;
		
		this.initPhase = e.initPhase;
		if (player.equals(e.playersTurn.name)) yourTurn = true;
		else yourTurn = false;
	}
	
	public void leftClick(Point p, Point p2) {
		int z = map.getZoneId(p);
		int x = map.getZoneId(p2);
		if (z != x) return; // miss
		if (z != -1 && yourTurn) {
			if (initPhase) queueEvent(new RpcTrainEvent(z, player));
			// queueEvent(new LclSelectEvent(z));
		}
	}
	
	public void rightClick(Point p, Point p2) {
		int z = map.getZoneId(p);
		int x = map.getZoneId(p2);
		if (z != x) return; // miss
		if (z != -1 && yourTurn) {
		//	queueEvent(new LclActionEvent(z));
		}
	}
}
