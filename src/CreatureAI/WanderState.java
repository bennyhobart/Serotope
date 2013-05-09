package CreatureAI;

import AI.State;
import GAME.Creature;

public class WanderState extends State<Creature> {
	static WanderState instance;
	@Override
	public void enter(Creature target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Creature target) {
		Creature closest = target.behaviour.findClosest(target.behaviour.getLocalCreatures());
		if(closest!=null&&Utils.Utils.lengthBetween(target.getBody().getPosition(), closest.getBody().getPosition())<10) {
			target.behaviour.stateMachine.changeState(FightingState.getInstance());
		}
		target.move(target.behaviour.wander());
		
	}

	@Override
	public void exit(Creature target) {
		// TODO Auto-generated method stub
		
	}
	public static State<Creature> getInstance() {
		if(instance==null) {
			instance = new WanderState();
		}
		return instance;
	}


}
