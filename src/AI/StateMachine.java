package AI;

import java.util.ArrayList;

public class StateMachine<Actor> {
	Actor target;
	State<Actor> currentState;
	State<Actor> previousState;
	ArrayList<Transition<Actor>> transitions;
	public StateMachine(Actor target,ArrayList<Transition<Actor>> transitions, State<Actor> start) {
		/*this.target=target;
		this.transitions = transitions;
		start.enter(target);
		currentState=start;*/
	}
	public void update() {
		for(int i=0;i<transitions.size();i++) {
			if(transitions.get(i).from==currentState) {
				if(transitions.get(i).condition()) {
					ChangeState(transitions.get(i).to);
				}
			}
		}
		if(currentState==null) {
			return;
		}
		currentState.execute(0);
	}
	void ChangeState(State<Actor> newState) {
		previousState = currentState;
		currentState= newState;
		previousState.exit();
		currentState.enter(target);
	}
}
