package Serotope;

import java.util.ArrayList;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import AI.StateMachine;
import Utils.Utils;

;
public class CreatureBehaviours {
	public Creature target;
	public StateMachine<Creature> stateMachine;
	// state timer
	public int timer;
	public Creature stateTarget;
	// used by wander
	float wanderRadius;
	float wanderDistance;
	float wanderJitter;
	Vec2 wanderTarget;

	public CreatureBehaviours(Creature target,
			StateMachine<Creature> stateMachine) {
		this.target = target;
		timer = 0;
		this.stateMachine = stateMachine;
		wanderRadius = Utils.WANDERRADIUS;
		wanderDistance = Utils.WANDERDISTANCE;
		wanderJitter = Utils.WANDERJITTER;
		wanderTarget = randomUnitVector();
	}

	public ArrayList<Creature> getLocalCreatures() {
		AABB zone = new AABB(target.getBody().getPosition()
				.add(new Vec2(-Utils.AISEEK, -Utils.AISEEK)), target.getBody()
				.getPosition().add(new Vec2(Utils.AISEEK, Utils.AISEEK)));
		ArrayList<Creature> targets = Utils.getCreatures(Utils
				.getGameObjectsAABB(zone));
		return targets;
	}

	public ArrayList<DNA> getLocalDNA() {
		AABB zone = new AABB(target.getBody().getPosition()
				.add(new Vec2(-Utils.AISEEK, -Utils.AISEEK)), target.getBody()
				.getPosition().add(new Vec2(Utils.AISEEK, Utils.AISEEK)));
		ArrayList<DNA> targets = Utils.getDNA(Utils.getGameObjectsAABB(zone));
		return targets;
	}

	public Vec2 seek(Vec2 position) {
		Vec2 targetVelocity = position.sub(target.getBody().getPosition());
		targetVelocity.mulLocal(target.getTopSpeed());
		return targetVelocity.sub(target.getBody().getLinearVelocity());
	}

	public Vec2 flee(Vec2 position) {
		Vec2 targetVelocity = target.getBody().getPosition().sub(position);
		targetVelocity.mulLocal(target.getTopSpeed());
		return targetVelocity.sub(target.getBody().getLinearVelocity());
	}

	public Vec2 arrive(Vec2 position) {
		Vec2 vecBetween = position.sub(target.getBody().getPosition());
		float lengthBetween = vecBetween.length();
		if (lengthBetween > 0) {
			float speed = lengthBetween / (0.3f);
			speed = Math.min(speed, target.getTopSpeed());
			Vec2 targetVelocity = vecBetween.mul(speed).mul(1 / lengthBetween);
			return (targetVelocity.sub(target.getBody().getLinearVelocity()));
		}
		return new Vec2(0, 0);
	}

	public Vec2 randomUnitVector() {
		float random1 = GameWorld.getRandomGenerator().nextFloat() * 2 - 1;
		float random2 = GameWorld.getRandomGenerator().nextFloat() * 2 - 1;
		return new Vec2(random1, random2);
	}

	public Vec2 wander() {

		wanderTarget.normalize();
		wanderTarget.addLocal(randomUnitVector().mul(wanderJitter));
		wanderTarget.normalize();
		wanderTarget.mulLocal(wanderRadius);
		Vec2 temp = target.getBody().getLinearVelocity().clone();
		temp.normalize();
		Vec2 worldSpace = wanderTarget.add(temp.mul(wanderDistance));
		return worldSpace;
	}

	public Vec2 pursuit(Body body) {
		Vec2 toEvader = body.getPosition().sub(target.getBody().getPosition());
		float timeToInteract = toEvader.length()
				/ (target.getTopSpeed() + body.getLinearVelocity().length());
		return seek(body.getPosition().add(
				body.getLinearVelocity().mul(timeToInteract)));
	}

	public Vec2 evade(Body body) {
		Vec2 toEvader = body.getPosition().sub(target.getBody().getPosition());
		float timeToInteract = toEvader.length()
				/ (target.getTopSpeed() + body.getLinearVelocity().length());
		return flee(body.getPosition().add(
				body.getLinearVelocity().mul(timeToInteract)));
	}

	public Creature findClosestCreature(ArrayList<Creature> targets) {
		Creature closest = null;
		float distance = -1;
		for (int i = 0; i < targets.size(); i++) {
			float temp = Utils.lengthBetween(target.getBody().getPosition(),
					targets.get(i).getBody().getPosition());
			if ((temp < distance || distance == -1)
					&& targets.get(i).id != target.id) {
				closest = targets.get(i);
				distance = temp;
			}
		}
		return closest;
	}

	public DNA findClosestDNA(ArrayList<DNA> targets) {
		DNA closest = null;
		float distance = -1;
		for (int i = 0; i < targets.size(); i++) {
			float temp = Utils.lengthBetween(target.getBody().getPosition(),
					targets.get(i).getBody().getPosition());
			if ((temp < distance || distance == -1)) {
				closest = targets.get(i);
				distance = temp;
			}
		}
		return closest;
	}

}
