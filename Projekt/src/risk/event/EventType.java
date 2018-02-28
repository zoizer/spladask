package risk.event;

/**
 * All event types, each entry should represent one event.
 * Rpc - Sent to server. Should generally NOT be used locally, as the server might reject it. Rpc = Remote Procedure Call.
 * Svr - Sent from server. THIS IS LAW. Svr = Server
 * Lcl - Purely local. THIS IS LOCAL ONLY. Lcl = Local
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public enum EventType {	
	// NEVER LISTEN TO RPC! THEY CAN BE FROM OTHER PLAYERS AND YOURSELF.
	// UI DATA SHOULD BE KEPT IN MODEL.
	// IT BELONGS THERE.
	
	/**
	 * Should be used to signal error or uninitialized.
	 */
	ERROR_DO_NOT_USE,
	
	/**
	 * Sent locally before attempting to start a new game
	 */
	LclPreStartGameEvent,
	
	/**
	 * Sent to confirm game is starting
	 */
	LclStartGameSentEvent,
	
	/**
	 * Sent by Model to server to start listening for clients.
	 */
	LclHostGameEvent,
	
	/**
	 * Sent to server in order to join.
	 */
	LclJoinGameEvent,
	
	/**
	 * Sent to initiate start server / join server / create local game
	 */
	LclStartGameEvent,
	
	/**
	 * Sent by server to actually start the game.
	 */
	SvrStartGameEvent,
	
	/**
	 * Used to tell hosting server to stop searching for players and start.
	 */
	LclStartGameHostEvent,
	
	/**
	 * Sent by Host Server before SvrStartGameEvent is sent, used to initialize some server specific objects.
	 */
	LclServerHostStartGameEvent,
	
	/**
	 * Sent by client player to join.
	 */
	RpcConnectEvent,
	
	/**
	 * Sent by client player to leave.
	 */
	RpcDisconnectEvent,
	
	/**
	 * Sent by server to say, next players turn. 
	 */
	SvrNextTurnEvent,
	
//	SvrStartTrainPhaseEvent,// NO IMPL	// Implicit in SvrNextTurn
//	RpcEndTrainPhaseEvent,	// NO IMPL	// OPTIONAL: Sent by client to end their train phase. (auto sent after 15 sec)
//	SvrStartAttackPhaseEvent,// NO IMPL	// Sent by server after some time or if client ended earlier (auto sent after 16 sec)
//	RpcEndAttackPhaseEvent,	// NO IMPL	// OPTIONAL: Sent by client to end their attack phase. (auto sent after 15 sec)
//	SvrStartMovePhaseEvent,	// NO IMPL	// Sent by server after some time or when client send RpcEndAttackPhase. (sent after 16 sec)
//	RpcEndMovePhaseEvent,	// NO IMPL	// OPTIONAL: Sent by client to end their move phase. (auto sent after 15 sec)
//	RpcEndBeginPhaseEvent,	// NO IMPL	// OPTIONAL: Sent by client to say they placed and server can send SvrNextTurn immediatly.
	
	/**
	 * Sent by client to attempt to modify zone
	 */
	RpcUpdateZoneEvent,
	
	/**
	 * Sent by server to modify zone.
	 */
	SvrUpdateZoneEvent,
	
	/**
	 * Sent from client to server in order to attack
	 */
	RpcAttackZoneEvent,
	
	/**
	 * Sent by server to confirm attack and results.
	 */
	SvrAttackZoneEvent,
	
	/**
	 * Sent to create visuals of where you can attack.	
	 */
	LclAttackFromEvent,
	
	/**
	 * Sent to stop visuals from LclAttackFromEvent.
	 */
	LclStopAttackFromEvent,
	
	/**
	 * sent to end current game.
	 */
	LclEndGameEvent,
	
	/**
	 * sent to generate a map.
	 */
	LclGenerateMap,
}
