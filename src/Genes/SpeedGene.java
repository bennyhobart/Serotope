package Genes;

import Serotope.Creature;
import Utils.Utils;

public class SpeedGene extends Gene {

	
	public SpeedGene(boolean left, boolean right) {
		super(left, right);
	}
	
	public SpeedGene() {
		super();
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed())
			creature.incrementStamina(Utils.SPEED_BUFF);
	}
	

}
