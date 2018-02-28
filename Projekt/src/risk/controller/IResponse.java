package risk.controller;

import risk.event.IEvent;

/**
 * IResponse is meant to allow parts of the program which generally aren't allowed to send events to do just that.
 * Useful for when certain input events are chained together.
 * 
 * @author Filip Törnqvist
 * @version 2018-02-28
 */
public interface IResponse {
	
	/**
	 * Respond to events by calling this function.
	 * 
	 * @param e The event which the response belongs to
	 * @param s The response
	 */
	public void respond(IEvent e, String s);
}
