package CreatureSpawnerAI;

import org.newdawn.slick.SlickException;

import AI.State;
import Serotope.CreatureSpawner;

public class DefaultState extends State<CreatureSpawner> {
	
	static DefaultState instance;
	
	@Override
	public void enter(CreatureSpawner target) {
		
	}

	@Override
	public void execute(CreatureSpawner target) {
		try {
			target.behaviour.spawnCreatureSomewhere();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void exit(CreatureSpawner target) {
		
	}
	public static State<CreatureSpawner> getInstance() {
		if(instance==null) {
			instance = new DefaultState();
		}
		return instance;
	}

}
