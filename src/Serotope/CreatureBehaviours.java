package Serotope;

import java.util.ArrayList;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import AI.StateMachine;
import Utils.Utils;

;
public class CreatureBehaviours {

	public Creature thisCreature;
	public StateMachine<Creature> stateMachine;
	// state timer
	public int timer;
	public Creature stateTarget;
	// used by wander
	float wanderRadius;
	float wanderDistance;
	float wanderJitter;
	Vec2 wanderTargetPosition;

	/**
	 * Initialise a creatures behaviours
	 * 
	 * @param target
	 *            The creature this is being applied to
	 * @param stateMachine
	 *            The statemachine responsible for the creature's states
	 */
	public CreatureBehaviours(Creature target,
			StateMachine<Creature> stateMachine) {
		this.thisCreature = target;
		timer = 0;
		this.stateMachine = stateMachine;
		wanderRadius = Utils.WANDERRADIUS;
		wanderDistance = Utils.WANDERDISTANCE;
		wanderJitter = Utils.WANDERJITTER;
		wanderTargetPosition = randomUnitVector();
	}

	/**
	 * @return list of nearby creatures
	 */
	public ArrayList<Creature> getLocalCreatures() {
		AABB zone = new AABB(thisCreature.getBody().getPosition()
				.add(new Vec2(-Utils.AISEEK, -Utils.AISEEK)), thisCreature
				.getBody().getPosition()
				.add(new Vec2(Utils.AISEEK, Utils.AISEEK)));
		ArrayList<Creature> targets = Utils.getCreatures(Utils
				.getGameObjectsAABB(zone));
		return targets;
	}

	/**
	 * @return list of nearby DNA objects
	 */
	public ArrayList<DNA> getLocalDNA() {
		AABB zone = new AABB(thisCreature.getBody().getPosition()
				.add(new Vec2(-Utils.AISEEK, -Utils.AISEEK)), thisCreature
				.getBody().getPosition()
				.add(new Vec2(Utils.AISEEK, Utils.AISEEK)));
		ArrayList<DNA> targets = Utils.getDNA(Utils.getGameObjectsAABB(zone));
		return targets;
	}

	/**
	 * Seek a given location
	 * 
	 * @param position
	 *            Co-ordinates of the position being seeked
	 * @return velocity vector aimed at given position
	 */
	public Vec2 seek(Vec2 position) {
		Vec2 targetVelocity = position
				.sub(thisCreature.getBody().getPosition());
		targetVelocity.mulLocal(thisCreature.getTopSpeed());
		return targetVelocity.sub(thisCreature.getBody().getLinearVelocity());
	}

	/**
	 * Flee from a given location
	 * 
	 * @param position
	 *            Co-ordinates of the position being fleed
	 * @return velocity vector aimed away from the position
	 */
	public Vec2 flee(Vec2 position) {
		Vec2 targetVelocity = thisCreature.getBody().getPosition()
				.sub(position);
		targetVelocity.mulLocal(thisCreature.getTopSpeed());
		return targetVelocity.sub(thisCreature.getBody().getLinearVelocity());
	}

	// TODO: Delete

	// public Vec2 arrive(Vec2 position) {
	// Vec2 vecBetween = position.sub(thisCreature.getBody().getPosition());
	// float lengthBetween = vecBetween.length();
	// if (lengthBetween > 0) {
	// float speed = lengthBetween / (0.3f);
	// speed = Math.min(speed, thisCreature.getTopSpeed());
	// Vec2 targetVelocity = vecBetween.mul(speed).mul(1 / lengthBetween);
	// return (targetVelocity.sub(thisCreature.getBody().getLinearVelocity()));
	// }
	// return new Vec2(0, 0);
	// }

	/**
	 * @return A random vector of unit length
	 */
	public Vec2 randomUnitVector() {
		float random1 = GameWorld.getRandomGenerator().nextFloat() * 2 - 1;
		float random2 = GameWorld.getRandomGenerator().nextFloat() * 2 - 1;
		return new Vec2(random1, random2);
	}

	/**
	 * 
	 * @return A velocity vector aimed at a random location
	 */
	public Vec2 wander() {

		wanderTargetPosition.normalize();
		wanderTargetPosition.addLocal(randomUnitVector().mul(wanderJitter));
		wanderTargetPosition.normalize();
		wanderTargetPosition.mulLocal(wanderRadius);
		Vec2 temp = thisCreature.getBody().getLinearVelocity().clone();
		temp.normalize();
		Vec2 worldSpace = wanderTargetPosition.add(temp.mul(wanderDistance));
		return worldSpace;
	}

	/**
	 * Follow a given body in the game world
	 * 
	 * @param body
	 *            The object being persuaded
	 * @return A velocity vector aimed at that body
	 */
	public Vec2 pursuit(Body body) {
		Vec2 toEvader = body.getPosition().sub(
				thisCreature.getBody().getPosition());
		float timeToInteract = toEvader.length()
				/ (thisCreature.getTopSpeed() + body.getLinearVelocity()
						.length());
		return seek(body.getPosition().add(
				body.getLinearVelocity().mul(timeToInteract)));
	}

	/**
	 * Move away from a given body in the game world
	 * 
	 * @param body
	 *            The object being evaded
	 * @return A velocity vector aimed away from that body
	 */
	public Vec2 evade(Body body) {
		Vec2 toEvader = body.getPosition().sub(
				thisCreature.getBody().getPosition());
		float timeToInteract = toEvader.length()
				/ (thisCreature.getTopSpeed() + body.getLinearVelocity()
						.length());
		return flee(body.getPosition().add(
				body.getLinearVelocity().mul(timeToInteract)));
	}

	/**
	 * @param targets
	 *            A list of creatures that is to be searched for a nearby one
	 * @return The closest creature to this one
	 */
	public Creature findClosestCreature(ArrayList<Creature> targets) {
		Creature closest = null;
		float distance = -1;
		for (int i = 0; i < targets.size(); i++) {
			float temp = Utils.lengthBetween(thisCreature.getBody()
					.getPosition(), targets.get(i).getBody().getPosition());
			if ((temp < distance || distance == -1)
					&& targets.get(i).id != thisCreature.id) {
				closest = targets.get(i);
				distance = temp;
			}
		}
		return closest;
	}

	/**
	 * @param targets
	 *            A list of DNA that is to be searched for a nearby one
	 * @return The closest DNA to this one
	 */
	public DNA findClosestDNA(ArrayList<DNA> targets) {
		DNA closest = null;
		float distance = -1;
		for (int i = 0; i < targets.size(); i++) {
			float temp = Utils.lengthBetween(thisCreature.getBody()
					.getPosition(), targets.get(i).getBody().getPosition());
			if ((temp < distance || distance == -1)) {
				closest = targets.get(i);
				distance = temp;
			}
		}
		return closest;
	}

}
