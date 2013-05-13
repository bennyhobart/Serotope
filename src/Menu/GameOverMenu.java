package Menu;


import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverMenu extends BasicGameState {
	
	
	private int id;
	private final float startScale = 1;
	private final float enlarge = 0.0001f;
	private static final String GAMEOVERTITLE = "assets/image/menus/GameOverTitle.png";
	private static final String FINALSCORE = "assets/image/menus/GameOverScore.png";
	private static final String REPLAY = "assets/image/menus/GameOverReplay.png";
	private static final String RETURNMAINMENU = "assets/image/menus/GameOverReturn.png";
	private ArrayList<Heading> headingsList;
	private Heading gameOver;
	private Heading finalScore;
	private ArrayList<Button> gameOverButtons;
	private Button replay;
	private Button returnMainMenu;

	public GameOverMenu(int id) {
		this.id=id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {	
        Color background = new Color(Color.black);
        gc.getGraphics().setBackground(background);
		gameOver = new Heading(GAMEOVERTITLE,gc.getWidth()/8,gc.getHeight()/6);
		finalScore = new Heading(FINALSCORE,gc.getWidth()/8,gc.getHeight()/2);
		replay = new Button(REPLAY,gc.getWidth()/8,gc.getHeight()/3*2,startScale,enlarge);
		returnMainMenu = new Button(RETURNMAINMENU,gc.getWidth()/8,gc.getHeight()/6*5,startScale,enlarge);
		gameOverButtons = new ArrayList<Button>();
		headingsList = new ArrayList<Heading>();
		gameOverButtons.add(replay);
		gameOverButtons.add(returnMainMenu);
		headingsList.add(gameOver);
		headingsList.add(replay);
		headingsList.add(returnMainMenu);
		headingsList.add(finalScore);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		for(Heading heading : headingsList){
			heading.draw();
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		int mouseX;
		int mouseY;
    	
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();
    	
    	if(replay.isInside(mouseX, mouseY)){
    		replay.increaseSize(delta);
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.PLAYID);
    		}
    	}else{
    		replay.decreaseSize(delta);
    	}

    	if(returnMainMenu.isInside(mouseX, mouseY)){
    		returnMainMenu.increaseSize(delta);
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.MAINMENUID);
    		}
    	}else{
    		returnMainMenu.decreaseSize(delta);
    	}

	}

	@Override
	public int getID() {
		return id;
	}
	

}
