package risk.event;

/**
 * IEventSystem is an interface which contains basic event system functions 
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 *
 */
public interface IEventSystem {
	
	/**
	 * attachListeners should attach all the default listeners for the derived class implementing this interface
	 */
	public void attachListeners();
	
	/**
	 * detachListeners should detach all the default listeners for the derived class implementing this interface
	 */
	public void detachListeners();
}
