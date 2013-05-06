package GAME;




public abstract class Controller {
	Creature target;
	public Controller(Creature creature) {
		target=creature;
	}
	public void update(int delta) {
		move(delta);
		shoot(delta);
	}
	
	abstract void shoot(int delta);
	abstract void move(int delta);

}
