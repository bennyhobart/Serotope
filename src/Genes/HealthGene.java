package Genes;

import Serotope.Creature;
import Utils.Utils;

public class HealthGene extends Gene {

	public HealthGene(int left, int right) {
		super(left, right);
	}

	@Override
	public void buffCreature(Creature creature) {
		int modifier = this.getLeftAllele() + this.getRightAllele();
		creature.incrementHealth(modifier * Utils.HEALTH_BUFF);
		creature.setCurrHealth(creature.getHealth());
	}

}

