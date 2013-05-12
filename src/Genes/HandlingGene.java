package Genes;

import Serotope.Creature;
import Utils.Utils;

public class HandlingGene extends Gene {

	public HandlingGene(boolean leftAllele, boolean rightAllele) {
		super(leftAllele, rightAllele);
		this.setIcon(Utils.HANDLING_ICON);
		this.setCreatureTag(Utils.HANDLING_TAG);
	}

	public HandlingGene() {
		super();
		this.setIcon(Utils.HANDLING_ICON);
		this.setCreatureTag(Utils.HANDLING_TAG);
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed()){
			creature.increaseHandling(Utils.HANDLING_BUFF);
		}

	}

}
