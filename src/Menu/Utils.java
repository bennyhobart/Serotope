package Menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Utils {
	//General positions on the screen for rendered certain headings or buttons
	public static final int LEFTALIGNX = gPanel.PWIDTH/8;
	public static final int TITLEPOSY = gPanel.PHEIGHT/6;
	public static final int FIRSTHEADING = gPanel.PHEIGHT/3;
	public static final int SECONDHEADING = gPanel.PHEIGHT/2;
	public static final int THIRDHEADING = gPanel.PHEIGHT/3*2;
	public static final int FOURTHHEADING = gPanel.PHEIGHT/6*5;
	public static final int BOTRIGHTX = gPanel.PWIDTH/8*7;
	public static final int BOTRIGHTY = gPanel.PHEIGHT/12*11;
	public static final int SETTINGSY = gPanel.PHEIGHT/8*7;

	//Some miscellaneous contants used
	public static final int RESETSCORE = 0;
	public static final float ENLARGE = 0.0001f;
	public static final float STARTSCALE = 1f;
	public static final int MAXSTRING = 10;
	public static final int SPACING = gPanel.PWIDTH/6;
	public static final int MAXHIGHSCORES = 10;
	
	//Data for SerotopeData singleton
	public static final String HIGHSCOREFILE = "assets/data/highscores.txt";
	public static final String ACHIEVEMENTSFILE = "assets/data/medals.txt";
	public static final String BRONZEMEDAL = "assets/image/menus/BronzeMedal.png";
	public static final String SILVERMEDAL = "assets/image/menus/SilverMedal.png";
	public static final String GOLDMEDAL = "assets/image/menus/GoldMedal.png";
	public static final String PLATINUMMEDAL = "assets/image/menus/PlatinumMedal.png";
	public static final String CURRENTSTATSFILE = "assets/data/curStats.txt";
	
	//Image file paths for all headings and buttons in the menus
	public static final String GOBACK = "assets/image/menus/GoBack.png";
	public static final String ACHIEVEMENTSTITLE = "assets/image/menus/AchievementsTitle.png";
	public static final String ACHIEVEMENTSHIGHSCORE = "assets/image/menus/AchievementsHighscore.png";
	public static final String ACHIEVEMENTSMEDAL = "assets/image/menus/AchievementsMedal.png";
	public static final String ACHIEVEMENTSLOCKED = "assets/image/menus/AchievementsLocked.png";
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
	
	//Image file paths for the How To Pages
	public static final String HOWTOPAGE1T = "assets/image/menus/HowToPages/Page1T.png";
	public static final String HOWTOPAGE1P = "assets/image/menus/HowToPages/Page1P.png";
	public static final String HOWTOPAGE2T = "assets/image/menus/HowToPages/Page2T.png";
	public static final String HOWTOPAGE2P = "assets/image/menus/HowToPages/Page2P.png";
	public static final String HOWTOPAGE3T = "assets/image/menus/HowToPages/Page3T.png";
	public static final String HOWTOPAGE3P = "assets/image/menus/HowToPages/Page3P.png";
	public static final String HOWTOPAGE4T = "assets/image/menus/HowToPages/Page4T.png";
	public static final String HOWTOPAGE4P = "assets/image/menus/HowToPages/Page4P.png";
	public static final String HOWTOPAGE5T = "assets/image/menus/HowToPages/Page5T.png";
	public static final String HOWTOPAGE5P = "assets/image/menus/HowToPages/Page5P.png";

}
