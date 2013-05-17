package Menu;


import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
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
		//Sets background and font
        Color background = new Color(Color.black);
        gc.getGraphics().setBackground(background);
        scoreFont = new TrueTypeFont(new java.awt.Font("Verdana", Font.BOLD, 48), false);
        
        //Initialises page's headings and buttons and puts them into some respectively ArrayLists
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
		
		//Helps for slight adjustments to rendering positions
		int adjust = gc.getHeight()/70;
		
		//Draws all the headings and buttons
		for(Heading heading : headingsList){
			heading.draw();
		}
		
		//Draws the Player's final score
		g.setFont(scoreFont);
		g.drawString(Integer.toString(Play.gameScore),gc.getWidth()/3+adjust, gc.getHeight()/2-adjust);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		//Gets mouse co-ordinates
		int mouseX;
		int mouseY;
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();    	
    	
    	
		//Checks if a button is pressed and executes its action
		for(Button button : buttonList)
			Utils.buttonPressed(delta, mouseX, mouseY, button, gc, sbg);
		
		
    	//Creates a new game and resets player's score
    	Play.world = new GameWorld("Serotope");
		GameWorld.setScore(0);
	}

	@Override
	public int getID() {
		return id;
	}
	

}
