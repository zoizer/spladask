package risk.controller;

import risk.event.IEventSystem;
import risk.util.IDestroyable;

/**
 * IPlayerController is the foundation for every controller which represents a player.
 * 
 * @author Filip Törnqvist
 * @version 2018-02-28
 */
public interface IPlayerController extends IEventSystem, IDestroyable {
	
}
