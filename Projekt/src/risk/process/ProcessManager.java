package risk.process;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This is a process manager, you can create as many or as few as you need, they will manage processes.
 * How to use:
 * ProcessManager pm = new ProcessManager();
 * pm.AttachProcess(new MyProcess()); // MyProcess inherits from Process
 * <br>
 * int timeMs = 0;<br>
 * int temp = 0;<br>
 * int deltaMs = 0;<br>
 * <br>
 * while(true) {<br>
 * 		temp = GetTime();<br>
 * 		deltaMs = temp - timeMs;<br>
 * 		timeMs = temp;<br>
 * 		UpdateProcess(deltaMs);<br>
 * }<br>
 * 
 * // some exit code may need to be added.
 * @author
 */
public class ProcessManager {
	private ArrayList<Process> processes;
	
	public ProcessManager() {
		processes = new ArrayList<Process>();
	}
	
	/**
	 * This updates and executes all processes in the manager.
	 * @param deltaMs The time that has passed since last time this update was done.
	 * @return The amount of processes that failed or was aborted.
	 */
	public int UpdateProcess(int deltaMs) {
		int failCount = 0;
		
		for(Iterator<Process> iter = processes.iterator(); iter.hasNext();) {
			Process p = iter.next();
			if(p.GetState() == Process.State.UNINITIALIZED) p.OnInit();
			if(p.GetState() == Process.State.RUNNING) p.OnUpdate(deltaMs);
			if(p.IsDead()) {
				Process.State state = p.GetState();
				
				if(state == Process.State.SUCCEEDED) {
					p.OnSuccess();
					Process child = p.RemoveChild();
					if(child != null) AttachProcess(child);
					
				} else if(state == Process.State.FAILED) {
					p.OnFail();
					failCount++;
				} else if(state == Process.State.ABORTED) {
					p.OnAbort();
					failCount++;
				}
				
				iter.remove();
			}
				
		}
		
		return failCount;
	}
	
	/**
	 * Attaches a process to the manager.
	 * @param process The process to attach.
	 */
	public void AttachProcess(Process process) {
		processes.add(process);
	}
	
	/**
	 * Removes all processes in the manager without calling any exit code.
	 */
	public void ClearAll() {
		processes.clear();
	}
	
	/**
	 * Aborts all living processes and removes them if set to instant.
	 * This does NOT include potential dead processes.
	 * @param instant Decides whether or not to finalize the abortion now or if UpdateProcess should handle it.
	 */
	public void AbortAll(boolean instant) {
		for(Iterator<Process> iter = processes.iterator(); iter.hasNext();) {
			Process p = iter.next();
			if(p.IsAlive()) {
				p.Abort();
				if(instant) {
					p.OnAbort();
					iter.remove();
				}
			}
		}
	}
	
	/**
	 * @return the amount of processes in the manager.
	 */
	int GetProcessCount() {
		return processes.size();
	}
}
