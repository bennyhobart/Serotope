package Genes;

import Serotope.Creature;
import Utils.Utils;

public class HealthGene extends Gene {

	public HealthGene(boolean left, boolean right) {
		super(left, right);
	}

	public HealthGene() {
		super();
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed()){
			creature.increaseHealth(Utils.HEALTH_BUFF);
			creature.setCurrHealth(creature.getHealth());
		}
	}

}

