package Genes;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Serotope.Creature;
import Serotope.GameWorld;


public abstract class Gene {
	
	// false for recessive, true for dominant
	private boolean leftAllele;
	private boolean rightAllele;
	private Image icon = null;
	private Image creatureTag = null;

	public Gene(boolean leftAllele, boolean rightAllele){
		this.leftAllele = leftAllele;
		this.rightAllele = rightAllele;
	}
	
	public Gene() {
		this.leftAllele = false;
		this.rightAllele = true;
	}
	
	/**
	 * Renders the UI icon associated with the gene
	 * 
	 * @param g Graphics
	 * @param xrender x co-ordinate
	 * @param yrender y co-ordinate
	 */
	public void renderIcon(Graphics g, float xrender, float yrender) {
		if (icon == null) {
			return;
		}
		icon.drawCentered(xrender, yrender);
	}
	
	/**
	 * Renders the creature icon associated with the gene
	 * 
	 * @param g Graphics
	 * @param xrender x co-ordinate
	 * @param yrender y co-ordinate
	 */
	public void renderTag(Graphics g, float xrender, float yrender) {
		if (creatureTag == null) {
			return;
		}
		creatureTag.drawCentered(xrender, yrender);
	}
	
	/**
	 * Checks if the gene is expressed (both the alleles are recessive)
	 */
	public boolean isExpressed(){
		return (leftAllele && rightAllele);
	}

	/**
	 * Randomly picks one of the gene's alleles
	 * 
	 * @return either the leftAllele or rightAllele
	 */
	public boolean getRandomAllele(){
		boolean left = GameWorld.getRandomGenerator().nextBoolean();
		if (left)
			return leftAllele;
		else
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
	
	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}

	public Image getCreatureTag() {
		return creatureTag;
	}

	public void setCreatureTag(Image creatureTag) {
		this.creatureTag = creatureTag;
	}

	public abstract void buffCreature(Creature creature);
}
