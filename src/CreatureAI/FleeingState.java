package CreatureAI;

import AI.State;
import Serotope.Creature;

/**
 * A state where the creature runs away from other creature
 *
 */
public class FleeingState extends State<Creature> {
	static FleeingState instance;
	@Override
	public void enter(Creature target) {
		
	}

	@Override
	public void execute(Creature target) {
		// Locate the nearest creature
		Creature closest = target.behaviour.findClosestCreature(target.behaviour.getLocalCreatures());
		// If no creatures are nearby, look for DNA instead
		if(closest==null||closest.id==target.id) {
			target.behaviour.stateMachine.changeState(FindDNA.getInstance());
		}
		// Run away from closest creature
		else {
			target.setSprinting(true);
			target.move(target.behaviour.evade(closest.getBody()));
		}
	}

	@Override
	public void exit(Creature target) {
		
	}
	public static State<Creature> getInstance() {
		if(instance==null) {
			instance = new FleeingState();
		}
		return instance;
	}


}
