package Genes;

import Serotope.Creature;
import Utils.Utils;

public class AccelerationGene extends Gene {

	public AccelerationGene(boolean left, boolean right) {
		super(left, right);
	}
	
	public AccelerationGene() {
		super();
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed())
			creature.incrementStamina(Utils.ACCELERATION_BUFF);
	}
	

}
