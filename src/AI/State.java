package AI;


/**
 * 
 * @author BenedictHobart
 *
 * @param <Actor> The target the state machine should act up
 */
public abstract class State<Actor> {
	/**
	 * called on entry to the state by the state machine to initialize the state
	 * @param target
	 */
	public abstract void enter(Actor target);
	/**
	 * called on every update() of the statemachine if set as current/global state
	 * @param target
	 */
	public abstract void execute(Actor target);
	/**
	 * called on exit when the statemachine changes states
	 * @param target
	 */
	public abstract void exit(Actor target);

}
