package risk.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Delegate extends Pair<Object, Method> {

	public Delegate(Object left, String right) {
			super(left, JavaIsWorseThanAIDS(left, right));
	}
	
	private static final Method JavaIsWorseThanAIDS(Object o, String f) {
		try {
			return  o.getClass().getMethod(f);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			ErrorHandler.ASSERT(true); // Reached this point? Well that's a fatal error. Only way to prevent it is by changing your code.
			return null;
		}
	}
	
	public void Execute() {
		try {
			right.invoke(left, (Object[])null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace(); // Reached this point? Well that's a fatal error. Only way to prevent it is by changing your code.
			ErrorHandler.ASSERT(true);
		}
	}
}
