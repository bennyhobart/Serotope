package Serotope;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import AI.StateMachine;
import CreatureSpawnerAI.DefaultState;



public class CreatureSpawner extends GameObject {
	public CreatureSpawnerBehaviours behaviour;
	StateMachine<CreatureSpawner> stateMachine;
	int lastSpawnTime;
	int spawnTime;
	Creature target;
	CreatureSpawner(Vec2 position) throws SlickException {
		super(position,null, false);
		behaviour = new CreatureSpawnerBehaviours(this);
		target = (Creature) GameWorld.getGameWorld().getGameObject(0);
		stateMachine = new StateMachine<CreatureSpawner>(this);
		stateMachine.setCurrentState(DefaultState.getInstance());
		spawnTime=Utils.Utils.spawnTime;
	}

	@Override
	public void update(int delta, GameContainer gc) {
		target=GameWorld.getGameWorld().getPlayer();
		stateMachine.update();
		lastSpawnTime+=delta;
	}

	public void spawn(Vec2 location) {
		if(lastSpawnTime<spawnTime) {
			return;
		}
		lastSpawnTime=0;
		try {
			new Creature(location, false);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
