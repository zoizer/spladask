package risk.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import risk.event.IEvent;

/**
 * This is a C++ style delegate, whos purpose is to create a function pointer to a member function.
 * 
 * @author 		Filip Törnqvist
 * @version 	2017-02-11
 */
public class Delegate extends Pair<Object, Method> {

	/**
	 * 
	 * @param left Owner object
	 * @param right Function name
	 */
	public Delegate(Object left, String right) {
			super(left, JavaWorkaround(left, right));
	}
	
	/**
	 * This function is used to allow code to be ran before a super constructor is called
	 * @param o Object
	 * @param f Function Name
	 * @return The resulting method
	 */
	private static final Method JavaWorkaround(Object o, String f) {
		try {
			return  o.getClass().getMethod(f, new Class[] {IEvent.class});
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			ErrorHandler.ASSERT(true); // Reached this point? Well that's a fatal error. Only way to prevent it is by changing your code.
			return null;
		}
	}
	
	/**
	 * Executes the delegate with params p
	 * @param p Params to the delegate
	 */
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
