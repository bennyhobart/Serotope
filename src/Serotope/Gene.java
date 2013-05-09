package Serotope;


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

	public abstract void buffCreature(Creature creature);
}
