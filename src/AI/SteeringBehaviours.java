package AI;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import GAME.Creature;
import GAME.GameWorld;

public class SteeringBehaviours {
	Creature target;
	
	//used by wander
	float wanderRadius;
	float wanderDistance;
	float wanderJitter;
	Vec2 wanderTarget;
	public SteeringBehaviours(Creature target) {
		this.target=target;
		wanderRadius = Utils.Utils.WANDERRADIUS;
		wanderDistance = Utils.Utils.WANDERDISTANCE;
		wanderJitter = Utils.Utils.WANDERJITTER;
		wanderTarget = randomUnitVector();
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
		if(lengthBetween>0) {
			float speed = lengthBetween/(0.3f);
			speed = Math.min(speed,target.getTopSpeed());
			Vec2 targetVelocity = vecBetween.mul(speed).mul(1/lengthBetween);
			return (targetVelocity.sub(target.getBody().getLinearVelocity()));
		}
		return new Vec2(0,0);
	}
	
	public Vec2 randomUnitVector() {
		float random1 = GameWorld.getRandomGenerator().nextFloat()*2-1;
		float random2 = GameWorld.getRandomGenerator().nextFloat()*2-1;
		return new Vec2(random1,random2);
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
		float timeToInteract = toEvader.length()/(target.getTopSpeed()+body.getLinearVelocity().length());
		return seek(body.getPosition().add(body.getLinearVelocity().mul(timeToInteract)));		
	}
	public Vec2 evade(Body body) {
		Vec2 toEvader = body.getPosition().sub(target.getBody().getPosition());
		float timeToInteract = toEvader.length()/(target.getTopSpeed()+body.getLinearVelocity().length());
		return flee(body.getPosition().add(body.getLinearVelocity().mul(timeToInteract)));
	}
	
}
