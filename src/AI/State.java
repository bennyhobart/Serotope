package AI;


public abstract class State<Actor> {
	public abstract void enter(Actor target);
	public abstract void execute(Actor target);
	public abstract void exit(Actor target);
}
