package Serotope;

import java.util.Random;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import AI.StateMachine;
import CreatureSpawnerAI.DefaultState;

public class CreatureSpawner extends GameObject {
	public CreatureSpawnerBehaviours behaviour;
	StateMachine<CreatureSpawner> stateMachine;
	int lastSpawnTime;
	int spawnTime;
	Creature target;
	// Initial probability of AI getting a recessive allele in a gene
	private static final float BASE_PROBABILITY = 0.3f;
	// Rate at which the probability increases with time
	// This makes the ai spawn with stronger DNA as the game progresses
	private static final float PROBABILITY_MODIFIER = 0.000005f;

	CreatureSpawner(Vec2 position) throws SlickException {
		super(position, null, false);
		behaviour = new CreatureSpawnerBehaviours(this);
		target = GameWorld.getGameWorld().getPlayer();
		stateMachine = new StateMachine<CreatureSpawner>(this);
		stateMachine.setCurrentState(DefaultState.getInstance());
		spawnTime = Utils.Utils.spawnTime;
	}

	@Override
	public void update(int delta, GameContainer gc) {
		target = GameWorld.getGameWorld().getPlayer();
		this.getBody().setTransform(target.getBody().getPosition(),0);
		stateMachine.update();
		lastSpawnTime += delta;
	}

	public void spawn(Vec2 location) throws SlickException {
		if (lastSpawnTime < spawnTime) {
			return;
		}
		lastSpawnTime = 0;
		float probability = getProbabiliyRecessiveGene();
		DNA dna = randomDna(probability);
		try {
			new Creature(location, false, dna);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// Calculate the probability of getting a recessive (good) allele when
	// creating DNA for a new AI creature
	// This value increases with the game time, reaching a maximum of 1.
	private float getProbabiliyRecessiveGene(){
		float timeElapsed = GameWorld.getGameWorld().getScore();
		float probability = PROBABILITY_MODIFIER * timeElapsed + BASE_PROBABILITY;
		if (probability > 1)
			probability = 1;
		return probability;
	}
	

	// Creates a new dna object using a random value for each allele (true or false)
//	private DNA randomDna() throws SlickException {
//		DNA dna = new DNA();
//
//		for (int i = 1; i < dna.getGenes().size(); i++){
//			boolean left = randomGenerator.nextBoolean();
//			boolean right = randomGenerator.nextBoolean();
//			dna.getGenes().get(i).setLeftAllele(left);
//			dna.getGenes().get(i).setRightAllele(right);
//		}
//		return dna;
//	}
//	
	// Creates a new dna object using a random value for each allele (true or false)
	// The probability of getting a recessive (true) allele is determined by the
	// probability parameter (which ranges from 0 to 1)
	// 
	
	private DNA randomDna(float probability) throws SlickException {
	DNA dna = new DNA();

	for (int i = 1; i < dna.getGenes().size(); i++){
		int randomNum = GameWorld.getRandomGenerator().nextInt(100);
		boolean left = (randomNum <= probability*100);
		
		randomNum = GameWorld.getRandomGenerator().nextInt(100);
		boolean right = (randomNum <= probability*100);
		
		dna.getGenes().get(i).setLeftAllele(left);
		dna.getGenes().get(i).setRightAllele(right);
	}
	return dna;
}



}
