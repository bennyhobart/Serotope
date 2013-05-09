package Menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {
	
	
	private int id;
	private static final String STARTGAMEMESSAGE = "Press <Enter> to Begin...";
	private static float ALPHAFORSTARTGAMEMESSAGE = 1;
	private static boolean STARTGAMEMESSAGEFLASHSWITCH=true;
	private static float STARTGAMEMESSAGEFLASHSPEED=(float) 1/850;
	private Sound STARTGAMESOUND;
	private boolean gameStarting=false;
	

	public Menu(int id) {
		this.id=id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		//load sound
		STARTGAMESOUND = new Sound("assets/audio/menu_select.ogg");
		//setup keyboard hook
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setColor(new Color(Color.white).darker(ALPHAFORSTARTGAMEMESSAGE));
		g.drawString(STARTGAMEMESSAGE,gc.getWidth()/2-g.getFont().getWidth(STARTGAMEMESSAGE)/2,gc.getHeight()/2);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		fadestartmessage(delta);
		if(gc.getInput().isKeyDown(InputManager.KeyStart)&&gameStarting==false) {
			gameStarting=true;
			STARTGAMESOUND.play();
		}
		if(gameStarting&&ALPHAFORSTARTGAMEMESSAGE>0.9) {
			sbg.enterState(gPanel.PLAYID);
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
