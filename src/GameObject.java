

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public abstract class GameObject {
	Image image;
	int id;
	Body body;
	boolean solid;
	GameObject(Vec2 position, Image image,boolean solid) {
		this.solid=solid;
		GameWorld.getGameWorld().getGameObjects().add(this);
		id = GameWorld.getGameWorld().idnum++;
		if(solid==false) {
			BodyDef bd = new BodyDef();
			bd.fixedRotation=true;
			bd.userData = this;
			bd.type=BodyType.KINEMATIC;
			bd.position.set(position);
			body = GameWorld.getGameWorld().getPhysicsWorld().createBody(bd);	
		}
		this.image=image;
	}
	abstract public void update(int delta, GameContainer gc);
	void render(Graphics g,float xrender,float yrender) {
		if(image==null) {
			return;
		}
		image.drawCentered(xrender, yrender);
	}
	
	
}
