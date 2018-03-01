package risk.util;

import java.util.ArrayList;
import java.util.List;

import risk.event.EventType;

/**
 * Utility class which should contain utility functions
 * @author 		Filip Törnqvist
 * @version 	2018-03-01
 *
 */
public class Utility {
	
	/**
	 * Utility function to create a list of all EventTypes beginning with a specific string.
	 * 
	 * @param s The starting string of the events
	 * @return The list of the EventTypes found
	 */
	public static List<EventType> getEventsOfType(String s) {
		EventType[] all = EventType.values();
		List<EventType> result = new ArrayList<EventType>();
		
		for (int i = 0; i < all.length; i++) {
			if (all[i].toString().startsWith(s))
				result.add(all[i]);
		}
		
		return result;
	}
	
	
	
}
