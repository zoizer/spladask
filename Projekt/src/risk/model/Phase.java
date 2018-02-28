package risk.model;

/**
 * The phase enums purpose is to create a clean way to know what the game is supposed to do.
 * 
 * @author 		Filip Törnqvist
 * @version 	2018-02-28
 */
public enum Phase {
	/**
	 * Used to signal error or that there is no phase
	 */
	ERROR_DO_NOT_USE,
	
	/**
	 * Initialization phase, where players place their units on the map
	 */
	INIT_PHASE,
	
	/**
	 * The training phase, where players train some units
	 */
	TRAIN_PHASE,
	
	/**
	 * The attack phase, where players attack others or move between their own zones.
	 */
	ATTACK_PHASE
}
