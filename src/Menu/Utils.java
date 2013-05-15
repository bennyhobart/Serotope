package Menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Utils {
	public static final int VOL_MIN = 0;
	public static final int VOL_MAX = 100;
	public static final int BUFFER = 60;
	public static final int STRINGBUFFER = 100;
	public static final int BOXBUFFER = 10;
	public static final int ARROWBUFFER = 20;
	public static final int FSCOREBUFFER = 10;
	public static final int SCOREBUFFER = 30;
	public static final int SETTINGSBUFFER = 10;
	public static final float ENLARGE = 0.0001f;
	public static final float STARTSCALE = 1f;
	public static final String HIGHSCOREFILE = "assets/data/highscores.txt";
	public static final String GOBACK = "assets/image/menus/GoBack.png";
	public static final String ACHIEVEMENTSTITLE = "assets/image/menus/AchievementsTitle.png";
	public static final String ACHIEVEMENTSHIGHSCORE = "assets/image/menus/AchievementsHighscore.png";
	public static final String ACHIEVEMENTSMEDAL = "assets/image/menus/AchievementsMedal.png";
	public static final String SETTINGSTITLE = "assets/image/menus/SettingsTitle.png";
	public static final String SETTINGSBOX = "assets/image/menus/SettingsBox.png";
	public static final String SETTINGSVOLUME = "assets/image/menus/SettingsVolume.png";
	public static final String SETTINGSCONTROLS = "assets/image/menus/SettingsControls.png";
	public static final String GAMEOVERTITLE = "assets/image/menus/GameOverTitle.png";
	public static final String GAMEOVERFINALSCORE = "assets/image/menus/GameOverScore.png";
	public static final String GAMEOVERREPLAY = "assets/image/menus/GameOverReplay.png";
	public static final String GAMEOVERRETURNMAINMENU = "assets/image/menus/GameOverReturn.png";
	public static final String HOWTOTITLE = "assets/image/menus/HowToTitle.png";
	public static final String HOWTOPREV = "assets/image/menus/HowToPrev.png";
	public static final String HOWTONEXT = "assets/image/menus/HowToNext.png";
	public static final String HOWTODUMMY = "assets/image/menus/HowToPages/DummyPage.png";
	public static final String MAINTITLE = "assets/image/menus/MainTitle.png";
	public static final String MAINPLAY = "assets/image/menus/MainPlay.png";
	public static final String MAINHOWTO = "assets/image/menus/MainHowTo.png";
	public static final String MAINACHIEVEMENTS = "assets/image/menus/MainAchievements.png";
	public static final String MAINSETTINGS = "assets/image/menus/MainSettings.png";
	public static final String MAINQUIT = "assets/image/menus/MainQuit.png";
	
	
	public static void buttonPressed(int delta, int x, int y, Button button, GameContainer gc, StateBasedGame sbg){
		if(button.isInside(x, y)){
			button.increaseSize(delta);
			if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				sbg.enterState(button.getEnterState());
			}
		}else{
			button.decreaseSize(delta);
		}
	}
}
