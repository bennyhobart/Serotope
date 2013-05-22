package CreatureAI;

import Serotope.Creature;
import Serotope.DNA;
import AI.State;

/**
 * A creature state that causes them to look for DNA in the game world
 * 
 */
public class FindDNA extends State<Creature> {
	static FindDNA instance;

	@Override
	public void enter(Creature target) {

	}

	@Override
	public void execute(Creature target) {
		// Locate the nearest DNA object
		DNA closest = target.behaviour.findClosestDNA(target.behaviour
				.getLocalDNA());
		// Move towards the DNA
		if (closest != null) {
			target.move(target.behaviour.seek(closest.getBody().getPosition()));
		} else {
			target.move(target.behaviour.wander());
		}

	}

	@Override
	public void exit(Creature target) {

	}

	public static State<Creature> getInstance() {
		if (instance == null) {
			instance = new FindDNA();
		}
		return instance;
	}

}
