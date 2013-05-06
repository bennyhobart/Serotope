package GAME;




import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Marker extends GameObject {
	float xVel;
	float yVel;
	int damage;
	
	public Marker(Vec2 position) throws SlickException {
		super(position,new Image("assets/image/creature2.png"),false);
	}

	@Override
	public void update(int delta, GameContainer gc) {
		
	}


}
