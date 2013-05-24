package Menu;



import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Serotope.GameWorld;



public class Play extends BasicGameState {
	static GameWorld world;
	private int id;
	public static int gameScore;
	public static int rank;

	public Play(int id) {
		this.id=id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		world = GameWorld.getGameWorld();
		gameScore = 0;
	
		}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setBackground(Color.black);
		world.render(g);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		world.update(delta, gc);
		if(world.getPlayer().doomed){
			gPanel.database.updateAchievements(GameWorld.gameStats);
			gameScore = world.getScore();
			rank = 0;
			ArrayList<Highscore> highscores = gPanel.database.getHighscore();
			if(highscores.size() < Utils.MAXHIGHSCORES || gameScore >= highscores.get(highscores.size()-1).getScore()){
				rank = gPanel.database.calcRank(gameScore);
				gc.getInput().clearKeyPressedRecord();
				sbg.enterState(gPanel.ENTERSCOREID);
			}else
				sbg.enterState(gPanel.GAMEOVERMENUID);
		}
	}

	@Override
	public int getID() {
		return id;
	}

}
