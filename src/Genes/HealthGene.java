package Genes;

import GAME.Creature;
import GAME.Gene;
import Utils.Utils;

public class HealthGene extends Gene {

	@Override
	public void buffCreature(Creature creature) {
		int modifier = this.getLeftAllele() + this.getRightAllele();
		creature.health += modifier * Utils.HEALTH_BUFF;
		
	}

}

