package Menu;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class AchievementsMenu extends BasicGameState {
	
	
	private int id;
	private BufferedReader reader;
	private ArrayList<String> highscoreList;
	private float goBackScale;
	private final float enlarge = 0.0001f;
	private static final String HIGHSCOREFILE = "assets/data/highscores.txt";
	private static final String TITLE = "assets/image/menus/AchievementsTitle.png";
	private static final String HIGHSCORE = "assets/image/menus/AchievementsHighscore.png";
	private static final String MEDAL = "assets/image/menus/AchievementsMedal.png";
	private int titlePosX;
	private int titlePosY;
	private int highscorePosX;
	private int highscorePosY;
	private int highscoreListPosX;
	private int highscoreListPosY;
	private int medalPosX;
	private int medalPosY;
	private int goBackPosX;
	private int goBackPosY;
	private Image title;
	private Image highscore;
	private Image medal;
	private Image goBack;

	public AchievementsMenu(int id) {
		this.id=id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
		try {
			String hLine;
			reader = new BufferedReader(new FileReader(HIGHSCOREFILE));
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
		
		title = new Image(TITLE);
		highscore = new Image(HIGHSCORE);
		medal = new Image(MEDAL);
		goBack = new Image(MainMenu.GOBACK);
		titlePosX = gc.getWidth()/8;
		titlePosY = gc.getHeight()/6;
		highscorePosX = gc.getWidth()/8;
		highscorePosY = gc.getHeight()/3;
		highscoreListPosX = gc.getWidth()/8;
		highscoreListPosY = gc.getHeight()/2;
		medalPosX = gc.getWidth()/8*5;
		medalPosY = gc.getHeight()/3;
		goBackPosX = gc.getWidth()/8*7;
		goBackPosY = gc.getHeight()/6*5;
		goBackScale = 1;
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		title.draw(titlePosX,titlePosY);
		highscore.draw(highscorePosX,highscorePosY);
		medal.draw(medalPosX,medalPosY);
		goBack.draw(goBackPosX,goBackPosY,goBackScale);
		
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
    	boolean onGoBack = false;
    	
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();
    	
    	if( ( mouseX >= goBackPosX && mouseX <= goBackPosX + goBack.getWidth()) &&
    		    ( mouseY >= goBackPosY && mouseY <= goBackPosY + goBack.getHeight()) ){
    		onGoBack = true;
    	}
    	
    	if(onGoBack){
    		if(goBackScale < 1.05f)
    			goBackScale += enlarge * delta;
    		
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.MAINMENUID);
    		}
    	}else{
    		if(goBackScale > 1.0f)
    			goBackScale -= enlarge *delta;
    	}
    	
	}

	@Override
	public int getID() {
		return id;
	}
	

}
