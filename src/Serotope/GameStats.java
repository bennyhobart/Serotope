package Serotope;

public class GameStats {

	
	private int enemiesKilled = 0;
	private int generations = 0;
	private int maxNumGenes = 0;
	
	public GameStats() {
	
	}

	public void incrementEnemiesKilled() {
		this.enemiesKilled++;
	}

	public void incrementGenerations() {
		this.generations++;
	}

	public void incrementMaxNumGenes() {
		this.maxNumGenes++;
	}

	public int getEnemiesKilled() {
		return enemiesKilled;
	}

	public int getGenerations() {
		return generations;
	}

	public int getMaxNumGenes() {
		return maxNumGenes;
	}
	
	
	
	

}
