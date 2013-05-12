package Genes;

import Serotope.Creature;
import Utils.Utils;

public class AccelerationGene extends Gene {

	public AccelerationGene(boolean left, boolean right) {
		super(left, right);
		this.setIcon(Utils.ACCELERATION_ICON);
		this.setCreatureTag(Utils.ACCELERATION_TAG);
	}
	
	public AccelerationGene() {
		super();
		this.setIcon(Utils.ACCELERATION_ICON);
		this.setCreatureTag(Utils.ACCELERATION_TAG);
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed())
			creature.increaseStamina(Utils.ACCELERATION_BUFF);
	}
	

}
