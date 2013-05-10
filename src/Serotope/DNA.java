package Serotope;

import java.util.ArrayList;

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


public class DNA {

	private ArrayList<Gene> genes = new ArrayList<Gene>();
	
	public DNA(){
		genes.add(new HealthGene(0,0));
		genes.add(new LifeSpanGene(0,0));
		genes.add(new ShieldGene(0,0));
		genes.add(new SpeedGene(0,0));
		genes.add(new AccelerationGene(0,0));
		genes.add(new HandlingGene(0,0));
		genes.add(new DamageGene(0,0));
		genes.add(new AttackSpeedGene(0,0));
		genes.add(new AttackTypeGene(0,0));
	}
	
	public DNA(ArrayList<Gene> genes){
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
	



}
