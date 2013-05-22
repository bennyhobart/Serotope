package Genes;

import Serotope.Creature;
import Utils.Utils;

/**
 * Determine's if a creature has a shield to absorb damage
 *
 */
public class ShieldGene extends Gene {

	public ShieldGene(boolean left, boolean right) {
		super(left, right);
		this.setIcon(Utils.SHIELD_ICON);
		this.setCreatureTag(Utils.SHIELD_TAG);
	}

	public ShieldGene() {
		super();
		this.setIcon(Utils.SHIELD_ICON);
		this.setCreatureTag(Utils.SHIELD_TAG);
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed()) {
			creature.setShield(true);
		} 
		else {
			creature.setShield(false);
		}
	}
	
}
