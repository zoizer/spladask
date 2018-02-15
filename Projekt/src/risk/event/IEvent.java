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
		
		// UI DATA SHOULD BE KEPT IN MODEL.
		// IT BELONGS THERE.
		RpcPreStartGameEvent,
		SvrStartGameEvent,
		LclClickEvent, // Local
		LclSelectEvent, // Local
		LclPreAttackEvent, // should have LOTS of events be local and have a GuiModel
		RpcAttackEvent, // should have LOTS of events be local and have a GuiModel
	}
	
	public String toString();
	public EventType getEventType();
}