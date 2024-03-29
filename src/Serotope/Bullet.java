package Serotope;

import java.util.ArrayList;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.ContactEdge;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import ParticleEffects.ExplosionEffect;
import Utils.Utils;

public class Bullet extends GameObject {

	private int damage;
	private Vec2 direction;
	public int creatorId;
	public int attackType;

	/**
	 * Creates a new bullet
	 * 
	 * @param position
	 *            Vector of where the bullet exists
	 * @param velocity
	 *            Bullet's velocity
	 * @param damage
	 *            Amount of damage the bullet deals
	 * @param id
	 *            Unique identifier
	 * @param attackType
	 *            Type of bullet
	 * @throws SlickException
	 */
	Bullet(Vec2 position, Vec2 velocity, int damage, int id, int attackType)
			throws SlickException {
		super(position, Utils.BULLETIMAGES[attackType], true);
		this.attackType = attackType;
		// keeps a handle on creator so it knows not to kill his own creator.
		creatorId = id;

		initialiseBodyDef(position);
		initialiseFixtureDefinition();

		direction = velocity;
		getBody().setFixedRotation(true);
		getBody().setTransform(getBody().getPosition(),
				(float) Math.atan2(direction.y, direction.x));
		getBody().setLinearVelocity(velocity);
		this.damage = damage;

	}

	/**
	 * Generate explosions on collision with another bullet
	 * 
	 * @param colidingwith
	 *            Another bullet that is colliding with this one
	 */
	public void collide(Bullet colidingwith) {
		if (doomed) {
			return;
		}
		switch (attackType) {
		case Utils.ROCKET_BULLETS:
			explode();
			break;
		case Utils.MACHINE_GUN_BULLETS:
			new ExplosionEffect(getBody().getPosition(), 1, 10);
			break;
		case Utils.SINGLESHOT:
			new ExplosionEffect(getBody().getPosition(),
					Utils.NUMPARTICLESEXPLOSION, Utils.EXPLOSION_LIFESPAN);

			break;
		case Utils.SHOTGUN:
			new ExplosionEffect(getBody().getPosition(),
					Utils.NUMPARTICLESEXPLOSION, 40);

			break;
		}
		this.die();
	}

	@Override
	public void update(int delta, GameContainer gc) {
		ContactEdge contact = getBody().getContactList();

		while (contact != null) {
			// Collisions with creatures
			if (contact.other.getUserData() instanceof Creature) {
				Creature creature = (Creature) contact.other.getUserData();
				// Check if that creature shot this bullet
				if (creature.id == creatorId) {
					contact = contact.next;
					continue;
				}
				// Deal damage to creature
				creature.hit(damage);

				// Increment the number of enemies killed statistic if a creature is killed
				// by the player
				if (creatorId == GameWorld.getGameWorld().getPlayerId() && creature.getCurrHealth() <= 0)
					GameWorld.gameStats.incrementEnemiesKilled();
				
				if (attackType == Utils.ROCKET) {
					explode();
				} else {
					new ExplosionEffect(getBody().getPosition(), 4, 200);
				}
				// Destroy bullet
				this.die();

				// Collisions with other bullets
			} else if (contact.other.getUserData() instanceof Bullet) {
				Bullet bullet = (Bullet) contact.other.getUserData();
				// Check if the bullet is from the same creature as this one
				if (bullet.creatorId == this.creatorId) {
					contact = contact.next;
					continue;
				}
				// Collide with the other bullet and explode
				bullet.collide(this);
				if (attackType == Utils.ROCKET) {
					explode();
				}
				die();
			}
			contact = contact.next;
		}
		return;
	}

	/**
	 * Generate explosion particles
	 */
	private void explode() {
		if (attackType != Utils.ROCKET_BULLETS) {
			return;
		}
		new ExplosionEffect(getBody().getPosition(), 10, 300);
		AABB zone = new AABB(this
				.getBody()
				.getPosition()
				.add(new Vec2(-Utils.ROCKETEXPLOSIONRADIUS,
						-Utils.ROCKETEXPLOSIONRADIUS)), this
				.getBody()
				.getPosition()
				.add(new Vec2(Utils.ROCKETEXPLOSIONRADIUS,
						Utils.ROCKETEXPLOSIONRADIUS)));
		ArrayList<GameObject> gameObjects = Utils.getGameObjectsAABB(zone);
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject target = gameObjects.get(i);
			if (Utils.lengthBetween(target.getBody().getPosition(), getBody()
					.getPosition()) > Utils.ROCKETEXPLOSIONRADIUS
					|| target.doomed || target.id == creatorId) {
				continue;
			}
			if (target instanceof Bullet) {
				target.doomed = true;
				((Bullet) target).collide(this);
			} else if (target instanceof Creature) {
				((Creature) target)
						.hit((int) (damage * Utils.ROCKETEXPLOSIONDAMAGE));

			}
		}
	}

	// Initialise a BodyDef Object with the required attributes for a new bullet

	private void initialiseBodyDef(Vec2 position) {
		// solid object needs a body
		BodyDef bd = new BodyDef();
		// has a fixed rotation in the direction of its velocity, it should
		// despawn after any physics interactions anyway
		// unless it interacts with its creator
		bd.fixedRotation = true;
		// seting the user data to point to this object
		bd.userData = this;
		// dynamic object can interact with other objects as well as vica versa
		bd.type = BodyType.KINEMATIC;
		bd.position.set(position);
		bd.bullet = true;
		setBody(GameWorld.getGameWorld().getPhysicsWorld().createBody(bd));
	}

	// Initialise a FixtureDef object with the required attributes for a new
	// bullet

	private void initialiseFixtureDefinition() {
		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.m_radius = (image.getWidth() / 2) / Utils.SCALE;
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicCircle;
		fixtureDef.density = 0f;
		getBody().createFixture(fixtureDef);
	}
}
