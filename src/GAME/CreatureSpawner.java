package GAME;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import AI.StateMachine;
import CreatureSpawnerAI.DefaultState;



public class CreatureSpawner extends GameObject {
	CreatureSpawnerBehaviours behaviour;
	StateMachine<CreatureSpawner> stateMachine;
	Creature target;
	CreatureSpawner(Vec2 position) throws SlickException {
		super(position,null, false);
		behaviour = new CreatureSpawnerBehaviours(this);
		target = (Creature) GameWorld.getGameWorld().getGameObject(0);
		stateMachine = new StateMachine<CreatureSpawner>(this);
		stateMachine.setCurrentState(DefaultState.getInstance());
	}

	@Override
	public void update(int delta, GameContainer gc) {
		stateMachine.update();
	}

}
