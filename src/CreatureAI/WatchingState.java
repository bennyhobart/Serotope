package CreatureAI;

import AI.State;
import Serotope.Creature;

public class WatchingState extends State<Creature> {
	static WatchingState instance;

	@Override
	public void enter(Creature target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(Creature target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Creature target) {
		// TODO Auto-generated method stub

	}

	public static State<Creature> getInstance() {
		if (instance == null) {
			instance = new WatchingState();
		}
		return instance;
	}

}
