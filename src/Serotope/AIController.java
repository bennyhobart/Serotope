package Serotope;

import AI.StateMachine;
import CreatureAI.DefaultState;

/**
 * State machine for AI creature behaviour states
 */
public class AIController extends Controller {
	public StateMachine<Creature> stateMachine;
	public CreatureBehaviours behaviours;

	public AIController(Creature creature) {
		super(creature);
		stateMachine = new StateMachine<Creature>(creature);
		stateMachine.setCurrentState(DefaultState.getInstance());
	}

	@Override
	public void update(int delta) {
		stateMachine.update();
	}

}
