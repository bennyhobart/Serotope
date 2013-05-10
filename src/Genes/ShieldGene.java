package Genes;

import Serotope.Creature;
import Utils.Utils;

public class ShieldGene extends Gene {

	public ShieldGene(int left, int right) {
		super(left, right);
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isLeftAllele() && this.isRightAllele()) {
			creature.setShield(true);
		} 
		else {
			creature.setShield(false);
		}
	}
	
}
