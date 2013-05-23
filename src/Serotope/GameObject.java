package Serotope;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class GameObject {
	protected Image image;
	public int id;
	private Body body;
	public boolean doomed;

	protected GameObject(Vec2 position, Image image, boolean solid) {
		doomed = false;
		GameWorld.getGameWorld().getGameObjects().add(this);
		GameWorld.getGameWorld();
		id = GameWorld.idNum++;
		if (!solid) {
			initialiseBodyDef(position);
		}
		this.image = image;
	}

	abstract public void update(int delta, GameContainer gc);

	// Draw the game objects image at the given position
	void render(Graphics g, float xrender, float yrender) {
		if (image == null) {
			return;
		}
		image.setRotation((float) (-getBody().getAngle() * 180 / Math.PI));
		image.drawCentered(xrender, yrender);
	}

	protected void die() {
		doomed = true;
	}

	// Initialise a BodyDef object with the physical attributes
	private void initialiseBodyDef(Vec2 position) {
		BodyDef bd = new BodyDef();
		bd.fixedRotation = true;
		bd.userData = this;
		bd.awake = false;
		bd.position.set(position);
		setBody(GameWorld.getGameWorld().getPhysicsWorld().createBody(bd));
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public int getId() {
		return id;
	}

}
