package Genes;

import Serotope.Creature;
import Utils.Utils;

public class AttackSpeedGene extends Gene {

	public AttackSpeedGene(int leftAllele, int rightAllele) {
		super(leftAllele, rightAllele);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buffCreature(Creature creature) {
		int modifier = this.getLeftAllele() + this.getRightAllele();
		creature.decrementCoolDown(modifier * Utils.ATTACK_SPEED_BUFF);

	}

}