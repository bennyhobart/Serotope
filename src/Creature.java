



import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;


public class Creature extends GameObject {
	//Move Variables
	float topSpeed;
	float acceleration;
	float currSpeed;
	float currMove;
	//Health Variables
	int maxHealth;
	int currHealth;
	int lifeSpan;
	int defence;
	//Damage Variables
	int attackSpeed;
	int timeSinceLastAttack;
	int damage;
	Controller controller;
	
	public Creature(int id,float x, float y,Image image,boolean playercontrolled) {
		super(id,x,y,image,true);
		if(playercontrolled){
			topSpeed=1f;
			controller = new PlayerController(this);
		}
		else {
			controller = new AIController(this);
		}
		BodyDef bd = new BodyDef();
		bd.fixedRotation=true;
		bd.type=BodyType.DYNAMIC;
		bd.position.set(new Vec2((float)x/Utils.scale,(float)y/Utils.scale));
		body = GameWorld.getGameWorld().getPhysicsWorld().createBody(bd);
		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.m_radius=(image.getWidth()/2)/Utils.scale;
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape=dynamicCircle;
		fixtureDef.density=1f;
		body.createFixture(fixtureDef);
		body.m_linearDamping=0.01f;
	}

	@Override
	public void update(int delta, GameContainer gc) {
		controller.update(delta);
	}


}
