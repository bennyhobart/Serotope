package Genes;

import Serotope.Creature;
import Utils.Utils;

public class DamageGene extends Gene {

	public DamageGene(boolean leftAllele, boolean rightAllele) {
		super(leftAllele, rightAllele);
	}

	public DamageGene() {
		super();
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed())
			creature.incrementDamage(Utils.DAMAGE_BUFF);

	}

}
