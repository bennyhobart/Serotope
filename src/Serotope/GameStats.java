package Serotope;

public class GameStats {

	private int enemiesKilled = 0;
	private int generations = -1;

	public GameStats() {

	}

	public void incrementEnemiesKilled() {
		this.enemiesKilled++;
	}

	public void incrementGenerations() {
		this.generations++;
	}

	public int getEnemiesKilled() {
		return enemiesKilled;
	}

	public int getGenerations() {
		return generations;
	}

}
