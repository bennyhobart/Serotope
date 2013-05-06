package AI;


public abstract class State<Actor> {
	abstract void enter(Actor target);
	abstract void execute(int delta);
	abstract void exit();
}
