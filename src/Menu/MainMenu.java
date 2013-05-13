package Menu;


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
	private float playScale;
	private float howToScale;
	private float achievementsScale;
	private final float enlarge = 0.0001f;
	private static final String GAMETITLE = "assets/image/menus/MainTitle.png";
	private static final String PLAY = "assets/image/menus/MainPlay.png";
	private static final String HOWTO = "assets/image/menus/MainHowTo.png";
	private static final String ACHIEVEMENTS = "assets/image/menus/MainAchievements.png";
	private static final String SETTINGS = "assets/image/menus/MainSettings.png";
	public static final String GOBACK = "assets/image/menus/GoBack.png";
	private int titlePosX;
	private int titlePosY;
	private int playPosX;
	private int playPosY;
	private int howToPosX;
	private int howToPosY;
	private int achievementsPosX;
	private int achievementsPosY;
	private int settingsPosX;
	private int settingsPosY;
	private Image title;
	private Image play;
	private Image howTo;
	private Image achievements;
	private Image settings;

	public MainMenu(int id) {
		this.id=id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {	
		title = new Image(GAMETITLE);
		play = new Image(PLAY);
		howTo = new Image(HOWTO);
		achievements = new Image(ACHIEVEMENTS);
		settings = new Image(SETTINGS);
		titlePosX = gc.getWidth()/8;
		titlePosY = gc.getHeight()/6;
		playPosX = gc.getWidth()/8;
		playPosY = gc.getHeight()/2;
		howToPosX = gc.getWidth()/8;
		howToPosY = gc.getHeight()/3*2;
		achievementsPosX = gc.getWidth()/8;
		achievementsPosY = gc.getHeight()/6*5;
		settingsPosX = gc.getWidth()/8*7;
		settingsPosY = gc.getHeight()/6*5;
		playScale = 1;
		howToScale = 1;
		achievementsScale = 1;
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		title.draw(titlePosX,titlePosY);
		play.draw(playPosX,playPosY,playScale);
		howTo.draw(howToPosX,howToPosY,howToScale);
		achievements.draw(achievementsPosX,achievementsPosY,achievementsScale);
		settings.draw(settingsPosX,settingsPosY);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		int mouseX;
		int mouseY;
    	boolean onPlay = false;
    	boolean onHowTo = false;
    	boolean onAchievements = false;
    	boolean onSettings = false;
    	
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();
    	
    	if( ( mouseX >= playPosX && mouseX <= playPosX + play.getWidth()) &&
    		    ( mouseY >= playPosY && mouseY <= playPosY + play.getHeight()) ){
    		onPlay = true;
    	}else if( ( mouseX >= howToPosX && mouseX <= howToPosX + howTo.getWidth()) &&
    	          ( mouseY >= howToPosY && mouseY <= howToPosY + howTo.getHeight()) ){
    	    onHowTo = true;
    	}else if( ( mouseX >= achievementsPosX && mouseX <= achievementsPosX + achievements.getWidth()) &&
  	          ( mouseY >= achievementsPosY && mouseY <= achievementsPosY + achievements.getHeight()) ){
    		onAchievements = true;
    	}else if( ( mouseX >= settingsPosX && mouseX <= settingsPosX + settings.getWidth()) &&
  	          ( mouseY >= settingsPosY && mouseY <= settingsPosY + settings.getHeight()) ){
    		onSettings = true;
    	}
    	
    	if(onPlay){
    		if(playScale < 1.05f)
    			playScale += enlarge * delta;
    		
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.PLAYID);
    		}
    	}else{
    		if(playScale > 1.0f)
    			playScale -= enlarge *delta;
    	}
    	
    	if(onHowTo){
    		if(howToScale < 1.05f)
    			howToScale += enlarge * delta;
    		
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.HOWTOMENUID);
    		}
    	}else{
    		if(howToScale > 1.0f)
    			howToScale -= enlarge *delta;
    	}
    	
    	if(onAchievements){
    		if(achievementsScale < 1.05f)
    			achievementsScale += enlarge * delta;
    		
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.ACHIEVEMENTSMENUID);
    		}
    	}else{
    		if(achievementsScale > 1.0f)
    			achievementsScale -= enlarge *delta;
    	}
    	
    	if(onSettings){
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.SETTINGSMENUID);
    		}
    	}
	}

	@Override
	public int getID() {
		return id;
	}
	

}
