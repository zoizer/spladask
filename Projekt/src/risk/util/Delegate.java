package risk.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import risk.event.IEvent;

/**
 * This is a C++ style delegate, whos purpose is to create a function pointer to a member function.
 * 
 * @author 		Filip Törnqvist
 * @version 	11/02
 */
public class Delegate extends Pair<Object, Method> {

	public Delegate(Object left, String right) {
			super(left, JavaWorkaround(left, right));
	}
	
	private static final Method JavaWorkaround(Object o, String f) {
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
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace(); // Reached this point? Well that's a fatal error. Only way to prevent it is by changing your code.
			ErrorHandler.ASSERT(false);
		} catch (InvocationTargetException e) {
			e.getTargetException().printStackTrace();;
			ErrorHandler.ASSERT(false);
		}
	}
}
