



public abstract class Controller extends Component {

	public Controller(Creature creature) {
		super(creature);
	}
	public void update(int delta) {
		move(delta);
		shoot(delta);
	}
	
	abstract void shoot(int delta);
	abstract void move(int delta);

}
