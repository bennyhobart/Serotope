
public abstract class Component {
	Creature target;
	public Component(Creature creature) {
		target=creature;
	}
	
	abstract void update(int delta);

}
