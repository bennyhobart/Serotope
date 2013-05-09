package Genes;

import Serotope.Creature;
import Serotope.Gene;
import Utils.Utils;

public class HealthGene extends Gene {

	public HealthGene(int i, int j) {
		super(i,j);
	}

	@Override
	public void buffCreature(Creature creature) {
		int modifier = this.getLeftAllele() + this.getRightAllele();
		//creature.health += modifier * Utils.HEALTH_BUFF;
		
	}

}

