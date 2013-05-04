import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.ContactEdge;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Bullet extends GameObject {

	private int damage;
	private Vec2 direction;
	private int creatorId;
	Bullet(Vec2 position, Vec2 velocity,int damage, int id) throws SlickException {
		super(position, new Image(Utils.bulletImage), true);
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
		body = GameWorld.getGameWorld().getPhysicsWorld().createBody(bd);
		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.m_radius=(image.getWidth()/2)/Utils.SCALE;
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape=dynamicCircle;
		fixtureDef.density=0f;
		body.createFixture(fixtureDef);
		direction=velocity;
		body.setLinearVelocity(velocity.mul(10));
		this.damage=damage;
		body.setFixedRotation(true);
		body.setTransform(body.getPosition(),(float) Math.atan2(direction.y, direction.x));
	}

	@Override
	public void update(int delta, GameContainer gc) {
		ContactEdge contact = body.getContactList();
		while(contact!=null) {
			if(contact.other.getUserData() instanceof Creature) {
				Creature creature = (Creature) contact.other.getUserData();
				if(creature.id==creatorId) {
					contact=contact.next;
					continue;
				}
				creature.hit(damage);
				GameWorld.getGameWorld().getGameObjects().remove(this);
				body.m_world.destroyBody(this.body);
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
