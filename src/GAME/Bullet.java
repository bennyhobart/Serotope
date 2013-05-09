package GAME;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.ContactEdge;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Utils.Utils;


public class Bullet extends GameObject {

	private int damage;
	private Vec2 direction;
	public int creatorId;
	Bullet(Vec2 position, Vec2 velocity,int damage, int id, int attackType) throws SlickException {
		super(position, new Image(Utils.BULLETIMAGES[attackType]), true);
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
		bd.type=BodyType.DYNAMIC;
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
		GameWorld.getGameWorld().getGameObjects().remove(this);
		getBody().m_world.destroyBody(this.getBody());
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
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
				GameWorld.getGameWorld().getGameObjects().remove(this);
				getBody().m_world.destroyBody(this.getBody());
				try {
					this.finalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				
			}
			if(contact.other.getUserData() instanceof Bullet) {
				Bullet bullet = (Bullet) contact.other.getUserData();
				if(bullet.creatorId==this.creatorId) {
					contact=contact.next;
					continue;
				}
				bullet.collide(this);
				GameWorld.getGameWorld().getGameObjects().remove(this);
				getBody().m_world.destroyBody(this.getBody());
				try {
					this.finalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
			contact=contact.next;
		}
		return;
	}
}
