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

public class EnterHighscore extends BasicGameState{
	
	private int id;
	private TrueTypeFont scoreFont;
	private String name;
	private int gameScore;
	private boolean toGameOver;
	private int donePosX;
	private int donePosY;
	private ArrayList<Heading> highscoreHeadings;
	private Button done;
	
	public EnterHighscore(int id) {
		this.id=id;
	}
	
	public class DoneButton extends Button{

		public DoneButton(String src, int x, int y, float s, float e, int es)
				throws SlickException {
			super(src, x, y, s, e, es);
		}
		
		public boolean buttonPressed(int delta, int x, int y, GameContainer gc, StateBasedGame sbg){
	    	//Checks if mouse selects the DONE option and enters highscores and sets toGameOver flag to true
			boolean mouseOver = false;
	    	if(isInside(x, y)){
	    		mouseOver = true;
				increaseSize(delta);
				if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
					gPanel.database.writeToHighscores(name, gameScore);
					toGameOver = true;
				}
			}else
				decreaseSize(delta);
			if(isSelected() && gc.getInput().isKeyPressed(Input.KEY_ENTER)){
				gPanel.database.writeToHighscores(name, gameScore);
				toGameOver = true;
			}
			return mouseOver;
		}
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		//Sets background and font
        Color background = new Color(Color.black);
        gc.getGraphics().setBackground(background);
        scoreFont = new TrueTypeFont(new java.awt.Font("Verdana", Font.BOLD, 48), false);	
        
        //Sets the x y position for the DONE button
        donePosX = gc.getWidth()/3*2;
        donePosY = gc.getHeight()/6*5;
        
        //Initialises headings and buttons for a page and puts them into a list together
        highscoreHeadings = new ArrayList<Heading>();
        highscoreHeadings.add(new Heading(Utils.HIGHSCORETITLE,Utils.LEFTALIGNX,Utils.TITLEPOSY));
        highscoreHeadings.add(new Heading(Utils.GAMEOVERENTER,Utils.LEFTALIGNX,Utils.THIRDHEADING));
		highscoreHeadings.add(done = new DoneButton(Utils.GAMEOVERDONE,donePosX,donePosY,Utils.STARTSCALE,Utils.ENLARGE,gPanel.GAMEOVERMENUID));
        done.setSelected(true);
		
		//Initialises the highscore name string and sets the toGameOver flag to false
		name = "";
        toGameOver = false;
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setFont(scoreFont);
		
		//Draws all the headings and buttons to the screen
		for(Heading heading : highscoreHeadings)
			heading.draw();
		
		//Sets the width and height for the box surrounding the name
		int boxWidth = gc.getWidth()/2;
		int boxHeight = gc.getHeight()/10;
		
		//Draws the current highscore name to the screen and rectangle surrounding it
		g.drawString(name, Utils.LEFTALIGNX, Utils.FOURTHHEADING);		
		g.drawRect(Utils.LEFTALIGNX, Utils.FOURTHHEADING , boxWidth, boxHeight);
		
		//Draws the highscore to the screen
		g.drawString(Integer.toString(gameScore), gc.getWidth()/8, gc.getHeight()/2);
	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		//Gets the final game score from the game just ended
    	gameScore = Play.gameScore;
    	
		//Gets mouse co-ordinates
		int mouseX;
		int mouseY;
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();    	
    	
    	
    	//Gets input for highscore name if the game is in this menu screen
    	//If enter is pressed highscore is entered and toGameOver flag set to true
    	if(sbg.getCurrentStateID() == gPanel.ENTERSCOREID){
			int newChar = InputManager.returnLetterPressed(gc.getInput());
			if(newChar == Keyboard.KEY_BACK){
				if(name.length()!=0)
					name = name.substring(0, name.length()-1);
			}else if(newChar == Keyboard.KEY_RETURN){
				gPanel.database.writeToHighscores(name, gameScore);
				toGameOver = true;
			}else if(newChar != -1 && name.length()<Utils.MAXSTRING){
				name += Keyboard.getKeyName(newChar);
			}
    	}
    	
    	done.buttonPressed(delta, mouseX, mouseY, gc, sbg);

    	//Checks toGameOver flag and if true enters Game Over screen
    	if(toGameOver){
    		toGameOver = false;
    		name = "";
    		gc.getInput().clearKeyPressedRecord();
    		sbg.enterState(gPanel.GAMEOVERMENUID);
    	}
    	
	}
	@Override
	public int getID() {
		return id;
	}

}
