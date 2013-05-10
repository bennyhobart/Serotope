package Genes;

import java.util.Random;

import Serotope.Creature;


public abstract class Gene {
	
	// 0 for recessive, 1 for dominant
	private boolean leftAllele;
	private boolean rightAllele;
	private Random randomGenerator = new Random(System.nanoTime());
	
	public Gene(boolean leftAllele, boolean rightAllele){
		this.leftAllele = leftAllele;
		this.rightAllele = rightAllele;
	}
	
	public Gene() {
		this.leftAllele = false;
		this.rightAllele = false;
	}
	
	public boolean isExpressed(){
		return (leftAllele && rightAllele);
	}

	public boolean getRandomAllele(){
		boolean left = randomGenerator.nextBoolean();
		if (left)
			return leftAllele;
		else
			return rightAllele;
	}

	public boolean getLeftAllele() {
		return leftAllele;
	}

	public boolean getRightAllele() {
		return rightAllele;
	}
	
	public void setLeftAllele(boolean leftAllele) {
		this.leftAllele = leftAllele;
	}

	public void setRightAllele(boolean rightAllele) {
		this.rightAllele = rightAllele;
	}

	public boolean isLeftAllele() {
		return (leftAllele);
	}
	
	public boolean isRightAllele() {
		return (rightAllele);
	}
	

	public abstract void buffCreature(Creature creature);
}
