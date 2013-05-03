



import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


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
	
	public Creature(Vec2 position,boolean playercontrolled) throws SlickException {
		super(position,new Image(Utils.creatureImage),true);
		attackSpeed=600;
		timeSinceLastAttack=0;
		damage=5;
		topSpeed=5f;
		acceleration=1f;
		if(playercontrolled){
			controller = new PlayerController(this);
		}
		else {
			controller = new AIController(this);
		}
		BodyDef bd = new BodyDef();
		bd.fixedRotation=true;
		bd.type=BodyType.DYNAMIC;
		bd.userData=this;
		bd.position.set(position);
		body = GameWorld.getGameWorld().getPhysicsWorld().createBody(bd);
		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.m_radius=(image.getWidth()/2)/Utils.scale;
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape=dynamicCircle;
		fixtureDef.density=2f;
		body.createFixture(fixtureDef);
		body.m_linearDamping=3f;
	}

	@Override
	public void update(int delta, GameContainer gc) {
		controller.update(delta);
		timeSinceLastAttack+=delta;
	}

	public void hit(int damage) {
		currHealth-=(damage-defence);
		
	}


}
