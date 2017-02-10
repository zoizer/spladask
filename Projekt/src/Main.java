//import java.io.*;
import java.util.Scanner;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
// test
import risk.process.*;
import risk.testing.*;

public final class Main {
	private Main(){}; // pure static class.
	
	public static void main(String[] args) {
		// Game game = new Game();
		
		
		/*ProcessManager pm = new ProcessManager();
		pm.AttachProcess(new TestProcess(100));
		
		int input = 0;
		Scanner in = new Scanner(System.in);
		int result = 0;
		
		while(input != -2) {
			input = in.nextInt();
			result = pm.UpdateProcess(input);
			
			if(result != 0) {
				System.out.println("Processes failed! " + result);
			}
			
			if(input == -1) {
				pm.AttachProcess(new TestProcess(100));
			}
			
			if(input == -3) {
				pm.AbortAll(false);
			}
			
		}
		
		
		in.close();*/
		
		//List delegateList;
		//delegateList = new List<>
		
		
		int input = 0;
		Scanner in = new Scanner(System.in);
		AClass myclass = new AClass();
		
		// onödig kommentar
		while(input != -2) {
			input = in.nextInt();
			testFunction(myclass, "myFunction");
			
		}
		
		
		in.close();
		
	}
	
	public static void testFunction(Object obj, String func) {
		Method method;
		try {
			method = obj.getClass().getMethod(func);
			method.invoke(obj, (Object[])null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Comment Test
	}
}
