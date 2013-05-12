package Genes;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Serotope.Creature;


public abstract class Gene {
	
	// 0 for recessive, 1 for dominant
	private boolean leftAllele;
	private boolean rightAllele;
	private Image icon = null;
	private Image creatureTag = null;

	private Random randomGenerator = new Random(System.nanoTime());
	
	public Gene(boolean leftAllele, boolean rightAllele){
		this.leftAllele = leftAllele;
		this.rightAllele = rightAllele;
	}
	
	public Gene() {
		this.leftAllele = false;
		this.rightAllele = false;
	}
	
	public void renderIcon(Graphics g, float xrender, float yrender) {
		if (icon == null) {
			return;
		}
		icon.drawCentered(xrender, yrender);
	}
	
	public void renderTag(Graphics g, float xrender, float yrender) {
		if (creatureTag == null) {
			return;
		}
		creatureTag.drawCentered(xrender, yrender);
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
