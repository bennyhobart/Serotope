package GAME;




import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Utils.Utils;


public class Creature extends GameObject {
	//Move Variables
	private float topSpeed;
	float acceleration;
	float handling;
	//Health Variables
	int health;
	int currHealth;
	int stamina;
	boolean shield;
	//Damage Variables
	int damage;
	int attackSpeed;
	int timeSinceLastAttack;
	int attackType;
	
	Controller controller;
	public Creature(Vec2 position,boolean playercontrolled) throws SlickException {
		super(position,new Image(Utils.CREATUREIMAGES[GameWorld.getRandomGenerator().nextInt(Utils.CREATUREIMAGES.length)]),true);
	
		
		
		
		//build physics body
		BodyDef bd = new BodyDef();
		//only should rotate when player tells it to by moving
		bd.fixedRotation=true;
		//Should be acted on by other objects as well as act upon other objects
		bd.type=BodyType.DYNAMIC;
		//setting the user data to this so the body has a reference to its corresponding game object
		bd.userData=this;
		//set bodies position
		bd.position.set(position);
		//build body
		setBody(GameWorld.getGameWorld().getPhysicsWorld().createBody(bd));
		//all creatures are circular
		CircleShape dynamicCircle = new CircleShape();
		//circle radius is equal to the size of the image divided by the scale
		dynamicCircle.setRadius((image.getWidth()/2)/Utils.SCALE);
		//body is solid creating fixture
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape=dynamicCircle;
		//density
		fixtureDef.density=Utils.stamina;
		getBody().createFixture(fixtureDef);
		getBody().setLinearDamping(Utils.handling);

		//initialize base stats
		//move
		setTopSpeed(Utils.topSpeed);
		acceleration=Utils.acceleration;
		handling=Utils.handling;
		//health
		health=Utils.health;
		currHealth=health;
		stamina=Utils.stamina;
		shield=Utils.shield;
		//damage
		damage=Utils.damage;
		attackSpeed=Utils.attackSpeed;
		timeSinceLastAttack=attackSpeed;
		attackType=Utils.attackType;
		//set controller
		if(playercontrolled){
			controller = new PlayerController(this);
			attackType=1;
			health=99999999;
			currHealth=health;
		}
		else {
			controller = new AIController(this);
		}
	}
	@Override
	public void update(int delta, GameContainer gc) {
		controller.update(delta);
		if(currHealth<=0) {
			die();
		}
		timeSinceLastAttack+=delta;
	}

	private void die() {
		dropDna();
		GameWorld.getGameWorld().getGameObjects().remove(this);
		getBody().getWorld().destroyBody(this.getBody());
		try {
			this.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void dropDna() {
		
	}
	public void hit(int damage) {
		if(shield) {
			shield=false;
			return;
		}
		currHealth-=(damage);
		
	}
	public void shoot(Vec2 velocity) {
		if(velocity.length()==0) {
			return;
		}
		if(timeSinceLastAttack<attackSpeed) {
			return;
		}
		switch(attackType) {
		case 0:
			shootForward(velocity);
			break;
		case 1:
			shootForward(velocity);
			shootForward(Utils.rotateVector(velocity, Math.toRadians(1)));
			shootForward(Utils.rotateVector(velocity, Math.toRadians(-1)));
			break;
		}
		
}
	private void shootForward(Vec2 velocity) {
		Vec2 spawnLoc = new Vec2(getBody().getPosition());
		Vec2 tempAdd = new Vec2(velocity);
		tempAdd.mulLocal(image.getWidth()/2+Utils.bullet1Width/2);
		tempAdd.mulLocal(1/Utils.SCALE);
		spawnLoc.addLocal(tempAdd);
		
		try {
			new Bullet(spawnLoc, velocity, damage,id);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		timeSinceLastAttack=0;
		
	}
	public void move(Vec2 move) {
		if(move==null) {
			return;
		}
		move.normalize();
		move.mulLocal((getTopSpeed()-getBody().getLinearVelocity().length()) * acceleration);
		move.mulLocal(getBody().getMass());
		getBody().applyForce(move,getBody().getPosition());
		getBody().setTransform(getBody().getPosition(), (float) -Math.atan2(move.x,move.y));		
	}
	public float getTopSpeed() {
		return topSpeed;
	}
	public void setTopSpeed(float topSpeed) {
		this.topSpeed = topSpeed;
	}


}
