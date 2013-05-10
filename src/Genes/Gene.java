package Genes;

import java.util.Random;

import Serotope.Creature;


public abstract class Gene {
	
	// 0 for recessive, 1 for dominant
	private int leftAllele;
	private int rightAllele;
	private Random randomGenerator = new Random(System.nanoTime());
	
	public Gene(int leftAllele, int rightAllele){
		this.leftAllele = leftAllele;
		this.rightAllele = rightAllele;
	}
	
	public int getRandomAllele(){
		boolean left = randomGenerator.nextBoolean();
		if (left)
			return leftAllele;
		else
			return rightAllele;
	}

	public int getLeftAllele() {
		return leftAllele;
	}

	public int getRightAllele() {
		return rightAllele;
	}
	
	public void setLeftAllele(int leftAllele) {
		this.leftAllele = leftAllele;
	}

	public void setRightAllele(int rightAllele) {
		this.rightAllele = rightAllele;
	}

	public boolean isLeftAllele() {
		return (leftAllele == 1);
	}
	
	public boolean isRightAllele() {
		return (rightAllele == 1);
	}

	public abstract void buffCreature(Creature creature);
}
