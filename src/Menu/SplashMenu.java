package Menu;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SplashMenu extends BasicGameState {
	
	
	private int id;
	private static final String GAMETITLE = "assets/image/menus/SplashTitle.png";
	private static final String STARTGAMEMESSAGE = "assets/image/menus/SplashText.png";
	private static float ALPHAFORSTARTGAMEMESSAGE = 1;
	private static boolean STARTGAMEMESSAGEFLASHSWITCH=true;
	private static float STARTGAMEMESSAGEFLASHSPEED=(float) 1/850;
	private Image title;
	private Image text;
	

	public SplashMenu(int id) {
		this.id=id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {		
		title = new Image(GAMETITLE);
		text = new Image(STARTGAMEMESSAGE);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		title.draw(gc.getWidth()/2-title.getWidth()/2,gc.getHeight()/3);
		text.draw(gc.getWidth()/2-text.getWidth()/2,gc.getHeight()/3*2);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		fadestartmessage(delta);
		if(gc.getInput().isKeyDown(InputManager.KeyStart)) {
			sbg.enterState(gPanel.MAINMENUID);
		}
		
	}

	@Override
	public int getID() {
		return id;
	}
	
	private void fadestartmessage(int delta) {
		if(ALPHAFORSTARTGAMEMESSAGE>1||ALPHAFORSTARTGAMEMESSAGE<0) {
			if(STARTGAMEMESSAGEFLASHSWITCH) {
				ALPHAFORSTARTGAMEMESSAGE=0;
			}
			else{
				ALPHAFORSTARTGAMEMESSAGE=1;
			}
			STARTGAMEMESSAGEFLASHSWITCH=!STARTGAMEMESSAGEFLASHSWITCH;
		}
	
	
		if(STARTGAMEMESSAGEFLASHSWITCH) {
			ALPHAFORSTARTGAMEMESSAGE-=(float)delta*STARTGAMEMESSAGEFLASHSPEED;
		}
		else {
			ALPHAFORSTARTGAMEMESSAGE+=(float)delta*STARTGAMEMESSAGEFLASHSPEED;

		}
	}

}
