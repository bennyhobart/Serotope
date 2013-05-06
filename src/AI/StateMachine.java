package AI;public class StateMachine<Actor> {
	Actor target;
	State<Actor> currentState;
	State<Actor> previousState;
	public StateMachine(Actor target, State<Actor> start) {
		this.target=target;
		start.enter(target);
		currentState=start;
	}
	public StateMachine() {
	}
	public void update(int delta) {
		if(currentState==null) {
			return;
		}
		currentState.execute(delta);
	}
	void ChangeState(State<Actor> newState) {
		previousState = currentState;
		currentState= newState;
		previousState.exit();
		currentState.enter(target);
	}
}
