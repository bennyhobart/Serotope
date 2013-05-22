package CreatureAI;

import AI.State;
import Serotope.Creature;

/**
 * A state that causes the creature to randomly move around the game world
 *
 */
public class WanderState extends State<Creature> {
	static WanderState instance;

	@Override
	public void enter(Creature target) {

	}

	@Override
	public void execute(Creature target) {
		// Locate the closest creature to itself
		Creature closest = target.behaviour
				.findClosestCreature(target.behaviour.getLocalCreatures());
		// If there is a nearby creature, change to fighting state
		if (closest != null
				&& Utils.Utils.lengthBetween(target.getBody().getPosition(),
						closest.getBody().getPosition()) < 10) {
			target.behaviour.stateMachine.changeState(FightingState
					.getInstance());
		}
		// Move randomly
		target.move(target.behaviour.wander());

	}

	@Override
	public void exit(Creature target) {

	}

	public static State<Creature> getInstance() {
		if (instance == null) {
			instance = new WanderState();
		}
		return instance;
	}

}
