package Genes;

import Serotope.Creature;
import Utils.Utils;

public class SpeedGene extends Gene {

	
	public SpeedGene(int left, int right) {
		super(left, right);
	}
	
	@Override
	public void buffCreature(Creature creature) {
		int modifier = this.getLeftAllele() + this.getRightAllele();
		creature.incrementStamina(modifier * Utils.SPEED_BUFF);
	}
	

}
