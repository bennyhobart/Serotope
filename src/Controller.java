
public abstract class Controller extends Component {

	public Controller(Creature creature) {
		super(creature);
	}
	abstract void shoot(int delta);
	abstract void move(int delta);

}
