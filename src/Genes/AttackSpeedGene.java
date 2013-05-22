package Genes;

import Serotope.Creature;
import Utils.Utils;

/**
 * Modifies a creature's attack speed (cool down time)
 *
 */
public class AttackSpeedGene extends Gene {

	public AttackSpeedGene(boolean leftAllele, boolean rightAllele) {
		super(leftAllele, rightAllele);
		this.setIcon(Utils.ATTACKSPEED_ICON);
		this.setCreatureTag(Utils.ATTACKSPEED_TAG);
	}

	public AttackSpeedGene() {
		super();
		this.setIcon(Utils.ATTACKSPEED_ICON);
		this.setCreatureTag(Utils.ATTACKSPEED_TAG);
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed())
			creature.decreaseCoolDown(Utils.ATTACK_SPEED_BUFF);

	}

}
