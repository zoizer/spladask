package risk.event;

/**
 * Base Event
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	11/02
 */

public interface IEvent {
	public enum EventType {	
		// Rpc - Sent to server. Should generally NOT be used locally, as the server might reject it. Rpc = Remote Procedure Call.
		// Svr - Sent from server. THIS IS LAW. Svr = Server
		// Lcl - Purely local. THIS IS LOCAL ONLY. Lcl = Local
		
		// NEVER LISTEN TO RPC! THEY CAN BE FROM OTHER PLAYERS AND YOURSELF.
		
		// UI DATA SHOULD BE KEPT IN MODEL.
		// IT BELONGS THERE.
		ERROR_DO_NOT_USE,
		LclPreStartGameEvent,			// Sent locally to before attempting to start a new game
		LclStartGameSentEvent,			// 
		LclHostGameEvent,				// Sent by Model to server to start listening for clients.
		LclJoinGameEvent,				// Sent to server in order to join.
		LclStartGameEvent,				// Sent to initiate start server / join server / create local game
		SvrStartGameEvent,				// 
		LclStartGameHostEvent,			// Used to tell hosting server to stop searching for players and start.
		LclServerHostStartGameEvent,	// Sent by Host Server before SvrStartGameEvent is sent, used to initialize some server specific objects.
		
		RpcConnectEvent,				// Sent by client player to join.
		RpcDisconnectEvent,				// Sent by client player to leave.
		
		

		SvrNextTurn,					// Sent by server to say, next player. (should probably be timed) (should contain player name and bool about init phase or game phase) (should be sent 3 sec after SvrStartGameEvent to start the game for real.) (auto sent 16 sec after SvrStartMovePhase)
	//	SvrStartTrainPhase,				// Implicit in SvrNextTurn
		RpcEndTrainPhase,				// OPTIONAL: Sent by client to end their train phase. (auto sent after 15 sec)
		SvrStartAttackPhase,			// Sent by server after some time or if client ended earlier (auto sent after 16 sec)
		RpcEndAttackPhase,				// OPTIONAL: Sent by client to end their attack phase. (auto sent after 15 sec)
		SvrStartMovePhase,				// Sent by server after some time or when client send RpcEndAttackPhase. (sent after 16 sec)
		RpcEndMovePhase,				// OPTIONAL: Sent by client to end their move phase. (auto sent after 15 sec)
		RpcEndBeginPhase,				// OPTIONAL: Sent by client to say they placed and server can send SvrNextTurn immediatly.
		
		
		LclSelectEvent, 
		
		LclActionEvent,
		RpcAttackEvent,
		SvrAttackEvent,
		
		LclGenerateMap,
	}
	
	public String toString();
	public EventType getEventType();
}