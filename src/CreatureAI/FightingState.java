package CreatureAI;

import AI.State;
import Serotope.Creature;
/**
 * 
 * @author BenedictHobart
 *The state representing a Creature "Fighting" another creature
 */
public class FightingState extends State<Creature> {
	static FightingState instance;
	@Override
	public void enter(Creature target) {
		Creature closest = target.behaviour.findClosest(target.behaviour.getLocalCreatures());
		target.behaviour.stateTarget=closest;

	}

	@Override
	public void execute(Creature target) {
		//if the target is dead the Creature should cease fighting and return to wandering about the map
		if(target.behaviour.stateTarget.doomed) {
			target.behaviour.stateMachine.changeState(WanderState.getInstance());
			return;
		}
		//if the distance between the creature and its target is greater than 8m the creature should cease fighting and return to wandering
		if(Utils.Utils.lengthBetween(target.getBody().getPosition(),target.behaviour.stateTarget.getBody().getPosition())>8) {
			target.behaviour.stateMachine.changeState(WanderState.getInstance());
			return;
		}
		//if the creature is below 40% health it should run away
		if(target.getCurrHealth()<target.getHealth()*0.4f) {
			target.behaviour.stateMachine.changeState(FleeingState.getInstance());
			return;
		}	
		
		//the creature will shoot at the target creature and move towards it
		target.shoot(target.behaviour.seek(target.behaviour.stateTarget.getBody().getPosition()));
		target.move(target.behaviour.pursuit(target.behaviour.stateTarget.getBody()));
		
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
