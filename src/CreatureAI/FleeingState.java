package CreatureAI;

import AI.State;
import GAME.Creature;

public class FleeingState extends State<Creature> {
	static FleeingState instance;
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
		if(instance==null) {
			instance = new FleeingState();
		}
		return instance;
	}


}
