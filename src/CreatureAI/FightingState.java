package CreatureAI;

import AI.State;
import Serotope.Creature;

public class FightingState extends State<Creature> {
	static FightingState instance;
	@Override
	public void enter(Creature target) {
		Creature closest = target.behaviour.findClosest(target.behaviour.getLocalCreatures());
		target.behaviour.stateTarget=closest;

	}

	@Override
	public void execute(Creature target) {
		if(target.behaviour.stateTarget.doomed) {
			target.behaviour.stateMachine.changeState(WanderState.getInstance());
			return;
		}
		if(Utils.Utils.lengthBetween(target.getBody().getPosition(),target.behaviour.stateTarget.getBody().getPosition())>8) {
			target.behaviour.stateMachine.changeState(WanderState.getInstance());
			return;
		}
		if(target.getCurrHealth()<target.getHealth()*0.4f) {
			target.behaviour.stateMachine.changeState(FleeingState.getInstance());
			return;
		}
		else {
			target.shoot(target.behaviour.seek(target.behaviour.stateTarget.getBody().getPosition()));
			target.move(target.behaviour.pursuit(target.behaviour.stateTarget.getBody()));
		}
		
	}

	@Override
	public void exit(Creature target) {
		target.behaviour.stateTarget=null;
	}
	public static State<Creature> getInstance() {
		if(instance==null) {
			instance = new FightingState();
		}
		return instance;
	}


}
