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
	private Random randomGenerator = new Random(System.nanoTime());

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
		stateMachine.update();
		lastSpawnTime += delta;
	}

	public void spawn(Vec2 location) throws SlickException {
		if (lastSpawnTime < spawnTime) {
			return;
		}
		lastSpawnTime = 0;
		DNA dna = randomDna();
		try {
			new Creature(location, false, dna);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void spawnChild(Vec2 location, DNA dna1, DNA dna2) throws SlickException{
		DNA dna = mergeDNA(dna1, dna2);
		new Creature(location, true, dna);
	}

	// Creates a new dna object using a random value for each allele (0 or 1)
	private DNA randomDna() throws SlickException {
		DNA dna = new DNA();

		for (int i = 1; i < dna.getGenes().size(); i++){
			boolean left = randomGenerator.nextBoolean();
			boolean right = randomGenerator.nextBoolean();
			dna.getGenes().get(i).setLeftAllele(left);
			dna.getGenes().get(i).setRightAllele(right);
		}
		return dna;
	}
	
	// randomly picks one allele from each of the two given dna's for
	// each gene. Then joins them together to form a new dna object.
	private DNA mergeDNA(DNA dna1, DNA dna2) throws SlickException{
		DNA dna = new DNA();
		for (int i = 1; i < dna.getGenes().size(); i++){
			boolean left = dna1.getGenes().get(i).getRandomAllele();
			boolean right = dna2.getGenes().get(i).getRandomAllele();
			dna.getGenes().get(i).setLeftAllele(left);
			dna.getGenes().get(i).setRightAllele(right);
		}
		return dna;
	}

}
