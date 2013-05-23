package Serotope;

public abstract class Controller {
	public Creature target;

	/**
	 * Create a new controller
	 * 
	 * @param creature
	 *            Creature object being controlled by this object
	 */
	public Controller(Creature creature) {
		target = creature;
	}

	abstract void update(int delta);

}
