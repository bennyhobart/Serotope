package ParticleEffects;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import Serotope.GameObject;
import Serotope.GameWorld;
import Utils.Utils;
public class Speck extends GameObject {
	int lifeSpan;
	int numParticles;
	Speck(Vec2 position,Vec2 direction, int lifeSpan) throws SlickException {
		super(position, Utils.PARTICLES[GameWorld.getRandomGenerator().nextInt(4)], true);
		initialiseBodyDef(position);
		initialiseFixtureDef();
		getBody().setLinearVelocity(direction.mul(Utils.PARTICLESPEED));
		this.lifeSpan=lifeSpan;
	}

	private void initialiseBodyDef(Vec2 position) {
		// build physics body
		BodyDef bd = new BodyDef();
		// only should rotate when player tells it to by moving
		bd.fixedRotation = true;
		// Should be acted on by other objects as well as act upon other objects
		bd.type = BodyType.DYNAMIC;
		// setting the user data to this so the body has a reference to its
		// corresponding game object
		bd.userData = this;
		// set bodies position
		bd.position.set(position);
		// build body
		setBody(GameWorld.getGameWorld().getPhysicsWorld().createBody(bd));
	}

	private void initialiseFixtureDef() {
		// all creatures are circular
		CircleShape dynamicCircle = new CircleShape();
		// circle radius is approximatly a point
		dynamicCircle.setRadius(1 / Utils.SCALE);
		// body is solid creating fixture
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicCircle;
		// density
		fixtureDef.density=0;
		getBody().createFixture(fixtureDef);
		
	}

	@Override
	public void update(int delta, GameContainer gc) {
		lifeSpan-=delta;
		if(lifeSpan<0) {
			this.die();
		}
		
	}

}
