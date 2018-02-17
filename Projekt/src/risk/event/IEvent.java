package risk.event;

/**
 * Base Event
 * 
 * 
 * @author 		Filip T�rnqvist
 * @version 	11/02
 */

public interface IEvent {
	public enum EventType {	
		// Rpc - Sent to server. Should generally NOT be used locally, as the server might reject it. Rpc = Remote Procedure Call.
		// Svr - Sent from server. THIS IS LAW. Svr = Server
		// Lcl - Purely local. THIS IS LOCAL ONLY. Lcl = Local
		
		// UI DATA SHOULD BE KEPT IN MODEL.
		// IT BELONGS THERE.
		RpcPreStartGameEvent,
		SvrStartGameEvent,
		LclTrySelectEvent, 
		LclTryAttackEvent,
		RpcAttackEvent,
		SvrAttackEvent,
		LclGenerateMap,
	}
	
	public String toString();
	public EventType getEventType();
}