package Serotope;

import java.util.ArrayList;

import Genes.HealthGene;


public class DNA {

	private ArrayList<Gene> genes = new ArrayList<Gene>();
	
	public DNA(){
		genes.add(new HealthGene(0,0));
	}
	
	public DNA(ArrayList<Gene> genes){
		this.genes = genes;
	}
	
	public void buffCreature(Creature creature){
		for (Gene gene : genes){
			gene.buffCreature(creature);
		}
	}



}
