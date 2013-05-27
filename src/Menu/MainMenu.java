package Menu;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState {

	private int id;
	private ArrayList<Heading> headingList;
	private ArrayList<Button> buttonList;
	private Button play;
	private Button howTo;
	private Button achievements;
	private Button quit;
	private Button settings;

	public MainMenu(int id) {
		this.id = id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		// Set background colour
		Color background = new Color(Color.black);
		gc.getGraphics().setBackground(background);

		// Intialise page's headings and buttons, and put them in their respective lists
		headingList = new ArrayList<Heading>();
		headingList.add(new Heading(Utils.MAINTITLE, Utils.LEFTALIGNX, Utils.TITLEPOSY));
		headingList.add(play = new Button(Utils.MAINPLAY, Utils.LEFTALIGNX, Utils.FIRSTHEADING, Utils.STARTSCALE, Utils.ENLARGE,gPanel.PLAYID));
		headingList.add(howTo = new Button(Utils.MAINHOWTO, Utils.LEFTALIGNX, Utils.SECONDHEADING, Utils.STARTSCALE, Utils.ENLARGE,	gPanel.HOWTOMENUID));
		headingList.add(achievements = new Button(Utils.MAINACHIEVEMENTS, Utils.LEFTALIGNX, Utils.THIRDHEADING, Utils.STARTSCALE,Utils.ENLARGE, gPanel.ACHIEVEMENTSMENUID));
		headingList.add(quit = new Button(Utils.MAINQUIT, Utils.LEFTALIGNX, Utils.FOURTHHEADING, Utils.STARTSCALE, Utils.ENLARGE,gPanel.MAINMENUID));
		headingList.add(settings = new Button(Utils.MAINSETTINGS, Utils.BOTRIGHTX, Utils.SETTINGSY,	Utils.STARTSCALE, Utils.ENLARGE, gPanel.SETTINGSMENUID));
		buttonList = new ArrayList<Button>();
		buttonList.add(play);
		buttonList.add(howTo);
		buttonList.add(achievements);
		buttonList.add(settings);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// Draw all the headings and buttons on the page
		for (Heading heading : headingList)
			heading.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		// Get the mouse's co-ordinates
		int mouseX;
		int mouseY;
		mouseX = gc.getInput().getMouseX();
		mouseY = gc.getInput().getMouseY();

		//Checks if any of the buttons have been pressed and execute the appropriate action
		for (Button button : buttonList) {
			Utils.buttonPressed(delta, mouseX, mouseY, button, gc, sbg);
		}
		if (quit.isInside(mouseX, mouseY)) {
			quit.increaseSize(delta);
			if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				gc.exit();
			}
		} else
			quit.decreaseSize(delta);

	}

	@Override
	public int getID() {
		return id;
	}

}
