package Menu;

import java.awt.Font;

import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Serotope.GameWorld;



public class Play extends BasicGameState {
	static GameWorld world;
	private int id;
	public static int gameScore;
	public static int rank;
	private boolean pause;
	
	private TrueTypeFont scoreFont;
	private ArrayList<Heading> headingList;
	private ArrayList<Button> buttonList;
	private Button resume;
	private Button restart;
	private Button returnMainMenu;
	private int selection;

	public Play(int id) {
		this.id=id;
	}
	
	public class ResumeButton extends Button{

		public ResumeButton(String src, int x, int y, float s, float e, int es)
				throws SlickException {
			super(src, x, y, s, e, es);
		}
		
		public boolean buttonPressed(int delta, int x, int y, GameContainer gc, StateBasedGame sbg){
			boolean mouseOver = false;
			if(isInside(x, y)){
				mouseOver = true;
				increaseSize(delta);
	    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
	    			pause = false;
	    		}
	    	}else{
	    		decreaseSize(delta);
	    	}
			if(isSelected() && gc.getInput().isKeyPressed(Input.KEY_RETURN)){
				pause = false;
			}
			return mouseOver;
		}
	}
	
	public class MainMenuButton extends Button{

		public MainMenuButton(String src, int x, int y, float s, float e, int es)
				throws SlickException {
			super(src, x, y, s, e, es);
		}
		
		public boolean buttonPressed(int delta, int x, int y, GameContainer gc, StateBasedGame sbg){
			boolean mouseOver = false;
			if(isInside(x, y)){
				mouseOver = true;
				increaseSize(delta);
	    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
	    			gc.getInput().clearKeyPressedRecord();
	    			world = new GameWorld("Serotope");
	    			GameWorld.setScore(Utils.RESETSCORE);
	    			pause = false;
					sbg.enterState(getEnterState());
	    		}
	    	}else{
	    		decreaseSize(delta);
	    	}
			if(isSelected() && gc.getInput().isKeyPressed(Input.KEY_RETURN)){
				gc.getInput().clearKeyPressedRecord();
				world = new GameWorld("Serotope");
				GameWorld.setScore(Utils.RESETSCORE);
				pause = false;
				sbg.enterState(getEnterState());
			}
			return mouseOver;
		}
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		world = GameWorld.getGameWorld();
		gameScore = 0;
		pause = false;
	
		scoreFont = new TrueTypeFont(new java.awt.Font("Vedana", Font.BOLD, 48), false);
		
		headingList = new ArrayList<Heading>();
		headingList.add(new Heading(Utils.PAUSETITLE,Utils.LEFTALIGNX,Utils.TITLEPOSY));
		headingList.add(resume = new ResumeButton(Utils.PAUSERESUME,Utils.LEFTALIGNX,Utils.SECONDHEADING,Utils.STARTSCALE,Utils.ENLARGE,gPanel.PLAYID));
		headingList.add(restart = new MainMenuButton(Utils.PAUSERESTART,Utils.LEFTALIGNX,Utils.THIRDHEADING,Utils.STARTSCALE,Utils.ENLARGE,gPanel.PLAYID));
		headingList.add(returnMainMenu = new MainMenuButton(Utils.GAMEOVERRETURNMAINMENU,Utils.LEFTALIGNX,Utils.FOURTHHEADING,Utils.STARTSCALE,Utils.ENLARGE,gPanel.MAINMENUID));
		buttonList = new ArrayList<Button>();
		buttonList.add(resume);
		buttonList.add(restart);
		buttonList.add(returnMainMenu);
		selection = 0;
		resume.setSelected(true);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.setBackground(Color.black);
		
		if(pause){
			g.setFont(scoreFont);

			for(Heading heading : headingList){
				heading.draw();
			}
			
			gameScore = world.getScore();
			g.drawString("Score: " + Integer.toString(gameScore), Utils.LEFTALIGNX, Utils.FIRSTHEADING);
		}else
			world.render(g);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		if(pause){
			
			//Gets mouse co-ordinates
			int mouseX;
			int mouseY;
	    	mouseX = gc.getInput().getMouseX();
	    	mouseY = gc.getInput().getMouseY();    	
	    	
	    	
			//Checks if a button is pressed and executes its action
			for(Button button : buttonList)
				if(button.buttonPressed(delta, mouseX, mouseY, gc, sbg)){
					buttonList.get(selection).setSelected(false);
					button.setSelected(true);
					selection = buttonList.indexOf(button);
				}
			
			if(gc.getInput().isKeyPressed(Input.KEY_DOWN)){
				buttonList.get(selection).setSelected(false);
				selection = (selection+1)%buttonList.size();
				buttonList.get(selection).setSelected(true);
			}else if(gc.getInput().isKeyPressed(Input.KEY_UP)){
				buttonList.get(selection).setSelected(false);
				if(selection==0){
					selection = buttonList.size()-1;
				}else
					selection -= 1;
				buttonList.get(selection).setSelected(true);
			}
			
		}else {
			world.update(delta, gc);
			
			if(InputManager.isKeyEscape){
				pause = true;
			}
			
			//Checks if player has died and ends the game
			//Then checks player's score to see if a highscore needs to be entered or not
			if(world.getPlayer().doomed){
				gPanel.database.updateAchievements(GameWorld.gameStats);
				gameScore = world.getScore();
				rank = 0;
				ArrayList<Highscore> highscores = gPanel.database.getHighscore();
				if(highscores.size() < Utils.MAXHIGHSCORES || gameScore >= highscores.get(highscores.size()-1).getScore()){
					rank = gPanel.database.calcRank(gameScore);
					gc.getInput().clearKeyPressedRecord();
					sbg.enterState(gPanel.ENTERSCOREID);
				}else
					sbg.enterState(gPanel.GAMEOVERMENUID);
			}
		}
	}

	@Override
	public int getID() {
		return id;
	}

}
