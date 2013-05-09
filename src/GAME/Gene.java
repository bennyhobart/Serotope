package GAME;

import Serotope.Creature;

public abstract class Gene {
	
	private int leftAllele;
	private int rightAllele;

	public int getLeftAllele() {
		return leftAllele;
	}

	public int getRightAllele() {
		return rightAllele;
	}

	public abstract void buffCreature(Creature creature);
}
