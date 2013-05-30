package Menu;


import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class AchievementsMenu extends BasicGameState {
	
	
	private int id;
	private Font scoreFont;
	private int highscoreListPosX;
	private int highscoreListPosY;
	private int medalPosX;
	private int medalPosY;
	private int medalHeadingX;
	private ArrayList<Heading> headingList;
	private Button goBack;
	
	
	public AchievementsMenu(int id) {
		this.id=id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
		//Set background colour to black and font.
        Color background = new Color(Color.black);
        gc.getGraphics().setBackground(background);
        scoreFont = new Font("Verdana", Font.BOLD, 18);

		//Set rendering co-ordinates for highscores
		highscoreListPosX = gc.getWidth()/8;
		highscoreListPosY = gc.getHeight()/2;
		medalPosX = gc.getWidth()/2;
		medalPosY = gc.getHeight()/2;
		medalHeadingX = gc.getWidth()/8*5;
		
		//Initialise page headings and buttons
		headingList = new ArrayList<Heading>();
		headingList.add(new Heading(Utils.ACHIEVEMENTSTITLE,Utils.LEFTALIGNX,Utils.TITLEPOSY));
		headingList.add(new Heading(Utils.ACHIEVEMENTSHIGHSCORE,Utils.LEFTALIGNX,Utils.FIRSTHEADING));
		headingList.add(new Heading(Utils.ACHIEVEMENTSMEDAL,medalHeadingX,Utils.FIRSTHEADING));
		headingList.add(goBack = new Button(Utils.GOBACK,Utils.BOTRIGHTX,Utils.BOTRIGHTY,Utils.STARTSCALE,Utils.ENLARGE,gPanel.MAINMENUID));
		goBack.setSelected(true);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		//Sets the spacing between highscore lines
		int highscoreSpacing = gc.getHeight()/20;
		
		//Sets the font of highscores
		g.setFont(new TrueTypeFont(scoreFont,false));
		
		//Draw each heading and button onto the page
		for(Heading heading : headingList)
			heading.draw();
		
		//Draws the highscores and medals onto the page
		g.setColor(Color.white);
    	gPanel.database.drawHighscores(highscoreListPosX, highscoreListPosY, highscoreSpacing, g);
    	gPanel.database.drawMedals(medalPosX, medalPosY, gc, g);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		//Get mouse co-ordinates
		int mouseX;
		int mouseY;    	
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();

    	//Check if button has been pressed and execute action if so
    	goBack.buttonPressed(delta, mouseX, mouseY, gc, sbg);    	
	}

	@Override
	public int getID() {
		return id;
	}
	

}
