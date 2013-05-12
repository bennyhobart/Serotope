package CreatureAI;

import Serotope.Creature;
import Serotope.DNA;
import AI.State;

public class FindDNA extends State<Creature> {
	static FindDNA instance;
	@Override
	public void enter(Creature target) {

	}

	@Override
	public void execute(Creature target) {
		DNA closest = target.behaviour.findClosestDNA(target.behaviour.getLocalDNA());
		if(closest!=null) {
			target.move(target.behaviour.seek(closest.getBody().getPosition()));
		}
		else {
			target.move(target.behaviour.wander());
		}

	}

	@Override
	public void exit(Creature target) {
		
	}

	public static State<Creature> getInstance() {
		if(instance==null) {
			instance = new FindDNA();
		}
		return instance;
	}

}
