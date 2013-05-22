package Genes;

import Serotope.Creature;

/**
 * Modifies a creature's attack type
 *
 */
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
			creature.incrementAttackType();
		}
	}

}
