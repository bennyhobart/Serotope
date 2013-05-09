package AI;

/**
 * 
 * @author BenedictHobart
 *
 * @param <Actor> The target the state machine should act up
 */
public class StateMachine<Actor> {
	Actor target;
	State<Actor> currentState;
	State<Actor> previousState;
	//State<Actor> globalState;
	/**
	 * sets the the object for the state machine to act upon
	 * @param target
	 */
	public StateMachine(Actor target) {
		this.target=target;
	}
	/*public void setGlobalState(State<Actor> state) {
		globalState = state;
	}*/
	/**
	 * sets the current state, used to initialize the state machine
	 * @param state
	 */
	public void setCurrentState(State<Actor> state) {
		currentState = state;
	}
	/**
	 * updates the state machine
	 */
	public void update() {
		/*if(globalState!=null) {
			globalState.execute(target);
		}*/
		if(currentState!=null) {
			currentState.execute(target);
		}
		
	}
	/**
	 * sets the state machine to enter the new state
	 * @param newState
	 */
	public void changeState(State<Actor> newState) {
		previousState = currentState;
		previousState.exit(target);
		currentState= newState;
		currentState.enter(target);
	}
}
