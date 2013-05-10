package Serotope;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.ContactEdge;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import AI.StateMachine;
import CreatureAI.DefaultState;
import Utils.Utils;

public class Creature extends GameObject {
	private DNA dna;
	// Move Variables
	private float topSpeed;
	private float acceleration;
	private float handling;
	private boolean sprinting;
	private float sprintTime;
	private float currSprint;
	private float sprintRestitution;
	private boolean tired;
	// Health Variables
	private int health;
	private int currHealth;
	private float stamina;
	private boolean shield;
	// Damage Variables
	private int damage;
	private int coolDown;
	private int timeSinceLastAttack;
	private int attackType;

	// AI Behaviours
	public CreatureBehaviours behaviour;

	public Controller controller;

	public Creature(Vec2 position, boolean playercontrolled, DNA dna)
			throws SlickException {
		super(position, new Image(Utils.CREATUREIMAGES[GameWorld
				.getRandomGenerator().nextInt(Utils.CREATUREIMAGES.length)]),
				true);

		// dna
		this.dna = dna;

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
		// all creatures are circular
		CircleShape dynamicCircle = new CircleShape();
		// circle radius is equal to the size of the image divided by the scale
		dynamicCircle.setRadius((image.getWidth() / 2) / Utils.SCALE);
		// body is solid creating fixture
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicCircle;
		// density
		fixtureDef.density = Utils.stamina;
		getBody().createFixture(fixtureDef);
		getBody().setLinearDamping(Utils.handling);

		// initialize base stats
		// move
		topSpeed = (Utils.topSpeed);
		acceleration = Utils.acceleration;
		handling = Utils.handling;
		sprintTime = Utils.sprintTime;
		currSprint = sprintTime;
		sprintRestitution = Utils.sprintRestitution;
		// health
		health = Utils.health;
		currHealth = health;
		stamina = Utils.stamina;
		shield = Utils.shield;
		// damage
		damage = Utils.damage;
		coolDown = Utils.cooldown;
		timeSinceLastAttack = coolDown;
		attackType = Utils.attackType;

		// apply DNA modifiers to base stats
		dna.buffCreature(this);

		// set controller
		if (playercontrolled) {
			health = 9999;
			currHealth = 9999;
			attackType = 1;
			controller = new PlayerController(this);
			GameWorld.getGameWorld().setPlayerId(id);
		} else {
			controller = new AIController(this);
			behaviour = new CreatureBehaviours(this,
					((AIController) controller).stateMachine);
		}
		switch (attackType) {
		case 1:
			coolDown *= Utils.NUMSHOTGUNBULLETS;
			break;
		case 2:
			coolDown /= Utils.MACHINEGUNSPEED;
			damage /= Utils.MACHINEGUNSPEED;
			break;
		default:
			break;
		}
	}

	public Creature(Vec2 position, Controller controller, DNA dna1, DNA dna2) throws SlickException {
		super(position, new Image(Utils.CREATUREIMAGES[GameWorld
		                               				.getRandomGenerator().nextInt(Utils.CREATUREIMAGES.length)]),
		                               				true);
		//implement dna merging here to initialize base stats
		this.dna=dna1;
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
				// all creatures are circular
				CircleShape dynamicCircle = new CircleShape();
				// circle radius is equal to the size of the image divided by the scale
				dynamicCircle.setRadius((image.getWidth() / 2) / Utils.SCALE);
				// body is solid creating fixture
				FixtureDef fixtureDef = new FixtureDef();
				fixtureDef.shape = dynamicCircle;
				// density
				fixtureDef.density = Utils.stamina;
				getBody().createFixture(fixtureDef);
				getBody().setLinearDamping(Utils.handling);
		// initialize base stats
				// move
				topSpeed = (Utils.topSpeed);
				acceleration = Utils.acceleration;
				handling = Utils.handling;
				sprintTime = Utils.sprintTime;
				currSprint = sprintTime;
				sprintRestitution = Utils.sprintRestitution;
				// health
				health = Utils.health;
				currHealth = health;
				stamina = Utils.stamina;
				shield = Utils.shield;
				// damage
				damage = Utils.damage;
				coolDown = Utils.cooldown;
				timeSinceLastAttack = coolDown;
				attackType = Utils.attackType;
				
				if(controller instanceof AIController ) {
					this.controller = new AIController(this);
					this.behaviour=new CreatureBehaviours(this,((AIController)this.controller).stateMachine);
				}
				else {
					this.controller=controller;
					controller.target=this;
					GameWorld.getGameWorld().setPlayer(this.id);
					health=99999;
					currHealth=99999;

				}
				
	}

	@Override
	public void update(int delta, GameContainer gc) {
		controller.update(delta);
		if (currHealth <= 0) {
			die();
		}
		timeSinceLastAttack += delta;
		if (isSprinting()) {
			currSprint -= delta;
		} else {
			currSprint += delta * sprintRestitution;
			if (tired && (currSprint >= sprintTime)) {
				tired = false;
			}
		}
	}

	protected void die() {
		doomed = true;
		dropDna();
	}

	private void dropDna() {
		DNA dna = this.getDna();
		Vec2 position = this.getBody().getPosition();
		this.dna.getBody().getPosition().set(position);
		dna.setDropped(true);
	}

	public void hit(int damage) {
		if (shield) {
			shield = false;
			return;
		}
		currHealth -= (damage);

	}

	public void shoot(Vec2 direction) {
		direction.normalize();
		if (direction.length() == 0) {
			return;
		}
		if (timeSinceLastAttack < coolDown) {
			return;
		}
		switch (attackType) {
		case 0:
			shootForward(direction);
			break;
		case 1:
			double angle = -(Utils.NUMSHOTGUNBULLETS - 1) * Math.PI
					/ (Utils.bullet1Width * 2);
			for (int i = 0; i < Utils.NUMSHOTGUNBULLETS; i++) {
				shootForwardAngle(direction, angle);
				angle += Math.PI / (Utils.bullet1Width);
			}
			break;
		case 2:
			shootForwardRandom(direction.mul(Utils.MACHINEGUNBULLETSPEED),
					Utils.MACHINEGUNSPRAY);
		}

	}

	private void shootForwardAngle(Vec2 velocity, double angle) {
		shootForward(Utils.rotateVector(velocity, angle));
		return;
	}

	private void shootForwardRandom(Vec2 velocity, double angle) {
		double randomAngle = GameWorld.getRandomGenerator().nextFloat() * angle
				- angle / 2;
		shootForwardAngle(velocity, (randomAngle));
		return;
	}

	private void shootForward(Vec2 velocity) {
		Vec2 spawnLoc = new Vec2(getBody().getPosition());
		Vec2 tempAdd = new Vec2(velocity);
		tempAdd.mulLocal(image.getWidth() / 2 + Utils.bullet1Width / 2);
		tempAdd.mulLocal(1 / Utils.SCALE);
		spawnLoc.addLocal(tempAdd);

		try {
			new Bullet(spawnLoc, velocity, damage, id, attackType);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		timeSinceLastAttack = 0;

	}

	public void move(Vec2 move) {
		if (move == null) {
			return;
		}
		move.normalize();
		if (currSprint > sprintTime) {
			currSprint = sprintTime;
		}
		if (currSprint < 0 || tired) {
			tired = true;
			setSprinting(false);
		}
		if (isSprinting()) {
			move.mulLocal(Utils.sprintModifier);
		}
		move.mulLocal((getTopSpeed() - getBody().getLinearVelocity().length())
				* acceleration);
		move.mulLocal(getBody().getMass());
		getBody().applyForce(move, getBody().getPosition());
		getBody().setTransform(getBody().getPosition(),
				(float) -Math.atan2(move.x, move.y));
	}

	public float getTopSpeed() {
		if (isSprinting()) {
			return topSpeed * Utils.sprintModifier;
		}
		return topSpeed;
	}

	public void incrementTopSpeed(float topSpeed) {
		this.topSpeed = topSpeed;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public boolean isSprinting() {
		return sprinting;
	}

	public void setSprinting(boolean sprinting) {
		this.sprinting = sprinting;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public void incrementAcceleration(float acceleration) {
		this.acceleration += acceleration;
	}

	public float getHandling() {
		return handling;
	}

	public void setHandling(float handling) {
		this.handling = handling;
	}

	public float getSprintTime() {
		return sprintTime;
	}

	public void setSprintTime(float sprintTime) {
		this.sprintTime = sprintTime;
	}

	public float getCurrSprint() {
		return currSprint;
	}

	public void setCurrSprint(float currSprint) {
		this.currSprint = currSprint;
	}

	public float getSprintRestitution() {
		return sprintRestitution;
	}

	public void setSprintRestitution(float sprintRestitution) {
		this.sprintRestitution = sprintRestitution;
	}

	public boolean isTired() {
		return tired;
	}

	public void setTired(boolean tired) {
		this.tired = tired;
	}

	public int getHealth() {
		return health;
	}

	public void incrementHealth(int health) {
		this.health += health;
	}

	public int getCurrHealth() {
		return currHealth;
	}

	public void setCurrHealth(int currHealth) {
		this.currHealth = currHealth;
	}

	public float getStamina() {
		return stamina;
	}

	public void incrementStamina(float stamina) {
		this.stamina += stamina;
	}

	public boolean isShield() {
		return shield;
	}

	public void setShield(boolean shield) {
		this.shield = shield;
	}

	public int getDamage() {
		return damage;
	}

	public void incrementDamage(int damage) {
		this.damage += damage;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void decrementCoolDown(int cooldown) {
		this.coolDown -= cooldown;
	}

//	public int getTimeSinceLastAttack() {
//		return timeSinceLastAttack;
//	}
//
//	public void setTimeSinceLastAttack(int timeSinceLastAttack) {
//		this.timeSinceLastAttack = timeSinceLastAttack;
//	}

	public int getAttackType() {
		return attackType;
	}

	public void setAttackType(int attackType) {
		this.attackType = attackType;
	}

	public DNA getDna() {
		return dna;
	}

	public void pickUpDna(DNA dna){
		try {
			new Creature(getBody().getPosition(), this.controller, dna, this.dna);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(this.controller instanceof PlayerController ){ 
			this.controller=new AIController(this);
			behaviour = new CreatureBehaviours(this, ((AIController)this.controller).stateMachine);
		}
		
	}
	
	

}
