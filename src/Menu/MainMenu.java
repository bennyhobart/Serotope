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

public class MainMenu extends BasicGameState {
	
	
	private int id;
	private static final float enlarge = 0.0001f;
	private static final float startScale = 1f;
	private static final String GAMETITLE = "assets/image/menus/MainTitle.png";
	private static final String PLAY = "assets/image/menus/MainPlay.png";
	private static final String HOWTO = "assets/image/menus/MainHowTo.png";
	private static final String ACHIEVEMENTS = "assets/image/menus/MainAchievements.png";
	private static final String SETTINGS = "assets/image/menus/MainSettings.png";
	private static final String QUIT = "assets/image/menus/MainQuit.png";
	public static final String GOBACK = "assets/image/menus/GoBack.png";
	private ArrayList<Heading> headingList;
	private Heading title;
	private Button play;
	private Button howTo;
	private Button achievements;
	private Button quit;
	private Button settings;

	public MainMenu(int id) {
		this.id=id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {	
        Color background = new Color(Color.black);
        gc.getGraphics().setBackground(background); 
		headingList = new ArrayList<Heading>();
		title = new Heading(GAMETITLE,gc.getWidth()/8,gc.getHeight()/6);
		play = new Button(PLAY,gc.getWidth()/8,gc.getHeight()/3,startScale,enlarge);
		howTo = new Button(HOWTO,gc.getWidth()/8,gc.getHeight()/2,startScale,enlarge);
		achievements = new Button(ACHIEVEMENTS,gc.getWidth()/8,gc.getHeight()/3*2,startScale,enlarge);
		quit = new Button(QUIT,gc.getWidth()/8,gc.getHeight()/6*5,startScale,enlarge);
		settings = new Button(SETTINGS,gc.getWidth()/8*7,gc.getHeight()/8*7,startScale,enlarge);
		headingList.add(title);
		headingList.add(play);
		headingList.add(howTo);
		headingList.add(achievements);
		headingList.add(quit);
		headingList.add(settings);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		for(Heading heading : headingList)
			heading.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		int mouseX;
		int mouseY;
    	
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();
    	
    	if(play.isInside(mouseX, mouseY)){
    		play.increaseSize(delta);
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.PLAYID);
    		}
    	}else{
    		play.decreaseSize(delta);
    	}
    	
    	if(howTo.isInside(mouseX, mouseY)){
    		howTo.increaseSize(delta);
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.HOWTOMENUID);
    		}
    	}else{
    		howTo.decreaseSize(delta);
    	}
    	
    	if(achievements.isInside(mouseX, mouseY)){
    		achievements.increaseSize(delta);
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.ACHIEVEMENTSMENUID);
    		}
    	}else{
    		achievements.decreaseSize(delta);
    	}
    	
    	if(quit.isInside(mouseX, mouseY)){
    		quit.increaseSize(delta);
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			gc.exit();
    		}
    	}else{
    		quit.decreaseSize(delta);
    	}
    	
    	if(settings.isInside(mouseX, mouseY)){
    		settings.increaseSize(delta);
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.SETTINGSMENUID);
    		}
    	}else{
    		settings.decreaseSize(delta);
    	}
	}

	@Override
	public int getID() {
		return id;
	}
	

}
