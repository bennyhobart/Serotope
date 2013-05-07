package AI;


public abstract class State<Actor> {
	StateMachine<Actor> stateMachine;
	State(StateMachine<Actor> target) {
		stateMachine=target;
	}
	abstract void enter(Actor target);
	abstract void execute(Actor target);
	abstract void exit(Actor target);
}
