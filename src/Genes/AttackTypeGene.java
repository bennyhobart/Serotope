package Genes;

import Serotope.Creature;
import Utils.Utils;

public class AttackTypeGene extends Gene {

	public AttackTypeGene(int leftAllele, int rightAllele) {
		super(leftAllele, rightAllele);
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isLeftAllele() && this.isRightAllele()) {
			creature.setAttackType(0);
		}
		if (!this.isLeftAllele() && !this.isRightAllele()) {
			creature.setAttackType(2);
		} else {
			creature.setAttackType(1);
		}
	}

}
