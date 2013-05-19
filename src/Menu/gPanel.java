package Menu;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class gPanel extends StateBasedGame
{
	final static String gameName = "Serotope";
	final static String VERSION = "0.9.5.13";
	
	
	public static final int MAINMENUID = 0;
	public static final int PLAYID = 1;
	public static final int SPLASHMENUID = 2;
	public static final int HOWTOMENUID = 3;
	public static final int ACHIEVEMENTSMENUID = 4;
	public static final int SETTINGSMENUID = 5;
	public static final int GAMEOVERMENUID = 6;
	public static final int ENTERSCOREID = 7;
	
	public static final int PWIDTH = 1100;
	public static final int PHEIGHT=700;
	public static final int FPS = 60;
		
	public static SerotopeData database;
	public static InputManager Input;
	
	public static void main(String[] args) {
		try {
			database = SerotopeData.getInstance(Utils.HIGHSCOREFILE,Utils.ACHIEVEMENTSFILE,Utils.CURRENTSTATSFILE);
			AppGameContainer app = new AppGameContainer(new gPanel());
			app.setDisplayMode(PWIDTH, PHEIGHT, false);
			Input = new InputManager(app); 
			app.setShowFPS(false);
			app.setTargetFrameRate(FPS);
			app.start();
		
		}
		catch(SlickException e) {
			e.printStackTrace();
		}
	}	
	
	public gPanel(){
		super(gameName+ " " +VERSION );
		this.addState(new MainMenu(MAINMENUID));
		this.addState(new Play(PLAYID));
		this.addState(new SplashMenu(SPLASHMENUID));
		this.addState(new HowToMenu(HOWTOMENUID));
		this.addState(new AchievementsMenu(ACHIEVEMENTSMENUID));
		this.addState(new SettingsMenu(SETTINGSMENUID));
		this.addState(new GameOverMenu(GAMEOVERMENUID));
		this.addState(new EnterHighscore(ENTERSCOREID));
	}
	
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setVSync(true);
		this.enterState(SPLASHMENUID);
		this.getState(MAINMENUID).init(gc,this);
		this.getState(PLAYID).init(gc, this);
		this.getState(HOWTOMENUID).init(gc,this);
		this.getState(ACHIEVEMENTSMENUID).init(gc, this);
		this.getState(SETTINGSMENUID).init(gc, this);
		this.getState(GAMEOVERMENUID).init(gc, this);
		this.getState(ENTERSCOREID).init(gc, this);
	}
}
