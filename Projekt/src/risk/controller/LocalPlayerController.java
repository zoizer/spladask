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

/**
 * The LocalPlayerController is the controller which handles input from the local player
 * 
 * @author Filip Törnqvist
 * @version 2018-02-28
 *
 */
public class LocalPlayerController extends AEventSystem implements IPlayerController {
	private Map map;
	private String player;
	private boolean yourTurn;
	private Phase phase;
	
	/**
	 * Constructor
	 * 
	 * @param map The game map
	 * @param player The local player
	 */
	public LocalPlayerController(Map map, String player) {
		this.map = map;
		this.player = player;
		this.yourTurn = false;
		this.phase = Phase.ERROR_DO_NOT_USE;
		attachListeners();
	}

	/**
	 * Attaches listeners
	 */
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
	}
	
	/**
	 * Detaches listeners
	 */
	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * Called everywhere by the server to change the turn
	 * 
	 * @param ev the event which was listened to
	 */
	public void svrNextTurn(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrNextTurnEvent);
		SvrNextTurnEvent e = (SvrNextTurnEvent) ev;
		
		this.phase = e.phase;
		if (player.equals(e.playersTurn.name)) yourTurn = true;
		else yourTurn = false;
	}
	
	/**
	 * Interprets a left click on the map, may send events as a result
	 * 
	 * @param now the point the mouse up happened
	 * @param prev the point the mouse down happened
	 */
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
	
	/**
	 * Interprets a right click on the map, may send events as a result
	 * NO IMPL.
	 * 
	 * @param p the point the mouse up happened
	 * @param p2 the point the mouse down happened
	 */
	public void rightClick(Point p, Point p2) {
		int z = map.getZoneId(p);
		int x = map.getZoneId(p2);
		if (z != x) return; // miss
		if (z != -1 && yourTurn) {
		//	queueEvent(new LclActionEvent(z));
		}
	}

	/**
	 * Interprets a left mouse btn down on the map, may send events as a result
	 * 
	 * @param p point at mouse down
	 */
	public void downClick(Point p) {
		int x = map.getZoneId(p);
		if (x == -1) return;
		if (yourTurn && phase == Phase.ATTACK_PHASE && map.getZone(x).hasOwner() && map.getZone(x).getOwner().equals(player)) {
			queueEvent(new LclAttackFromEvent(x));
		}
	}
	
	/**
	 * Interprets a left mouse btn up on the map, may send events as a result
	 * 
	 * @param p point at mouse up
	 */
	public void upClick(Point p) {
		if (phase == Phase.ATTACK_PHASE) queueEvent(new LclStopAttackFromEvent());
	}

	/**
	 * no-op, nothing to destroy.
	 */
	@Override
	public void destroy() {
		
	}
}
