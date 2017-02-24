package risk.general.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import risk.general.event.IEvent;

public class Delegate extends Pair<Object, Method> {

	public Delegate(Object left, String right) {
			super(left, JavaIsWorseThanAIDS(left, right));
	}
	
	private static final Method JavaIsWorseThanAIDS(Object o, String f) {
		try {
			return  o.getClass().getMethod(f, new Class[] {IEvent.class});
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			ErrorHandler.ASSERT(true); // Reached this point? Well that's a fatal error. Only way to prevent it is by changing your code.
			return null;
		}
	}
	
	public void Execute(Object[] p) {
		try {
			right.invoke(left, p);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace(); // Reached this point? Well that's a fatal error. Only way to prevent it is by changing your code.
			ErrorHandler.ASSERT(true);
		}
	}
}
