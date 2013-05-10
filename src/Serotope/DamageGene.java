package Serotope;

import Utils.Utils;
import Genes.Gene;

public class DamageGene extends Gene {

	public DamageGene(int leftAllele, int rightAllele) {
		super(leftAllele, rightAllele);
	}

	@Override
	public void buffCreature(Creature creature) {
		int modifier = this.getLeftAllele() + this.getRightAllele();
		creature.incrementDamage(modifier * Utils.DAMAGE_BUFF);

	}

}
