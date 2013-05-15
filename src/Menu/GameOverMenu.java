package Menu;


import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Serotope.GameWorld;

public class GameOverMenu extends BasicGameState {
	
	
	private int id;
	private TrueTypeFont scoreFont;
	private ArrayList<Heading> headingsList;
	private ArrayList<Button> buttonList;
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
        scoreFont = new TrueTypeFont(new java.awt.Font("Verdana", Font.BOLD, 48), false);
        
		headingsList = new ArrayList<Heading>();
		headingsList.add(new Heading(Utils.GAMEOVERTITLE,gc.getWidth()/8,gc.getHeight()/6));
		headingsList.add(replay = new Button(Utils.GAMEOVERREPLAY,gc.getWidth()/8,gc.getHeight()/3*2,Utils.STARTSCALE,Utils.ENLARGE,gPanel.PLAYID));
		headingsList.add(returnMainMenu = new Button(Utils.GAMEOVERRETURNMAINMENU,gc.getWidth()/8,gc.getHeight()/6*5,Utils.STARTSCALE,Utils.ENLARGE,gPanel.MAINMENUID));
		headingsList.add(new Heading(Utils.GAMEOVERFINALSCORE,gc.getWidth()/8,gc.getHeight()/2));
		buttonList = new ArrayList<Button>();
		buttonList.add(replay);
		buttonList.add(returnMainMenu);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		for(Heading heading : headingsList){
			heading.draw();
		}
		g.setFont(scoreFont);
		g.drawString(Integer.toString(Play.gameScore), gc.getWidth()/3+Utils.FSCOREBUFFER, gc.getHeight()/2-Utils.FSCOREBUFFER);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		int mouseX;
		int mouseY;
    	
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();
		Play.world = new GameWorld("Serotope");
		GameWorld.setScore(0);
    	
		for(Button button : buttonList)
			Utils.buttonPressed(delta, mouseX, mouseY, button, gc, sbg);
		
	}

	@Override
	public int getID() {
		return id;
	}
	

}
