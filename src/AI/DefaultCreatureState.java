package AI;

import java.util.ArrayList;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;

import GAME.Creature;
import Utils.Utils;

public class DefaultCreatureState extends State<Creature> {

	CreatureBehaviours behaviours;
	public DefaultCreatureState(StateMachine<Creature> target) {
		super(target);
		behaviours = (CreatureBehaviours) target.extraData;
	}

	@Override
	void enter(Creature target) {
		
	}

	@Override
	void execute(Creature target) {
		ArrayList<Creature> targets = behaviours.getLocalCreatures();
		if(targets.size()==1) {
			target.move(behaviours.wander());
		}
		else {
			Creature creature = behaviours.findClosest(targets);
			target.move(behaviours.pursuit(creature.getBody()));
			target.shoot(Utils.vectorBetween(target.getBody().getPosition(),creature.getBody().getPosition()));
			
		}
	}

	@Override
	void exit(Creature target) {
		
	}

}
