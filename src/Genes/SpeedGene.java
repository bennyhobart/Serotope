package Genes;

import Serotope.Creature;
import Utils.Utils;

public class SpeedGene extends Gene {

	
	public SpeedGene(boolean left, boolean right) {
		super(left, right);
		this.setIcon(Utils.SPEED_ICON);
		this.setCreatureTag(Utils.SPEED_TAG);
	}
	
	public SpeedGene() {
		super();
		this.setIcon(Utils.SPEED_ICON);
		this.setCreatureTag(Utils.SPEED_TAG);
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed())
			creature.increaseStamina(Utils.SPEED_BUFF);
	}
	

}
