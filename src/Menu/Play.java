package Menu;



import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Serotope.GameWorld;



public class Play extends BasicGameState {
	static GameWorld world;
	private int id;

	public Play(int id) {
		this.id=id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		world = GameWorld.getGameWorld();
	
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
			sbg.enterState(gPanel.GAMEOVERMENUID);
		}
	}

	@Override
	public int getID() {
		return id;
	}

}
