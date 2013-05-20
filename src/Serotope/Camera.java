package Serotope;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;



public class Camera extends GameObject{
	GameObject target;
	
	/**
	 * Generate a camera object
	 * 
	 * @param gameObject The object being focused on by the camera
	 */
	public Camera(GameObject gameObject) {
		super(new Vec2(0,0), null, false);
		target = gameObject;
	}

	@Override
	public void update(int delta, GameContainer gc) {
		target = GameWorld.getGameWorld().getPlayer();
		getBody().setTransform(target.getBody().getPosition(),0);
		
	}

}
