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
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ParticleEffects.ExplosionEffect;
import Utils.Utils;


public class Bullet extends GameObject {

	private int damage;
	private Vec2 direction;
	public int creatorId;
	public int attackType;
	Bullet(Vec2 position, Vec2 velocity,int damage, int id, int attackType) throws SlickException {
		super(position,Utils.BULLETIMAGES[attackType], true);
		this.attackType=attackType;
		//keeps a handle on creator so it knows not to kill his own creator.
		creatorId=id;
		//solid object needs a body
		BodyDef bd = new BodyDef();
		//has a fixed rotation in the direction of its velocity, it should despawn after any physics interactions anyway
		//unless it interacts with its creator
		bd.fixedRotation=true;
		//seting the user data to point to this object
		bd.userData = this;
		//dynamic object can interact with other objects as well as vica versa
		bd.type=BodyType.KINEMATIC;
		bd.position.set(position);
		bd.bullet=true;
		setBody(GameWorld.getGameWorld().getPhysicsWorld().createBody(bd));
		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.m_radius=(image.getWidth()/2)/Utils.SCALE;
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape=dynamicCircle;
		fixtureDef.density=0f;
		getBody().createFixture(fixtureDef);
		direction=velocity;
		getBody().setFixedRotation(true);
		getBody().setTransform(getBody().getPosition(),(float) Math.atan2(direction.y, direction.x));
		getBody().setLinearVelocity(velocity.mul(Utils.BULLETVELOCITY));
		this.damage=damage;

	}
	public void collide(Bullet colidingwith) {
		if(doomed) {
			return;
		}
		if(attackType==3) {
			explode();
		}
		else {
			new ExplosionEffect(getBody().getPosition(), 6, 200);
		}
		this.die();
	}
	@Override
	public void update(int delta, GameContainer gc) {
		ContactEdge contact = getBody().getContactList();
		
		while(contact!=null) {
			if(contact.other.getUserData() instanceof Creature) {
				Creature creature = (Creature) contact.other.getUserData();
				if(creature.id==creatorId) {
					contact=contact.next;
					continue;
				}
				creature.hit(damage);
				if(attackType==3) {
					explode();
				}
				else {
					new ExplosionEffect(getBody().getPosition(),8,300);
				}
				this.die();
				
			}
			else if(contact.other.getUserData() instanceof Bullet) {
				Bullet bullet = (Bullet) contact.other.getUserData();
				if(bullet.creatorId==this.creatorId) {
					contact=contact.next;
					continue;
				}
				bullet.collide(this);
				if(attackType==3) {
					explode();
				}
				die();
			}
			contact=contact.next;
		}
		return;
	}
	private void explode() {
		if(attackType!=3) {
			return;
		}
		new ExplosionEffect(getBody().getPosition(), 15,500);
		AABB zone = new AABB(this.getBody().getPosition().add(new Vec2(-Utils.ROCKETEXPLOSIONRADIUS,-Utils.ROCKETEXPLOSIONRADIUS)),this.getBody().getPosition().add(new Vec2(Utils.ROCKETEXPLOSIONRADIUS,Utils.ROCKETEXPLOSIONRADIUS)));
		ArrayList<GameObject> gameObjects = Utils.getGameObjectsAABB(zone);
		for(int i=0;i<gameObjects.size();i++) {
			GameObject target = gameObjects.get(i);
			if(Utils.lengthBetween(target.getBody().getPosition(), getBody().getPosition())>Utils.ROCKETEXPLOSIONRADIUS||target.doomed||target.id==creatorId) {
				continue;
			}
			if(target instanceof Bullet) {
				target.doomed=true;
				((Bullet) target).collide(this);
			}
			else if(target instanceof Creature) {
				((Creature) target).hit((int)(damage*Utils.ROCKETEXPLOSIONDAMAGE));
				
			}
		}
	}
}
