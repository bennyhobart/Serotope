package Genes;

import Serotope.Creature;
import Utils.Utils;

public class HealthGene extends Gene {

	public HealthGene(boolean left, boolean right) {
		super(left, right);
		this.setIcon(Utils.HEALTH_ICON);
		this.setCreatureTag(Utils.HEALTH_TAG);
	}

	public HealthGene() {
		super();
		this.setIcon(Utils.HEALTH_ICON);
		this.setCreatureTag(Utils.HEALTH_TAG);
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed()){
			creature.increaseHealth(Utils.HEALTH_BUFF);
			creature.setCurrHealth(creature.getHealth());
		}
	}

}

