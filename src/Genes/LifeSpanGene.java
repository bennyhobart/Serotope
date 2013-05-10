package Genes;

import Serotope.Creature;
import Utils.Utils;

public class LifeSpanGene extends Gene {
	
	public LifeSpanGene(boolean left, boolean right) {
		super(left, right);
	}
	
	public LifeSpanGene() {
		super();
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed())
			creature.incrementStamina(Utils.STAMINA_BUFF);
	}
	
	
	

}
