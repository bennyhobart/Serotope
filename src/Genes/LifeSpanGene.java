package Genes;

import Serotope.Creature;
import Utils.Utils;

/**
 * Modifies a creature's rate of decrease in health over time
 *
 */
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
