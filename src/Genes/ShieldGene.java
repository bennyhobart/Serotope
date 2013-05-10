package Genes;

import Serotope.Creature;
import Utils.Utils;

public class ShieldGene extends Gene {

	public ShieldGene(boolean left, boolean right) {
		super(left, right);
	}

	public ShieldGene() {
		super();
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
