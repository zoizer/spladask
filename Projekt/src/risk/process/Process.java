package risk.process;

import risk.util.ErrorHandler;

/**
 * The base class of all processes.
 * All new process classes should inherit this.
 * @author -
 */
public abstract class Process {
	public enum State {
		UNINITIALIZED, REMOVED, RUNNING, PAUSED, SUCCEEDED, FAILED, ABORTED;
	};
	
	private State state;
	private Process child;
	
	public Process() {
		state = State.UNINITIALIZED;
		child = null;
	}
	
	// "protected" section
	/**
	 * Code to execute on initialization. including setting state to running.
	 *  - Feel free to override -
	 */
	protected void OnInit() { state = State.RUNNING; }
	
	/**
	 * Updates the process and executes the potentially time based code.
	 *  - Feel free to override -
	 * @param deltaMs milli-seconds since last call.
	 */
	protected abstract void OnUpdate(int deltaMs);
	
	/**
	 * Called after success has been triggered.
	 *  - Feel free to override -
	 */
	protected void OnSuccess() {};
	
	/**
	 * Called after fail has been triggered.
	 *  - Feel free to override -
	 */
	protected void OnFail() {};
	
	/**
	 * Called after abort has been triggered.
	 *  - Feel free to override -
	 */
	protected void OnAbort() {};

	/**
	 * Sets the state but calls no code.
	 * @param state The new state.
	 */
	protected final void SetState(State state) {
		this.state = state;
	}
	
	// "public final" section
	/**
	 * Sets the status to ABORTED.
	 */
	public final void Abort() {
		state = State.ABORTED;
	}
	
	/**
	 * Sets the status to SUCCEEDED, will ASSERT that the process is alive.
	 */
	public final void Succeed() {
		ErrorHandler.ASSERT(IsAlive());
		state = State.SUCCEEDED;
	}
	
	/**
	 * Sets the status to FAILED, will ASSERT that the process is alive.
	 */
	public final void Fail() {
		ErrorHandler.ASSERT(IsAlive());
		state = State.FAILED;
	}
	
	/**
	 * Attempts to pause the process. Will give a warning if it isn't running.
	 */
	public final void Pause() {
		if(state == State.RUNNING) state = State.PAUSED;
		else ErrorHandler.WARNING("Attempting to pause a process that isn't running.");
	}
	
	/**
	 * Attempts to unpause the process. Will give a warning if it isn't paused.
	 */
	public final void Unpause() {
		if(state == State.PAUSED) state = State.RUNNING;
		else ErrorHandler.WARNING("Attempting to unpause a procecss that isn't paused.");
	}
	
	public final State GetState() {
		return state;
	}
	
	public final boolean IsAlive() {
		return (state == State.RUNNING) || (state == State.PAUSED);
	}
	
	public final boolean IsDead() {
		return (state == State.SUCCEEDED) || (state == State.FAILED) || (state == State.ABORTED);
	}
	
	public final boolean IsRemoved() {
		return state == State.REMOVED;
	}
	
	public final boolean IsPaused() {
		return state == State.PAUSED;
	}
	
	/**
	 * Attaches a child to this process or its child or its etc.
	 * @param child The child to be attached.
	 */
	public final void AttachChild(Process child) {
		if(this.child == null) this.child = child;
		else this.child.AttachChild(child);
	}
	
	/**
	 * Removes the child. This includes the childs children.
	 * @return The Child or null.
	 */
	public final Process RemoveChild() {
		if(child != null) {
			Process temp = child;
			child = null;
			return temp;
		}
		else return null;
	}
	
	/**
	 * Gets a reference to the potential child (may be null)
	 * @return The Child. (or null.)
	 */
	public final Process PeekChild() { 
		return child; 
	}
	
}
