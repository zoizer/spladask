package risk.model;

import risk.event.IEventSystem;
import risk.util.IDestroyable;

/**
 * The base interface for all GameModels
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 *
 */
public interface IGameModel extends IEventSystem, IDestroyable {
	
}
