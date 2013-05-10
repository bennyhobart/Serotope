package Genes;

import Serotope.Creature;


public abstract class Gene {
	
	private int leftAllele;
	private int rightAllele;
	
	public Gene(int leftAllele, int rightAllele){
		this.leftAllele = leftAllele;
		this.rightAllele = rightAllele;
	}

	public int getLeftAllele() {
		return leftAllele;
	}

	public int getRightAllele() {
		return rightAllele;
	}
	
	public boolean isLeftAllele() {
		return (leftAllele == 1);
	}
	
	public boolean isRightAllele() {
		return (rightAllele == 1);
	}

	public abstract void buffCreature(Creature creature);
}
