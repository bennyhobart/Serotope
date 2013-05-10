package Serotope;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class GameObject {
	Image image;
	public int id;
	private Body body;
	boolean solid;
	public boolean doomed;

	GameObject(Vec2 position, Image image, boolean solid) {
		doomed = false;
		this.solid = solid;
		GameWorld.getGameWorld().getGameObjects().add(this);
		GameWorld.getGameWorld();
		id = GameWorld.idnum++;
		if (!solid) {
			BodyDef bd = new BodyDef();
			bd.fixedRotation = true;
			bd.userData = this;
			bd.position.set(position);
			setBody(GameWorld.getGameWorld().getPhysicsWorld().createBody(bd));
		}
		this.image = image;
	}

	abstract public void update(int delta, GameContainer gc);

	void render(Graphics g, float xrender, float yrender) {
		if (image == null) {
			return;
		}
		image.setRotation((float) (-getBody().getAngle() * 180 / Math.PI));
		image.drawCentered(xrender, yrender);
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	protected void die() {
		doomed = true;
	}
	
	public int getId() {
		return id;
	}

}
