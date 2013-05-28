package Serotope;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Genes.Gene;
import Genes.ShieldGene;
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
	private float currShield;
	private int currShieldCooldown;
	private int shieldCooldown;
	// Damage Variables
	private int damage;
	private int coolDown;
	private int timeSinceLastAttack;
	private int attackType;

	// AI Behaviours
	public CreatureBehaviours behaviour;

	public Controller controller;

	/**
	 * Create a Creature object with a new Controller
	 * 
	 * @param position
	 *            Creature's position in the gameworld
	 * @param playercontrolled
	 *            True if creature is to be controlled by the player
	 * @param dna
	 *            DNA object defining the creature's traits
	 * @throws SlickException
	 */
	public Creature(Vec2 position, boolean playercontrolled, DNA dna)
			throws SlickException {
		super(position, Utils.CREATUREIMAGES[GameWorld.getRandomGenerator()
				.nextInt(Utils.CREATUREIMAGES.length)], true);

		// Set up base stats and physics stats
		initialiseBodyDef(position);
		initialiseFixtureDef();
		initialiseStats();

		// apply DNA modifiers to base stats
		this.dna = dna;
		dna.buffCreature(this);

		// set controller
		if (playercontrolled) {
			controller = new PlayerController(this);
			GameWorld.getGameWorld().setPlayer(id);

			// super creature for testing TODO delete!
			for (Gene gene : dna.getGenes()) {
				gene.setLeftAllele(true);
				gene.setRightAllele(true);
			}
			dna.buffCreature(this);

		} else {
			controller = new AIController(this);
			behaviour = new CreatureBehaviours(this,
					((AIController) controller).stateMachine);
		}
		setupAttackType();
	}

	/**
	 * Create a Creature object using an existing Controller
	 * 
	 * Use this when passing a controller from an existing creature to a new one
	 * 
	 * @param position
	 *            Creature's position in the gameworld
	 * @param controller
	 *            Controller for the creature
	 * @param dna
	 *            DNA object defining the creature's traits
	 * @throws SlickException
	 */
	public Creature(Vec2 position, Controller controller, DNA dna)
			throws SlickException {
		super(position, Utils.CREATUREIMAGES[GameWorld.getRandomGenerator()
				.nextInt(Utils.CREATUREIMAGES.length)], true);

		// Creature( position, controller, dna1);

		initialiseBodyDef(position);
		initialiseFixtureDef();
		initialiseStats();

		this.dna = dna;
		dna.buffCreature(this);
		setupAttackType();

		if (controller instanceof AIController) {
			this.controller = new AIController(this);
			this.behaviour = new CreatureBehaviours(this,
					((AIController) this.controller).stateMachine);
		} else {
			this.controller = controller;
			controller.target = this;
			GameWorld.getGameWorld().setPlayer(this.id);
		}
	}

	private void setupAttackType() {
		switch (attackType) {
		case Utils.SHOTGUN_BULLETS:
			coolDown *= Utils.NUMSHOTGUNBULLETS;
			break;
		case Utils.MACHINE_GUN_BULLETS:
			coolDown /= Utils.MACHINEGUNSPEED;
			damage /= Utils.MACHINEGUNSPEED;
			break;
		case 3:
			coolDown *= Utils.ROCKETLAUNCHERDAMAGE;
			damage *= Utils.ROCKETLAUNCHERDAMAGE;
		default:
			break;
		}
	}

	// Update function checks if the Creature has taken enough damage to cause
	// it to die.
	// Updates the creature's stats and timers (such as sprint and shield)
	@Override
	public void update(int delta, GameContainer gc) {
		controller.update(delta);
		if (currHealth <= 0) {
			die();
			//GameWorld.gameStats.incrementEnemiesKilled();
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
		if (currShieldCooldown >= shieldCooldown) {
			setCurrShield(getCurrShield() + (delta / 1000f) * getHealth()
					* Utils.SHIELDRECHARGERATE);
			if (getCurrShield() > health / 2) {
				setCurrShield(health / 2);
			}
		} else {
			currShieldCooldown += delta;
		}
	}

	// Set's doomed to true for garbage collection from gameWorld
	// Drops the creatures DNA object
	protected void die() {
		doomed = true;
		dropDna();
	}

	// Drop's the creature's DNA object into the game world at its current
	// position
	private void dropDna() {
		DNA dna = this.getDna();
		Vec2 position = this.getBody().getPosition();
		this.dna.getBody().getPosition().set(position);
		dna.setDropped(true);
		GameWorld.getGameWorld().getGameObjects().add(dna);
	}

	/**
	 * Deals damage to the creature
	 * 
	 * @param damage
	 *            Amount of damage being dealt
	 */
	public void hit(int damage) {

		if (shield) {
			currShieldCooldown = 0;
			setCurrShield(getCurrShield() - damage);
			if (getCurrShield() < 0) {
				// shield didn't block all the damage
				currHealth += getCurrShield();
				setCurrShield(0);
			}
			return;
		}
		currHealth -= (damage);

	}

	/**
	 * Shoot bullets
	 * 
	 * The creature shoots bullets based on its attackType and damage attributes
	 * 
	 * @param direction
	 *            The direction the bullets are being shot
	 */
	public void shoot(Vec2 direction) {
		direction.normalize();
		if (direction.length() == 0) {
			return;
		}
		if (timeSinceLastAttack < coolDown) {
			return;
		}
		switch (attackType) {
		case Utils.DEFAULT_ATTACK:
			shootForward(direction);
			break;
		case Utils.SHOTGUN_BULLETS:
			double angle = -(Utils.NUMSHOTGUNBULLETS - 1) * Math.PI
					/ (Utils.bullet1Width * 2);
			for (int i = 0; i < Utils.NUMSHOTGUNBULLETS; i++) {
				shootForwardAngle(direction, angle);
				angle += Math.PI / (Utils.bullet1Width);
			}
			break;
		case Utils.MACHINE_GUN_BULLETS:
			shootForwardRandom(direction.mul(Utils.MACHINEGUNBULLETSPEED),
					Utils.MACHINEGUNSPRAY);
		case Utils.ROCKET_BULLETS:
			shootForward(direction.mul(Utils.ROCKETLAUNCHERSPEED));
		}

	}

	// Shoot forward at the givin angle
	private void shootForwardAngle(Vec2 velocity, double angle) {
		shootForward(Utils.rotateVector(velocity, angle));
		return;
	}

	// Shoot forward, rotated slightly at a random angle
	private void shootForwardRandom(Vec2 velocity, double angle) {
		double randomAngle = GameWorld.getRandomGenerator().nextFloat() * angle
				- angle / 2;
		shootForwardAngle(velocity, (randomAngle));
		return;
	}

	// Shoot directly in front of the creature with bullet speed based on
	// the given direction

	private void shootForward(Vec2 velocity) {
		Vec2 spawnLoc = new Vec2(getBody().getPosition());
		Vec2 tempAdd = new Vec2(velocity);
		tempAdd.mulLocal(image.getWidth() / 2
				+ Utils.BULLETIMAGES[attackType].getWidth());
		tempAdd.mulLocal(1 / Utils.SCALE);
		spawnLoc.addLocal(tempAdd);

		try {
			new Bullet(spawnLoc, velocity.mul(Utils.BULLET_VELOCITY).add(
					getBody().getLinearVelocity().mul(
							Utils.BULLET_VELOCITY_INHERITENCE)), damage, id,
					attackType);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		timeSinceLastAttack = 0;

	}

	/**
	 * Moves the creature
	 * 
	 * Moves the creature in the given direction, using velocity and
	 * acceleration derived from the creature's attributes, and whether the
	 * creature is currently sprinting.
	 * 
	 * @param move
	 *            The direction to move
	 */
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
			move.mulLocal(Utils.SPRINT_MODIFIER);
		}
		move.mulLocal((getTopSpeed() - getBody().getLinearVelocity().length())
				* acceleration);
		move.mulLocal(getBody().getMass());
		getBody().applyForce(move, getBody().getPosition());
		getBody().setTransform(getBody().getPosition(),
				(float) -Math.atan2(move.x, move.y));
	}

	public void pickUpDna(DNA dna) {
		try {
			DNA newDna = mergeDna(this.getDna(), dna);
			new Creature(getBody().getPosition(), this.controller, newDna);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		if (this.controller instanceof PlayerController) {
			this.controller = new AIController(this);
			behaviour = new CreatureBehaviours(this,
					((AIController) this.controller).stateMachine);
		}

	}

	// randomly picks one allele from each of the two given dna's for
	// each gene. Then joins them together to form a new dna object.
	private DNA mergeDna(DNA dna1, DNA dna2) throws SlickException {
		DNA dna = new DNA();
		for (int i = 1; i < dna.getGenes().size(); i++) {
			boolean left = dna1.getGenes().get(i).getRandomAllele();
			boolean right = dna2.getGenes().get(i).getRandomAllele();
			dna.getGenes().get(i).setLeftAllele(left);
			dna.getGenes().get(i).setRightAllele(right);
		}
		return dna;
	}

	// Renders the creature's image, overlaid with icons for any
	// genes it expresses.
	void render(Graphics g, float xrender, float yrender) {
		super.render(g, xrender, yrender);
		ArrayList<Gene> genes = this.getDna().getGenes();
		for (Gene gene : genes) {
			if (gene instanceof ShieldGene) {
				if (!shield || currShield <= 0) {
					continue;
				}
			}
			if (gene.isExpressed()) {
				if (gene.getCreatureTag() != null) {
					gene.getCreatureTag().setRotation(
							(float) (-getBody().getAngle() * 180 / Math.PI));
					gene.getCreatureTag().drawCentered(xrender, yrender);
				}
			}
		}
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
		// circle radius is equal to the size of the image divided by the scale
		dynamicCircle.setRadius((image.getWidth() / 2) / Utils.SCALE);
		// body is solid creating fixture
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicCircle;
		// density
		fixtureDef.density = Utils.STAMINA;
		getBody().createFixture(fixtureDef);
		getBody().setLinearDamping(Utils.HANDLING);
	}

	private void initialiseStats() {
		// movement
		topSpeed = (Utils.TOP_SPEED);
		acceleration = Utils.ACCELERATION;
		handling = Utils.HANDLING;
		sprintTime = Utils.SPRINT_TIME;
		currSprint = sprintTime;
		sprintRestitution = Utils.SPRINT_RESTITUTION;
		// health
		health = Utils.HEALTH;
		currHealth = health;
		stamina = Utils.STAMINA;
		shield = Utils.SHIELD;
		currShieldCooldown = Utils.SHIELD_COOLDOWN;
		shieldCooldown = Utils.SHIELD_COOLDOWN;
		// damage
		damage = Utils.DAMAGE;
		coolDown = Utils.COOLDOWN;
		timeSinceLastAttack = coolDown;
		attackType = Utils.ATTACK_TYPE;
	}

	// Getters and Setters

	public float getTopSpeed() {
		if (isSprinting()) {
			return topSpeed * Utils.SPRINT_MODIFIER;
		}
		return topSpeed;
	}

	public void increaseTopSpeed(int modifier) {
		this.topSpeed *= modifier;
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

	public void increaseAcceleration(int modifier) {
		this.acceleration *= modifier;
	}

	public float getHandling() {
		return handling;
	}

	public void increaseHandling(int modifier) {
		this.handling *= modifier;
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

	public void increaseHealth(int modifier) {
		this.health *= modifier;
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

	public void increaseStamina(int modifier) {
		this.stamina *= modifier;
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

	public void increaseDamage(int modifier) {
		this.damage *= modifier;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void decreaseCoolDown(int modifier) {
		this.coolDown /= modifier;
	}

	public int getTimeSinceLastAttack() {
		return timeSinceLastAttack;
	}

	public void setTimeSinceLastAttack(int timeSinceLastAttack) {
		this.timeSinceLastAttack = timeSinceLastAttack;
	}

	public int getAttackType() {
		return attackType;
	}

	public void incrementAttackType() {
		this.attackType += 1;
	}

	public DNA getDna() {
		return dna;
	}

	public ArrayList<Gene> getExpressedGenes() {
		ArrayList<Gene> expressedGenes = new ArrayList<Gene>();
		for (Gene gene : this.getDna().getGenes()) {
			if (gene.isExpressed()) {
				expressedGenes.add(gene);
			}
		}
		return expressedGenes;
	}

	/**
	 * @return the currShield
	 */
	public float getCurrShield() {
		return currShield;
	}

	/**
	 * @param currShield
	 *            the currShield to set
	 */
	public void setCurrShield(float currShield) {
		this.currShield = currShield;
	}

}
