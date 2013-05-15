package Menu;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class AchievementsMenu extends BasicGameState {
	
	
	private int id;
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
        Color background = new Color(Color.black);
        gc.getGraphics().setBackground(background);
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

		highscoreListPosX = gc.getWidth()/8;
		highscoreListPosY = gc.getHeight()/2;
		headingList = new ArrayList<Heading>();
		headingList.add(new Heading(Utils.ACHIEVEMENTSTITLE,gc.getWidth()/8,gc.getHeight()/6));
		headingList.add(new Heading(Utils.ACHIEVEMENTSHIGHSCORE,gc.getWidth()/8,gc.getHeight()/3));
		headingList.add(new Heading(Utils.ACHIEVEMENTSMEDAL,gc.getWidth()/8*5,gc.getHeight()/3));
		headingList.add(goBack = new Button(Utils.GOBACK,gc.getWidth()/8*7,gc.getHeight()/12*11,Utils.STARTSCALE,Utils.ENLARGE,gPanel.MAINMENUID));
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		for(Heading heading : headingList)
			heading.draw();
		
		int scorePosY = highscoreListPosY;
		for(int i=0;i<highscoreList.size();i++){
			g.drawString((i+1) + ".  " + highscoreList.get(i), highscoreListPosX, scorePosY);
			scorePosY += 20;
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		int mouseX;
		int mouseY;    	
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();

    	if(goBack.isInside(mouseX, mouseY)){
    		goBack.increaseSize(delta);
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.MAINMENUID);
    		}
    	}else{
    		goBack.decreaseSize(delta);
    	}
    	
	}

	@Override
	public int getID() {
		return id;
	}
	

}
