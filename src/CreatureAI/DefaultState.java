package CreatureAI;

import AI.State;
import GAME.Creature;

public class DefaultState extends State<Creature> {
	
	static DefaultState instance;
	
	@Override
	public void enter(Creature target) {
		
	}

	@Override
	public void execute(Creature target) {
		
	}

	@Override
	public void exit(Creature target) {
		
	}

	public static State<Creature> getInstance() {
		if(instance==null) {
			instance = new DefaultState();
		}
		return instance;
	}

}
