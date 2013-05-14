package Menu;

import java.util.ArrayList;

import javax.swing.JSlider;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SettingsMenu extends BasicGameState {
	
	
	private int id;
	private static final String TITLE = "assets/image/menus/SettingsTitle.png";
	private static final String BOX = "assets/image/menus/SettingsBox.png";
	private static final String VOLUME = "assets/image/menus/SettingsVolume.png";
	private static final String CONTROLS = "assets/image/menus/SettingsControls.png";
	private static final int VOL_MIN = 0;
	private static final int VOL_MAX = 100;
	
	private ArrayList<Heading> headingList;
	private Button goBack;
	
	private ArrayList<ControlBox> controlList;
	private ControlBox leftBox;
	private ControlBox rightBox;
	private ControlBox upBox;
	private ControlBox downBox;
	private ControlBox sleftBox;
	private ControlBox srightBox;
	private ControlBox supBox;
	private ControlBox sdownBox;
	private ControlBox pauseBox;
	private ControlBox sprintBox;

	public class ControlBox extends Heading{
		private String name;
		private int curKey;
		public ControlBox(String n,int x,int y,String i, int k) throws SlickException{
			super(i,x,y);
			name = n;
			curKey = k;
		}
		
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
        Color background = new Color(Color.black);
        gc.getGraphics().setBackground(background);
		
		headingList = new ArrayList<Heading>();
		headingList.add(new Heading(TITLE,gc.getWidth()/8,gc.getHeight()/6));
		headingList.add(new Heading(VOLUME,gc.getWidth()/8,gc.getHeight()/3));
		headingList.add(new Heading(CONTROLS,gc.getWidth()/8,gc.getHeight()/2));
		headingList.add(goBack = new Button(MainMenu.GOBACK,gc.getWidth()/8*7,gc.getHeight()/12*11,MainMenu.startScale,MainMenu.enlarge));

		controlList = new ArrayList<ControlBox>();
		controlList.add(leftBox = new ControlBox("Move Left",gc.getWidth()/8,gc.getHeight()/2+60,BOX,InputManager.MoveLeft));
		controlList.add(rightBox = new ControlBox("Move Right",gc.getWidth()/8,gc.getHeight()/2+120,BOX,InputManager.MoveRight));
		controlList.add(upBox = new ControlBox("Move Up",gc.getWidth()/8,gc.getHeight()/2+180,BOX,InputManager.MoveUp));
		controlList.add(downBox = new ControlBox("Move Down",gc.getWidth()/8,gc.getHeight()/2+240,BOX,InputManager.MoveDown));
		controlList.add(sleftBox = new ControlBox("Shoot Left",gc.getWidth()/8*3,gc.getHeight()/2+60,BOX,InputManager.ShootLeft));
		controlList.add(srightBox = new ControlBox("Shoot Right",gc.getWidth()/8*3,gc.getHeight()/2+120,BOX,InputManager.ShootRight));
		controlList.add(supBox = new ControlBox("Shoot Up",gc.getWidth()/8*3,gc.getHeight()/2+180,BOX,InputManager.ShootUp));
		controlList.add(sdownBox = new ControlBox("Shoot Down",gc.getWidth()/8*3,gc.getHeight()/2+240,BOX,InputManager.ShootDown));
		controlList.add(pauseBox = new ControlBox("Pause Game",gc.getWidth()/8*5,gc.getHeight()/2+60,BOX,InputManager.KeyEscape));
		controlList.add(sprintBox = new ControlBox("Sprint",gc.getWidth()/8*5,gc.getHeight()/2+120,BOX,InputManager.KeySprint));

		
		/*
		JSlider volumeSlider = new JSlider(VOL_MIN,VOL_MAX,VOL_MAX);
		volumeSlider.setMajorTickSpacing(10);
		volumeSlider.setPaintTicks(true);
		*/
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		for(Heading heading : headingList)
			heading.draw();
		for(ControlBox boxi : controlList){
			boxi.draw();
			g.drawString(boxi.name, boxi.xpos+100, boxi.ypos+(boxi.img.getHeight()/2));
			g.drawString(Input.getKeyName(boxi.curKey), boxi.xpos+20, boxi.ypos+boxi.img.getHeight()/2);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		int mouseX;
		int mouseY;
    	
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();
    	
    	
    	if(goBack.isInside(mouseX, mouseY)){
    		goBack.increaseSize(delta);
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.MAINMENUID);
    		}
    	}else{
    		goBack.decreaseSize(delta);
    	}
    	
   		if(leftBox.isInside(mouseX, mouseY)){
       		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
       			InputManager.MoveLeft = InputManager.returnKeyPressed(gc.getInput());
       			leftBox.curKey = InputManager.MoveLeft;
       		}
   		}else if(rightBox.isInside(mouseX, mouseY)){
       		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
       			InputManager.MoveRight = InputManager.returnKeyPressed(gc.getInput());
       			rightBox.curKey = InputManager.MoveRight;
       		}
   		}else if(downBox.isInside(mouseX, mouseY)){
       		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
       			InputManager.MoveDown = InputManager.returnKeyPressed(gc.getInput());
       			downBox.curKey = InputManager.MoveDown;
       		}
   		}else if(upBox.isInside(mouseX, mouseY)){
       		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
       			InputManager.MoveUp = InputManager.returnKeyPressed(gc.getInput());
       			upBox.curKey = InputManager.MoveUp;
       		}
   		}else if(sleftBox.isInside(mouseX, mouseY)){
       		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
       			InputManager.ShootLeft = InputManager.returnKeyPressed(gc.getInput());
       			sleftBox.curKey = InputManager.ShootLeft;
       		}
   		}else if(srightBox.isInside(mouseX, mouseY)){
       		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
       			InputManager.ShootRight = InputManager.returnKeyPressed(gc.getInput());
       			srightBox.curKey = InputManager.ShootRight;
       		}
   		}else if(supBox.isInside(mouseX, mouseY)){
       		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
       			InputManager.ShootUp = InputManager.returnKeyPressed(gc.getInput());
       			supBox.curKey = InputManager.ShootUp;
       		}
   		}else if(sdownBox.isInside(mouseX, mouseY)){
       		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
       			InputManager.ShootDown = InputManager.returnKeyPressed(gc.getInput());
       			sdownBox.curKey = InputManager.ShootDown;
       		}
   		}else if(sprintBox.isInside(mouseX, mouseY)){
       		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
       			InputManager.KeySprint = InputManager.returnKeyPressed(gc.getInput());
       			sprintBox.curKey = InputManager.KeySprint;
       		}
   		}else if(pauseBox.isInside(mouseX, mouseY)){
       		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
       			InputManager.KeyEscape = InputManager.returnKeyPressed(gc.getInput());
       			pauseBox.curKey = InputManager.KeyEscape;
       		}
   		}
	}
	
	@Override
	public int getID() {
		return id;
	}
	

}
