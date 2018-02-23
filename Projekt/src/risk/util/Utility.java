package risk.util;

import java.util.ArrayList;
import java.util.List;

import risk.event.EventType;

public class Utility {
	
	
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
