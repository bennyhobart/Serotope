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
		creatorId=id;
		BodyDef bd = new BodyDef();
		bd.fixedRotation=true;
		bd.userData = this;
		bd.type=BodyType.DYNAMIC;
		bd.position.set(position);
		bd.bullet=true;
		body = GameWorld.getGameWorld().getPhysicsWorld().createBody(bd);
		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.m_radius=(image.getWidth()/2)/Utils.scale;
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape=dynamicCircle;
		fixtureDef.density=0f;
		body.createFixture(fixtureDef);
		direction=velocity;
		body.setLinearVelocity(direction.mul(5));
		this.damage=damage;
	}

	@Override
	public void update(int delta, GameContainer gc) {
		ContactEdge contact = body.getContactList();
		while(contact!=null) {
			System.out.println(contact.other.getUserData().toString());
			if(contact.other.getUserData() instanceof Creature) {
				Creature creature = (Creature) contact.other.getUserData();
				if(creature.id==creatorId) {
					contact=contact.next;
					continue;
				}
				
				creature.hit(damage);
				/*GameWorld.getGameWorld().getGameObjects().remove(this);
				body.m_world.destroyBody(this.body);
				try {
					this.finalize();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
			contact=contact.next;
		}
		return;
	}
}
