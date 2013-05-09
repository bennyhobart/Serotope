package AI;


public class StateMachine<Actor> {
	Actor target;
	State<Actor> currentState;
	State<Actor> previousState;
	//State<Actor> globalState;
	public StateMachine(Actor target) {
		this.target=target;
	}
	/*public void setGlobalState(State<Actor> state) {
		globalState = state;
	}*/
	public void setCurrentState(State<Actor> state) {
		currentState = state;
	}
	public void update() {
		/*if(globalState!=null) {
			globalState.execute(target);
		}*/
		if(currentState!=null) {
			currentState.execute(target);
		}
		
	}
	public void changeState(State<Actor> newState) {
		previousState = currentState;
		previousState.exit(target);
		currentState= newState;
		currentState.enter(target);
	}
}
