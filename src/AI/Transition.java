package AI;

public abstract class Transition<Actor> {
	State<Actor> from;
	State<Actor> to;
	abstract boolean condition();
}
