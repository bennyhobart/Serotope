package Genes;

import Serotope.Creature;
import Utils.Utils;

public class AttackTypeGene extends Gene {

	public AttackTypeGene(boolean leftAllele, boolean rightAllele) {
		super(leftAllele, rightAllele);
	}

	public AttackTypeGene() {
		super();
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed()){
			creature.setAttackType(1);
		} else {
			creature.setAttackType(1);
		}
	}

}
