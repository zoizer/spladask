package risk.testing;

import risk.general.process.Process;

public class TestProcess extends Process {
	int counter;
	public TestProcess(int val) {
		counter = val;
	}
	
	public void OnUpdate(int deltaMs) {
		counter -= deltaMs;
		if(counter <= 0) {
			Succeed();
			System.out.println("Success!");
		} else {

			System.out.println("Not yet done! " + counter);
		}
	}
}
