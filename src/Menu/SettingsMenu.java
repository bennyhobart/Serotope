package Menu;

import java.util.ArrayList;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SettingsMenu extends BasicGameState {
	
	
	private int id;
	static boolean changingKey= false;
	private float maxVolume;
	private int rectsLevel;
	public static float volumeLevel;
	private ArrayList<Heading> headingList;
	private Button goBack;
	private Button incVol;
	private Button decVol;
	private ArrayList<ControlBox> controlList;

	//Data structure that helps with the customising of controls
	public class ControlBox extends Heading{
		private String name;
		private int curKey;
		boolean changingKey=false;
		public ControlBox(String n,int x,int y,String i, int k) throws SlickException{
			super(i,x,y);
			name = n;
			curKey = k;
		}
		
		//Checks if (x,y) is inside the box
		boolean isInside(int x, int y){
	    	if( ( x >= xpos && x <= xpos + img.getWidth()) &&
	    		    ( y >= ypos && y <= ypos + img.getHeight()) ){
	    		return true;
	    	}else
	    		return false;
		}
		
	}
	
	public SettingsMenu(int id) {
		this.id=id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
        //Sets colours and background
		Color background = new Color(Color.black);
        gc.getGraphics().setBackground(background);
        
        //Initialises all buttons and headings and puts them into a list
		headingList = new ArrayList<Heading>();
		headingList.add(new Heading(Utils.SETTINGSTITLE,gc.getWidth()/8,gc.getHeight()/6));
		headingList.add(new Heading(Utils.SETTINGSVOLUME,gc.getWidth()/8,gc.getHeight()/3));
		headingList.add(new Heading(Utils.SETTINGSCONTROLS,gc.getWidth()/8,gc.getHeight()/2));
		headingList.add(goBack = new Button(Utils.GOBACK,gc.getWidth()/8*7,gc.getHeight()/12*11,Utils.STARTSCALE,Utils.ENLARGE,gPanel.MAINMENUID));
		headingList.add(decVol = new Button(Utils.SETTINGSDEC,gc.getWidth()/8+20,gc.getHeight()/12*5+20,Utils.STARTSCALE,Utils.ENLARGE,gPanel.SETTINGSMENUID));
		headingList.add(incVol = new Button(Utils.SETTINGSINC,gc.getWidth()/14*13,gc.getHeight()/12*5,Utils.STARTSCALE,Utils.ENLARGE,gPanel.SETTINGSMENUID));
		
		//Initialises the control boxes and adds them into a list together
        int buffer = gc.getHeight()/10;
		controlList = new ArrayList<ControlBox>();
		controlList.add(new ControlBox("Move Left",gc.getWidth()/8,gc.getHeight()/2+buffer,Utils.SETTINGSBOX,InputManager.MoveLeftRef));
		controlList.add(new ControlBox("Move Right",gc.getWidth()/8,gc.getHeight()/2+buffer*2,Utils.SETTINGSBOX,InputManager.MoveRightRef));
		controlList.add(new ControlBox("Move Up",gc.getWidth()/8,gc.getHeight()/2+buffer*3,Utils.SETTINGSBOX,InputManager.MoveUpRef));
		controlList.add(new ControlBox("Move Down",gc.getWidth()/8,gc.getHeight()/2+buffer*4,Utils.SETTINGSBOX,InputManager.MoveDownRef));
		controlList.add(new ControlBox("Shoot Left",gc.getWidth()/8*3,gc.getHeight()/2+buffer,Utils.SETTINGSBOX,InputManager.ShootLeftRef));
		controlList.add(new ControlBox("Shoot Right",gc.getWidth()/8*3,gc.getHeight()/2+buffer*2,Utils.SETTINGSBOX,InputManager.ShootRightRef));
		controlList.add(new ControlBox("Shoot Up",gc.getWidth()/8*3,gc.getHeight()/2+buffer*3,Utils.SETTINGSBOX,InputManager.ShootUpRef));
		controlList.add(new ControlBox("Shoot Down",gc.getWidth()/8*3,gc.getHeight()/2+buffer*4,Utils.SETTINGSBOX,InputManager.ShootDownRef));
		controlList.add(new ControlBox("Pause Game",gc.getWidth()/8*5,gc.getHeight()/2+buffer,Utils.SETTINGSBOX,InputManager.PauseRef));
		controlList.add(new ControlBox("Sprint",gc.getWidth()/8*5,gc.getHeight()/2+buffer*2,Utils.SETTINGSBOX,InputManager.SprintRef));

		//Initialises sound variables
		maxVolume = gc.getSoundVolume();
		rectsLevel = 10;		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		//Draws each heading and button onto the screen
		for(Heading heading : headingList)
			heading.draw();
		
		//Draws each control box onto the screen
		int adjust = gc.getHeight()/70;
		for(ControlBox boxi : controlList){
			boxi.draw();
			g.drawString(boxi.name, boxi.xpos+gc.getWidth()/10, boxi.ypos+boxi.img.getHeight()/2-adjust);
			g.drawString(InputManager.returnkey(boxi.curKey), boxi.xpos+adjust, boxi.ypos+boxi.img.getHeight()/2-adjust);
		}		
		
		//Draws the sound bar as a collection of rectangles incrementally getting longer in length
		g.setColor(Color.blue);
		int numRects = rectsLevel;
		int rectX = gc.getWidth()/14*3;
		int rectY = gc.getHeight()/12*5;
		int height = gc.getHeight()/120;
		int incX = gc.getWidth()/14;
		int incY = height;
		int fullheight = gc.getHeight()/12;
		for(int i=0; i<10; i++){
			if(!(numRects>0)){
				g.setColor(Color.white);
			}
			g.fillRect(rectX, rectY+(fullheight-height), incX/2, height);
			rectX += incX;
			height += incY;
			numRects--;
		}
		g.setColor(Color.white);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		//Gets the co-ordinates the mouses position
		int mouseX;
		int mouseY;
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY(); 
    	
    	//Checks if the goBack button is pressed
    	Utils.buttonPressed(delta, mouseX, mouseY, goBack, gc, sbg);
    	
    	//Checks if volume buttons are pressed and either raises or lowers the volume
    	if(incVol.isInside(mouseX, mouseY)){
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && rectsLevel<10){
    			rectsLevel ++;
    			gc.setSoundVolume(rectsLevel/10*maxVolume);
    		}
    	}
    	
    	if(decVol.isInside(mouseX, mouseY)){
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && rectsLevel>0){
    			rectsLevel --;
    			gc.setSoundVolume(rectsLevel/10*maxVolume);
    		}
    	}
    	
    	//Checks if the control box option is selected and then gets new input for the replacement key
    	for(ControlBox control : controlList) {
    		changeControl(control, mouseX, mouseY, gc);
    		if(control.changingKey) {
    			int newKey = InputManager.returnKeyPressed(gc.getInput());
				if(newKey != -1){
					control.changingKey=false;
					InputManager.changeKey(control.curKey, newKey);
				}
    		}
    	}
	}
	
	@Override
	public int getID() {
		return id;
	}
	
	//Handles a flag for sensing when a key is been changed
	private static void changeControl(ControlBox control, int x, int y, GameContainer gc){
		if(control.isInside(x, y)){
			if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				control.changingKey=true;
			}
		}
	}
	

}
