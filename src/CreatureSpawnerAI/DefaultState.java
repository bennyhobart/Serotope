package CreatureSpawnerAI;

import AI.State;
import GAME.CreatureSpawner;

public class DefaultState extends State<CreatureSpawner> {
	
	static DefaultState instance;
	
	@Override
	public void enter(CreatureSpawner target) {
		
	}

	@Override
	public void execute(CreatureSpawner target) {
		
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
