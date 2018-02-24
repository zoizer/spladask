package risk.controller;

import java.awt.Point;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.RpcUpdateZoneEvent;
import risk.event.SvrNextTurnEvent;
import risk.general.Map;
import risk.model.Phase;
import risk.util.Delegate;
import risk.util.ErrorHandler;

public class LocalPlayerController extends AEventSystem implements IPlayerController {
	private Map map;
	private String player;
	private boolean yourTurn;
	private Phase phase;
	
	public LocalPlayerController(Map map, String player) {
		this.map = map;
		this.player = player;
		this.yourTurn = false;
		this.phase = Phase.ERROR_DO_NOT_USE;
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
		
		this.phase = e.phase;
		if (player.equals(e.playersTurn.name)) yourTurn = true;
		else yourTurn = false;
	}
	
	public void leftClick(Point p, Point p2) {
		int z = map.getZoneId(p);
		int x = map.getZoneId(p2);
		if (z != x) return; // miss
		if (z == -1) return;
		if (!map.getZone(z).hasOwner() || map.getZone(z).getOwner().equals(player)) {
			if (yourTurn) {
				if (phase == Phase.INIT_PHASE) queueEvent(new RpcUpdateZoneEvent(z, player, 1));
				// queueEvent(new LclSelectEvent(z));
			}
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
