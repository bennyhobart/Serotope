package Serotope;

import org.jbox2d.common.Vec2;

import Menu.gPanel;

public class CreatureSpawnerBehaviours {

	CreatureSpawner target;
	public CreatureSpawnerBehaviours(CreatureSpawner creatureSpawner) {
		target = creatureSpawner;
	}
	
	public void spawnCreatureSomewhere() {
		float radius = Math.max(gPanel.PWIDTH, gPanel.PHEIGHT);
		radius/=Utils.Utils.SCALE;
		Vec2 randomDir = Utils.Utils.randomUnitVector();
		randomDir.mulLocal(radius);
		randomDir.addLocal(target.target.getBody().getPosition());
		target.spawn(randomDir);
		return;
	}


}
