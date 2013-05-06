package AI;


import GAME.Creature;

public class CreatureStateMachine extends StateMachine<Creature> {
	
	public CreatureStateMachine(Creature target) {		
		this.target=target;
		currentState=new DefaultCreatureState();
		currentState.enter(target);
	}

}
