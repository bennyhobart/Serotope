package Genes;

import Serotope.Creature;
import Utils.Utils;

/**
 * Modifies a creature's damage output
 *
 */
public class DamageGene extends Gene {

	public DamageGene(boolean leftAllele, boolean rightAllele) {
		super(leftAllele, rightAllele);
		this.setIcon(Utils.DAMAGE_ICON);
		this.setCreatureTag(Utils.DAMAGE_TAG);
	}

	public DamageGene() {
		super();
		this.setIcon(Utils.DAMAGE_ICON);
		this.setCreatureTag(Utils.DAMAGE_TAG);
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed())
			creature.increaseDamage(Utils.DAMAGE_BUFF);

	}

}
