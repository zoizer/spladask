package risk.util;

/**
 * ErrorHandler is a class that contains static help functions that allows easier warnings and debug.
 * 
 * 
 * @author 		Filip Törnqvist
 * @version 	2017-02-11
 */
public final class ErrorHandler {
	private ErrorHandler() {}
	
	private static int warningCount = 0;
	
	/**
	 * Special Assert Function, writes log and crashes program.
	 * @param val Expected to be true or log and exit will occur.
	 */
	public static void ASSERT(boolean val) { 
		if(!val) {
			System.out.println("Assertion Failiure!");
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			System.out.println("CLASS: " + stackTraceElements[2].getClassName() + ", METHOD: " + stackTraceElements[2].getMethodName() + ", LINE: " + stackTraceElements[2].getLineNumber());
			System.exit(0); // meant to be non-zero for error.
		}
	}
	
	/**
	 * Special Warning function.
	 * @param msg Message to be written to the console.
	 */
	public static void WARNING(String msg) {
		System.out.println("Warning " + ++warningCount + ": " + msg);
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		System.out.println("CLASS: " + stackTraceElements[4].getClassName() + ", METHOD: " + stackTraceElements[4].getMethodName() + ", LINE: " + stackTraceElements[4].getLineNumber());
		
	}
}
