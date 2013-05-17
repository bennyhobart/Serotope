package Menu;



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
		gameScore = world.getScore();
		if(world.getPlayer().doomed){
			if(gPanel.highscores.size() == 0 || gameScore >= gPanel.highscores.get(gPanel.highscores.size()-1).getScore()){
				for(int i=0;i<gPanel.highscores.size();i++){
		    		if(gameScore > gPanel.highscores.get(i).getScore())
		    			rank = i;
		    	}
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
