package risk.general.util;

public final class ErrorHandler {
	private ErrorHandler() {}
	
	private static int warningCount = 0;
	
	/**
	 * Special Assert Function.
	 * @param Expected to be true or exit will occur.
	 */
	public static void ASSERT(boolean val) { 
		if(!val) {
			System.out.println("Assertion Failiure!");
			System.exit(0); // meant to be non-zero for error.
		}
	}
	
	/**
	 * Special Warning function.
	 * @param msg Message to be written to the console.
	 */
	public static void WARNING(String msg) {
		System.out.println("Warning " + ++warningCount + ": " + msg);
	}
}
