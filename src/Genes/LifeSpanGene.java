package Genes;

import Serotope.Creature;
import Utils.Utils;

public class LifeSpanGene extends Gene {
	
	public LifeSpanGene(boolean left, boolean right) {
		super(left, right);
		this.setIcon(Utils.LIFESPAN_ICON);
		this.setCreatureTag(Utils.LIFESPAN_TAG);
	}
	
	public LifeSpanGene() {
		super();
		this.setIcon(Utils.LIFESPAN_ICON);
		this.setCreatureTag(Utils.LIFESPAN_TAG);
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed())
			creature.increaseStamina(Utils.STAMINA_BUFF);
	}
	
	
	

}
