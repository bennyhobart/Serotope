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
	private ArrayList<Heading> highscoreHeadings;
	private Button done;
	
	public EnterHighscore(int id) {
		this.id=id;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		//Sets background and font
        Color background = new Color(Color.black);
        gc.getGraphics().setBackground(background);
        scoreFont = new TrueTypeFont(new java.awt.Font("Verdana", Font.BOLD, 48), false);	
        
        highscoreHeadings = new ArrayList<Heading>();
        highscoreHeadings.add(new Heading(Utils.HIGHSCORETITLE,gc.getWidth()/8,gc.getHeight()/6));
        highscoreHeadings.add(new Heading(Utils.GAMEOVERENTER,gc.getWidth()/8,gc.getHeight()/3*2));
		highscoreHeadings.add(done = new Button(Utils.GAMEOVERDONE,gc.getWidth()/2,gc.getHeight()/6*5,Utils.STARTSCALE,Utils.ENLARGE,gPanel.GAMEOVERMENUID));
        name = "";
        toGameOver = false;
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setFont(scoreFont);
		for(Heading heading : highscoreHeadings)
			heading.draw();
		g.drawString(name, gc.getWidth()/8, gc.getHeight()/6*5);		
		g.drawString(Integer.toString(gameScore), gc.getWidth()/8, gc.getHeight()/2);
		g.drawRect(gc.getWidth()/8, gc.getHeight()/6*5 , gc.getWidth()/3, gc.getHeight()/8);
	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
    	gameScore = Play.gameScore;
		//Gets mouse co-ordinates
		int mouseX;
		int mouseY;
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();    	
    	
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
    	
    	if(done.isInside(mouseX, mouseY)){
			done.increaseSize(delta);
			if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				gPanel.database.writeToHighscores(name, gameScore);
				toGameOver = true;
			}
		}else
			done.decreaseSize(delta);

    	if(toGameOver){
    		toGameOver = false;
    		name = "";
    		sbg.enterState(gPanel.GAMEOVERMENUID);
    	}
    	
	}
	@Override
	public int getID() {
		return id;
	}

}
