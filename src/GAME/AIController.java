package GAME;

import java.util.ArrayList;

import AI.State;
import AI.StateMachine;
import AI.SteeringBehaviours;
import AI.Transition;


public class AIController extends Controller {
	StateMachine<Creature> stateMachine;
	SteeringBehaviours steeringBehaviours;
	Creature targetCreature;
	public AIController(Creature creature) {
		super(creature);
		steeringBehaviours = new SteeringBehaviours(target);
		ArrayList<Transition<Creature>> transitions = null;
		State<Creature> startState = null;
		stateMachine = new StateMachine<Creature>(target, transitions, startState);
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
		target.move(steeringBehaviours.pursuit(targetCreature.getBody()));
	}

	@Override
	void shoot(int delta) {
		
	}

	@Override
	void move(int delta) {
		
	}
	
	
}

