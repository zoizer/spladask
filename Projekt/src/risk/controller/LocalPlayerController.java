package risk.controller;

import java.awt.Point;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.LclAttackFromEvent;
import risk.event.LclStopAttackFromEvent;
import risk.event.RpcAttackZoneEvent;
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
	
	public void leftClick(Point now, Point prev) {
		int z = map.getZoneId(now);
		int x = map.getZoneId(prev);
		if (z == -1 || x == -1) return;
		if (phase == Phase.ATTACK_PHASE) {
			if (z != x) {
				if (map.getZone(x).hasOwner() && map.getZone(x).getOwner().equals(player)) { // i own source
					queueEvent(new RpcAttackZoneEvent(x, z, player));
				}
			}
			return;
		}
		
		if (z != x) return; // different zones.
		if (!map.getZone(z).hasOwner() || map.getZone(z).getOwner().equals(player)) {
			if (yourTurn) {
				if (phase == Phase.INIT_PHASE) queueEvent(new RpcUpdateZoneEvent(z, player, 1));
				if (phase == Phase.TRAIN_PHASE) queueEvent(new RpcUpdateZoneEvent(z, player, 1));
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

	public void downClick(Point p) {
		int x = map.getZoneId(p);
		if (x == -1) return;
		if (yourTurn && phase == Phase.ATTACK_PHASE && map.getZone(x).hasOwner() && map.getZone(x).getOwner().equals(player)) {
			queueEvent(new LclAttackFromEvent(x));
		}
	}
	
	public void upClick(Point p) {
		if (phase == Phase.ATTACK_PHASE) queueEvent(new LclStopAttackFromEvent());
	}
}
