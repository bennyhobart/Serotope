package Genes;

import Serotope.Creature;
import Utils.Utils;

public class AttackSpeedGene extends Gene {

	public AttackSpeedGene(boolean leftAllele, boolean rightAllele) {
		super(leftAllele, rightAllele);
		// TODO Auto-generated constructor stub
	}

	public AttackSpeedGene() {
		super();
	}

	@Override
	public void buffCreature(Creature creature) {
		if (this.isExpressed())
			creature.decrementCoolDown(Utils.ATTACK_SPEED_BUFF);

	}

}
