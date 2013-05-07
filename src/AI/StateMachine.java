package AI;public class StateMachine<Actor> {
	Actor target;
	State<Actor> currentState;
	State<Actor> previousState;
	State<Actor> globalState;
	Object extraData;
	public StateMachine(Actor target, State<Actor> start,Object extraData) {
		this.target=target;
		
		if(start!=null) {
			start.enter(target);
			currentState=start;
		}
		this.extraData = extraData;
	}
	public void update() {
		if(globalState!=null) {
			globalState.execute(target);
		}
		if(currentState!=null) {
			currentState.execute(target);
		}
		
	}
	public void changeState(State<Actor> newState) {
		if(previousState!=null) {
			previousState = currentState;
			previousState.exit(target);
		}
		currentState= newState;
		currentState.enter(target);
	}
}
