package Genes;

import Serotope.Creature;
import Utils.Utils;

public class LifeSpanGene extends Gene {
	
	public LifeSpanGene(int left, int right) {
		super(left, right);
	}
	
	@Override
	public void buffCreature(Creature creature) {
		int modifier = this.getLeftAllele() + this.getRightAllele();
		creature.incrementStamina(modifier * Utils.STAMINA_BUFF);
	}
	
	
	

}
