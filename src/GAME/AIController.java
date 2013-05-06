package GAME;

import AI.CreatureStateMachine;
import AI.StateMachine;
import AI.SteeringBehaviours;


public class AIController extends Controller {
	CreatureStateMachine stateMachine;
	SteeringBehaviours steeringBehaviours;
	Creature targetCreature;
	public AIController(Creature creature) {
		super(creature);
		steeringBehaviours = new SteeringBehaviours(target);
		stateMachine = new CreatureStateMachine(target);
		targetCreature=(Creature) GameWorld.getGameWorld().getGameObjects().get(0);
	}

	@Override
	public void update(int delta) {
		//stateMachine.update();
		//target.move(steeringBehaviours.wander());
		//target.move(steeringBehaviours.seek(targetCreature.getBody().getPosition()));
		//target.move(steeringBehaviours.flee(targetCreature.getBody().getPosition()));
		//target.move(steeringBehaviours.arrive(targetCreature.getBody().getPosition()));
		//target.move(steeringBehaviours.evade(targetCreature.getBody()));
		//target.move(steeringBehaviours.pursuit(targetCreature.getBody()));
	}

	@Override
	void shoot(int delta) {
		
	}

	@Override
	void move(int delta) {
		
	}
	
	
}

