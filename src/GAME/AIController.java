package GAME;

import AI.CreatureBehaviours;
import AI.DefaultCreatureState;
import AI.StateMachine;


public class AIController extends Controller {
	StateMachine<Creature> stateMachine;
	public CreatureBehaviours behaviours;
	public AIController(Creature creature) {
		super(creature);
		behaviours = new CreatureBehaviours(creature);
		stateMachine = new StateMachine<Creature>(creature, null, behaviours);
		stateMachine.changeState(new DefaultCreatureState(stateMachine));
		
		
	}
	@Override
	public void update(int delta) {
		stateMachine.update();
	}

	@Override
	void shoot(int delta) {
		
	}

	@Override
	void move(int delta) {
		
	}
	
	
}

