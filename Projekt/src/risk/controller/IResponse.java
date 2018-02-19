package risk.controller;

import risk.event.IEvent;

public interface IResponse {
	public void respond(IEvent e, String s);
}
