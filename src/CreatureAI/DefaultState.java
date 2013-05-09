package CreatureAI;

import AI.State;
import Serotope.Creature;

public class DefaultState extends State<Creature> {
	
	static DefaultState instance;
	
	@Override
	public void enter(Creature target) {
		
	}

	@Override
	public void execute(Creature target) {
		target.behaviour.stateMachine.changeState(WanderState.getInstance());	
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
