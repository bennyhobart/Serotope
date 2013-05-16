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
	private TrueTypeFont scoreFont;
	private BufferedReader reader;
	private ArrayList<String> highscoreList;
	private int highscoreListPosX;
	private int highscoreListPosY;
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
        scoreFont = new TrueTypeFont(new java.awt.Font("Verdana", Font.BOLD, 18), false);

        //Read in the highscores from the text file
		try {
			String hLine;
			reader = new BufferedReader(new FileReader(Utils.HIGHSCOREFILE));
			highscoreList = new ArrayList<String>();
			while((hLine = reader.readLine()) != null){
				highscoreList.add(hLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null)
					reader.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}

		//Set rendering co-ordinates for highscores
		highscoreListPosX = gc.getWidth()/8;
		highscoreListPosY = gc.getHeight()/2;
		//Initialise page headings and buttons
		headingList = new ArrayList<Heading>();
		headingList.add(new Heading(Utils.ACHIEVEMENTSTITLE,gc.getWidth()/8,gc.getHeight()/6));
		headingList.add(new Heading(Utils.ACHIEVEMENTSHIGHSCORE,gc.getWidth()/8,gc.getHeight()/3));
		headingList.add(new Heading(Utils.ACHIEVEMENTSMEDAL,gc.getWidth()/8*5,gc.getHeight()/3));
		headingList.add(goBack = new Button(Utils.GOBACK,gc.getWidth()/8*7,gc.getHeight()/12*11,Utils.STARTSCALE,Utils.ENLARGE,gPanel.MAINMENUID));
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setFont(scoreFont);
		
		//Draw each heading and button onto the page
		for(Heading heading : headingList)
			heading.draw();
		
		//Draw each highscore on the page in their ranked order
		int scorePosY = highscoreListPosY;
		for(int i=0;i<highscoreList.size();i++){
			g.drawString((i+1) + ".  " + highscoreList.get(i), highscoreListPosX, scorePosY);
			scorePosY += gc.getHeight()/20;
		}
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
    	Utils.buttonPressed(mouseX, mouseX, mouseY, goBack, gc, sbg);
    	
	}

	@Override
	public int getID() {
		return id;
	}
	

}
