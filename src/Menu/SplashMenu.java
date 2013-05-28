package Menu;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SplashMenu extends BasicGameState {
	
	
	private int id;
	private static final String GAMETITLE = "assets/image/menus/SplashTitle.png";
	private static final String STARTGAMEMESSAGE = "assets/image/menus/SplashText.png";
	private Image title;
	private Image text;
	

	public SplashMenu(int id) {
		this.id=id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {	
        Color background = new Color(Color.black);
        gc.getGraphics().setBackground(background); 
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

		Input input = gc.getInput();
		if(input.isKeyDown(InputManager.KeyStart) || input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			input.clearKeyPressedRecord();
			sbg.enterState(gPanel.MAINMENUID);
		}
		
	}

	@Override
	public int getID() {
		return id;
	}
	
}
