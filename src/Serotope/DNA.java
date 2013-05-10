package Serotope;

import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Utils.Utils;

import Genes.AccelerationGene;
import Genes.AttackSpeedGene;
import Genes.AttackTypeGene;
import Genes.DamageGene;
import Genes.Gene;
import Genes.HandlingGene;
import Genes.HealthGene;
import Genes.LifeSpanGene;
import Genes.ShieldGene;
import Genes.SpeedGene;


public class DNA extends GameObject{

	private ArrayList<Gene> genes = new ArrayList<Gene>();
	private boolean dropped = false;
	
	public DNA() throws SlickException{
		super(new Vec2(0,0), new Image(Utils.dnaImage), false);
		genes.add(new HealthGene());
		genes.add(new LifeSpanGene());
		genes.add(new ShieldGene());
		genes.add(new SpeedGene());
		genes.add(new AccelerationGene());
		genes.add(new HandlingGene());
		genes.add(new DamageGene());
		genes.add(new AttackSpeedGene());
		genes.add(new AttackTypeGene());
	}
	
	public DNA(ArrayList<Gene> genes) throws SlickException{
		super(new Vec2(0,0), new Image(Utils.dnaImage), false);
		this.genes = genes;
	}
	
	public void buffCreature(Creature creature){
		for (Gene gene : genes){
			gene.buffCreature(creature);
		}
	}

	public ArrayList<Gene> getGenes() {
		return genes;
	}

	@Override
	public void update(int delta, GameContainer gc) {
	}
	
	@Override
	void render(Graphics g, float xrender, float yrender) {
		if (dropped){
			super.render(g, xrender, yrender);
		}
	}

	public void setDropped(boolean b) {
		this.dropped = b;
		
	}
	



}
