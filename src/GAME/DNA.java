package GAME;

import java.util.ArrayList;

import Serotope.Creature;

public class DNA {

	private ArrayList<Gene> genes = new ArrayList<Gene>();
	
	public DNA(ArrayList<Gene> genes){
		this.genes = genes;
	}
	
	public void buffCreature(Creature creature){
		for (Gene gene : genes){
			gene.buffCreature(creature);
		}
	}



}
