package risk.event;

/**
 * <p>All event types, each entry should represent one event. </p>
 * <p>Rpc - Sent to server. Should generally NOT be used locally, as the server might reject it. Rpc = Remote Procedure Call.</p>
 * <p>Svr - Sent from server. THIS IS LAW. Svr = Server</p>
 * <p>Lcl - Purely local. THIS IS LOCAL ONLY. Lcl = Local</p>
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public enum EventType {	
	// NEVER LISTEN TO RPC! THEY CAN BE FROM OTHER PLAYERS AND YOURSELF.
	// UI DATA SHOULD BE KEPT IN MODEL.
	// IT BELONGS THERE.
	
	// LCL
	
	/**
	 * Should be used to signal error or uninitialized.
	 */
	ERROR_DO_NOT_USE,
	
	/**
	 * Sent to create visuals of where you can attack.	
	 */
	LclAttackFromEvent,
	
	/**
	 * sent to end current game.
	 */
	LclEndGameEvent,
	
	/**
	 * sent to generate a map.
	 */
	LclGenerateMap,
	
	/**
	 * Sent by Model to server to start listening for clients.
	 */
	LclHostGameEvent,
	
	/**
	 * Sent locally before attempting to start, join or host a new game
	 */
	LclPreStartGameEvent,
	
	/**
	 * Sent by Host Server before SvrStartGameEvent is sent, used to initialize some server specific objects.
	 */
	LclServerHostStartGameEvent,
	
	/**
	 * Sent to initiate start server / join server / create local game
	 */
	LclStartGameEvent,
	
	/**
	 * Used to tell hosting server to stop searching for players and start.
	 */
	LclStartGameHostEvent,

	/**
	 * Sent to confirm game is starting
	 */
	LclStartGameSentEvent,
	
	/**
	 * Sent to stop visuals from LclAttackFromEvent.
	 */
	LclStopAttackFromEvent,
	
	
	// RPC
	
	/**
	 * Sent from any to server in order to attack
	 */
	RpcAttackZoneEvent,
	
	/**
	 * Sent by client player to join.
	 */
	RpcConnectEvent,
	
	/**
	 * Sent by client player to leave.
	 */
	RpcDisconnectEvent,
	
	/**
	 * Sent by client to attempt to modify zone
	 */
	RpcUpdateZoneEvent,
	
	
	// SVR

	/**
	 * Sent by server to confirm attack and results.
	 */
	SvrAttackZoneEvent,
	
	/**
	 * Sent by server to say, next players turn. 
	 */
	SvrNextTurnEvent,

	/**
	 * Sent by server to actually start the game.
	 */
	SvrStartGameEvent,

	/**
	 * Sent by server to modify zone.
	 */
	SvrUpdateZoneEvent,
	
	
//	SvrStartTrainPhaseEvent,// NO IMPL	// Implicit in SvrNextTurn
//	RpcEndTrainPhaseEvent,	// NO IMPL	// OPTIONAL: Sent by client to end their train phase. (auto sent after 15 sec)
//	SvrStartAttackPhaseEvent,// NO IMPL	// Sent by server after some time or if client ended earlier (auto sent after 16 sec)
//	RpcEndAttackPhaseEvent,	// NO IMPL	// OPTIONAL: Sent by client to end their attack phase. (auto sent after 15 sec)
//	SvrStartMovePhaseEvent,	// NO IMPL	// Sent by server after some time or when client send RpcEndAttackPhase. (sent after 16 sec)
//	RpcEndMovePhaseEvent,	// NO IMPL	// OPTIONAL: Sent by client to end their move phase. (auto sent after 15 sec)
//	RpcEndBeginPhaseEvent,	// NO IMPL	// OPTIONAL: Sent by client to say they placed and server can send SvrNextTurn immediatly.
}
