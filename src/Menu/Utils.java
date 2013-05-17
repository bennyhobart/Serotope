package Menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Utils {
	public static final float ENLARGE = 0.0001f;
	public static final float STARTSCALE = 1f;
	public static final int MAXSTRING = 10;
	public static final int SPACING = gPanel.PWIDTH/6;
	public static final int MAXHIGHSCORES = 10;
	public static final String HIGHSCOREFILE = "assets/data/highscores.txt";
	public static final String GOBACK = "assets/image/menus/GoBack.png";
	public static final String ACHIEVEMENTSTITLE = "assets/image/menus/AchievementsTitle.png";
	public static final String ACHIEVEMENTSHIGHSCORE = "assets/image/menus/AchievementsHighscore.png";
	public static final String ACHIEVEMENTSMEDAL = "assets/image/menus/AchievementsMedal.png";
	public static final String SETTINGSTITLE = "assets/image/menus/SettingsTitle.png";
	public static final String SETTINGSBOX = "assets/image/menus/SettingsBox.png";
	public static final String SETTINGSVOLUME = "assets/image/menus/SettingsVolume.png";
	public static final String SETTINGSCONTROLS = "assets/image/menus/SettingsControls.png";
	public static final String SETTINGSINC = "assets/image/menus/SettingsInc.png";
	public static final String SETTINGSDEC = "assets/image/menus/SettingsDec.png";
	public static final String GAMEOVERTITLE = "assets/image/menus/GameOverTitle.png";
	public static final String GAMEOVERFINALSCORE = "assets/image/menus/GameOverScore.png";
	public static final String GAMEOVERREPLAY = "assets/image/menus/GameOverReplay.png";
	public static final String GAMEOVERRETURNMAINMENU = "assets/image/menus/GameOverReturn.png";
	public static final String GAMEOVERENTER = "assets/image/menus/GameOverEnter.png";
	public static final String GAMEOVERDONE = "assets/image/menus/GameOverDone.png";
	public static final String HIGHSCORETITLE = "assets/image/menus/HighscoreTitle.png";
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
