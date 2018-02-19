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
		LclStartGameEvent,				// 
		LclStartGameSentEvent,			// 
		LclHostGameEvent,				// Sent by Model to server to start listening for clients.
		LclJoinGameEvent,				// Sent to server in order to join.
		RpcStartGameEvent,				// 
		SvrStartGameEvent,				// 
		LclKillNetEvent,				// Used to kill all net classes.
		LclStartGameHostEvent,			// Used to tell hosting server to stop searching for players and start.
		LclServerHostStartGameEvent,	// Sent by Host Server before SvrStartGameEvent is sent, used to initialize some server specific objects.
		
		RpcConnectEvent,				// Sent by client player to join.
		RpcDisconnectEvent,				// Sent by client player to leave.
		
		
		LclSelectEvent, 
		
		LclActionEvent,
		RpcAttackEvent,
		SvrAttackEvent,
		
		LclGenerateMap,
	}
	
	public String toString();
	public EventType getEventType();
}