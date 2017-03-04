package risk.general.event;

/**
 * Base Event
 * 
 * 
 * @author 		Filip T�rnqvist
 * @version 	11/02
 */

public interface IEvent {
	public float GetTimeStamp();
	public int GetEventType();
	public String ToString();
}