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
		Creature closest = target.behaviour.findClosest(target.behaviour.getLocalCreatures());
		if(closest==null||closest.id==target.id) {
			target.behaviour.stateMachine.changeState(WanderState.getInstance());
		}
		else {
			target.move(target.behaviour.evade(closest.getBody()));
		}
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
