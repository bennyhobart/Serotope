package CreatureAI;

import AI.State;
import GAME.Creature;

public class FightingState extends State<Creature> {
	static FightingState instance;
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
			target.shoot(target.behaviour.seek(closest.getBody().getPosition()));
			target.move(target.behaviour.pursuit(closest.getBody()));
		}
		
		
	}

	@Override
	public void exit(Creature target) {
		// TODO Auto-generated method stub
		
	}
	public static State<Creature> getInstance() {
		if(instance==null) {
			instance = new FightingState();
		}
		return instance;
	}


}
