package GAME;




public abstract class Controller {
	public Creature target;
	public Controller(Creature creature) {
		target=creature;
	}
	abstract void update(int delta);

}
