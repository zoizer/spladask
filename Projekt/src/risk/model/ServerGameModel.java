package risk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import risk.event.AEventSystem;
import risk.event.EventType;
import risk.event.IEvent;
import risk.event.RpcAttackZoneEvent;
import risk.event.RpcUpdateZoneEvent;
import risk.event.SvrAttackZoneEvent;
import risk.event.SvrNextTurnEvent;
import risk.event.SvrUpdateZoneEvent;
import risk.general.Map;
import risk.general.Zone;
import risk.net.NetPlayer;
import risk.util.Delegate;
import risk.util.ErrorHandler;
import risk.util.TimedEvent;

/**
 * The server game model is the implementation of the actual game logic.
 * Knows about the the entire game
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public class ServerGameModel extends AEventSystem implements IGameModel {
	private Map map;
	private List<NetPlayer> players; // should not change anymore.
	private int activePlayer;
	private Phase phase;
	private final int startingStrength;
	private List<Integer> trainableUnits;
	private List<Integer> trainableUnitsMax;
	private Random rand;
	
	/**
	 * 
	 * @param map The map which will be played on
	 * @param players The players
	 * @param startingStrength The starting strength
	 */
	public ServerGameModel(Map map, List<NetPlayer> players, int startingStrength) {
		this.map = map;
		this.players = players;
		this.activePlayer = 0;
		this.phase = Phase.ERROR_DO_NOT_USE;
		this.startingStrength = startingStrength;
		trainableUnits = new ArrayList<Integer>();
		trainableUnitsMax = new ArrayList<Integer>();
		rand = new Random();
		for (int i = 0; i < players.size(); i++)
			trainableUnits.add(this.startingStrength);
		
		for (int i = 0; i < players.size(); i++)
			trainableUnitsMax.add(0);
		
		attachListeners();

		IEvent ev = new SvrNextTurnEvent(players.get(0), Phase.INIT_PHASE);
		new TimedEvent(1, ev, EventType.ERROR_DO_NOT_USE);
	}
	
	/*
	 * (non-Javadoc)
	 * @see risk.event.IEventSystem#attachListeners()
	 */
	@Override
	public void attachListeners() {
		attachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
		attachListener(new Delegate(this, "rpcUpdateZone"), EventType.RpcUpdateZoneEvent);
		attachListener(new Delegate(this, "rpcAttackZone"), EventType.RpcAttackZoneEvent);
		
	}

	/*
	 * (non-Javadoc)
	 * @see risk.event.IEventSystem#detachListeners()
	 */
	@Override
	public void detachListeners() {
		detachListener(new Delegate(this, "svrNextTurn"), EventType.SvrNextTurnEvent);
		detachListener(new Delegate(this, "rpcUpdateZone"), EventType.RpcUpdateZoneEvent);
		detachListener(new Delegate(this, "rpcAttackZone"), EventType.RpcAttackZoneEvent);
	}

	/*
	 * (non-Javadoc)
	 * @see risk.util.IDestroyable#destroy()
	 */
	@Override
	public void destroy() {
		detachListeners();
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * This function will handle the changing of game phases
	 * 
	 * @param ev the event which was listened to
	 */
	public void svrNextTurn(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof SvrNextTurnEvent);
		SvrNextTurnEvent e = (SvrNextTurnEvent) ev;
		phase = e.phase;
		activePlayer = players.indexOf(e.playersTurn);
	}
	
	/**
	 * Retrieves the index of the next player
	 * @return The next players index
	 */
	private int getNextPlayerIndex() {
		int tmp = activePlayer + 1;
		if (tmp == players.size()) return 0;
		return tmp;
	}
	
	/**
	 * Retrieves the index of a specific player
	 * @param name Name of the player
	 * @return Index of the player, will be -1 if the player doesnt exists.
	 */
	private int getIndexOfPlayer(String name) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).name.equals(name)) return i;
		}
		
		return -1;
	}
	
	/**
	 * Determies if someone won and returns the name of that player
	 * @return Name of the winner or null if there is none
	 */
	private String whoWon() {
		if (map.getZoneCount() == 0 || !map.getZone(0).hasOwner()) return null;
		String winner = map.getZone(0).getOwner();
		
		for (int i = 1; i < map.getZoneCount(); i++) {
			if (map.getZone(i).hasOwner()) { // has owner? then we can check if its the same as winner.
				if (map.getZone(i).getOwner().equals(winner)) {
					// is same? then check next.
				} else return null; // not same? then return null.
			} else return null; // no owner, no winner.
		}
		
		return winner;
	}
	
	/**
	 * Updates the phase if able to
	 * @return The (potentially updated) phase
	 */
	private Phase TryUpdatePhase() {
		
		if (phase == Phase.INIT_PHASE) {
			for (Integer e : trainableUnits) if (e != 0) return phase; // no change
			phase = Phase.TRAIN_PHASE; // update if no 
			
			for (int i = 0; i < trainableUnitsMax.size(); i++) {
				trainableUnits.set(i, trainableUnitsMax.get(i));
			}
			
			return phase;
		} else if (phase == Phase.TRAIN_PHASE) {
			// Can we go to the next phase?
			boolean proceed = true;
			for (int i = 0; i < trainableUnits.size(); i++) {
				if (trainableUnits.get(i) > 0) proceed = false; // everyone has placed their units.
			}
			
			if (proceed) {
				phase = Phase.ATTACK_PHASE;
			}
			return phase;
		} else if (phase == Phase.ATTACK_PHASE) {
			
			
			if (getNextPlayerIndex() != 0) { // phase is not over.
				
			} else { // phase is over
				// if attack phase done.
				for (int i = 0; i < trainableUnitsMax.size(); i++) {
					trainableUnits.set(i, trainableUnitsMax.get(i));
				}
				
				phase = Phase.TRAIN_PHASE;
			}
			
			return phase;
		}
		
		return Phase.ERROR_DO_NOT_USE;
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * This function will handle zone updates based on the current phase
	 * 
	 * @param ev the event which was listened to
	 */
	public void rpcUpdateZone(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof RpcUpdateZoneEvent);
		RpcUpdateZoneEvent e = (RpcUpdateZoneEvent) ev;
		
		if (phase == Phase.INIT_PHASE) {
			if (e.player.equals(players.get(activePlayer).name)) {
				Zone z = map.getZone(e.zoneId).clone();
				if (!z.hasOwner() || (z.getOwner().equals(e.player))) {

					int max = trainableUnits.get(activePlayer);
					if ((max - e.unitChange) < 0) return; // cant train that many units.
					
					trainableUnits.set(activePlayer, max - e.unitChange);
					
					if (z.hasOwner()) {
						int x = getIndexOfPlayer(z.getOwner());
						ErrorHandler.ASSERT(x != -1);
						trainableUnitsMax.set(x, trainableUnitsMax.get(x) - z.getProduction()); // remove old owner.
					}
					
					int x = getIndexOfPlayer(e.player);
					ErrorHandler.ASSERT(x != -1);
					trainableUnitsMax.set(x, trainableUnitsMax.get(x) + z.getProduction()); // add new owner.
					// net difference 0 if both are some, obviously terrible performance but idc for this project.
					
					z.setOwner(e.player);
					z.setArmy(z.getArmy() + e.unitChange);
					queueEvent(new SvrUpdateZoneEvent(z, e.zoneId, phase));
					queueEvent(new SvrNextTurnEvent(players.get(getNextPlayerIndex()), TryUpdatePhase()));
				}
			}
		} else if (phase == Phase.TRAIN_PHASE) {
			if (e.player.equals(players.get(activePlayer).name)) {
				Zone z = map.getZone(e.zoneId).clone();
				if (z.hasOwner() && (z.getOwner().equals(e.player))) {

					int max = trainableUnits.get(activePlayer);
					if ((max - e.unitChange) < 0) return; // cant train that many units.
					
					trainableUnits.set(activePlayer, max - e.unitChange);
					z.setArmy(z.getArmy() + e.unitChange);
					queueEvent(new SvrUpdateZoneEvent(z, e.zoneId, phase));
					
					// ensure all units are placed by this player before next turn.
					if (trainableUnits.get(activePlayer) <= 0) queueEvent(new SvrNextTurnEvent(players.get(getNextPlayerIndex()), TryUpdatePhase()));
				}
			}
		}
		
	}
	
	/**
	 * This is an Event Response function, meaning, you are not intended to call this, only the EventManager should call this function.
	 * This function will handle an attack or move event
	 * 
	 * @param ev the event which was listened to
	 */
	public void rpcAttackZone(IEvent ev) {
		ErrorHandler.ASSERT(ev instanceof RpcAttackZoneEvent);
		RpcAttackZoneEvent e = (RpcAttackZoneEvent) ev;
		ErrorHandler.ASSERT(e.fromid != -1 && e.toid != -1);
		
		if (phase != Phase.ATTACK_PHASE) return;
		if (players.get(activePlayer).name.equals(e.player)) {
			Zone dst = map.getZone(e.toid).clone();
			Zone src = map.getZone(e.fromid).clone();
			int force = 0;
			
			if (!src.getNeighbours().contains(e.toid)) return;
			
			if (src.hasOwner() && src.getOwner().equals(e.player) && src.getArmy() > 1) { // I own the src! and I can attack from there.
				if (dst.hasOwner() && dst.getOwner().equals(e.player)) { // I own the dst! meaning, I WILL MOVE MY ARMY THERE INSTEAD OF ATTACK
					force = src.getArmy() - 1;
					src.setArmy(1);
					dst.setArmy(dst.getArmy() + force);
					
					
					
					queueEvent(new SvrNextTurnEvent(players.get(getNextPlayerIndex()), TryUpdatePhase()));
					queueEvent(new SvrAttackZoneEvent(src, dst, false, e.fromid, e.toid, 0, 0, null, TryUpdatePhase()));
				} else { // atttack!!!!
					force = src.getArmy() - 1;
					src.setArmy(1);
					int defend = dst.getArmy();
					final int preDefend = defend;
					final int preForce = force;
					
					while(force > 0 && defend > 0) {
						int aroll = rand.nextInt(force);
						int droll = rand.nextInt(defend);
						if (aroll > droll) defend -= 1;
						else force -= 1;
					}
					
					if (force > 0) { // success
						if (dst.hasOwner()) {
							int i = getIndexOfPlayer(dst.getOwner());
							trainableUnitsMax.set(i, trainableUnitsMax.get(i) - dst.getProduction());
						}

						int i = getIndexOfPlayer(src.getOwner());
						trainableUnitsMax.set(i, trainableUnitsMax.get(i) + src.getProduction());
						
						dst.setOwner(src.getOwner());
						dst.setArmy(force);
					} else { // fail
						dst.setArmy(defend);
					}
					String winner = whoWon();
					
					queueEvent(new SvrNextTurnEvent(players.get(getNextPlayerIndex()), TryUpdatePhase()));
					queueEvent(new SvrAttackZoneEvent(src, dst, true, e.fromid, e.toid, preForce - force, preDefend - defend,  winner, TryUpdatePhase()));
				}
			} else {
				boolean unableToAttack = true; // if the player cant attack cause they have no soldiers! next players turn!
				for (int i = 0; i < map.getZoneCount(); i++) {
					if (map.getZone(i).hasOwner() && map.getZone(i).getOwner().equals(e.player)) {
						if (map.getZone(i).getArmy() > 1) {
							unableToAttack = false;
							break;
						}
					}
				}
				
				if (unableToAttack) queueEvent(new SvrNextTurnEvent(players.get(getNextPlayerIndex()), TryUpdatePhase()));
			}
		}
	}
	
}
