import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public abstract class GameObject {
	double x;
	double y;
	int id;
	abstract public void update(int delta, GameContainer gc);
	abstract public void render(Graphics g);
}
