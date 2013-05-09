package CreatureSpawnerAI;

import AI.State;
import GAME.CreatureSpawner;

public class Challenge extends State<CreatureSpawner> {
	
	static Challenge instance;
	
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
			instance = new Challenge();
		}
		return instance;
	}

}
