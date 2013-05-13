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
	private float goBackScale;
	private final float enlarge = 0.0001f;
	private static final String TITLE = "assets/image/menus/SettingsTitle.png";
	private static final String BOX = "assets/image/menus/SettingsBox.png";
	private static final String VOLUME = "assets/image/menus/SettingsVolume.png";
	private static final String CONTROLS = "assets/image/menus/SettingsControls.png";
	private static final int VOL_MIN = 0;
	private static final int VOL_MAX = 100;
	private int titlePosX;
	private int titlePosY;
	private int volumePosX;
	private int volumePosY;
	private int controlsPosX;
	private int controlsPosY;
	private int goBackPosX;
	private int goBackPosY;
	private Image title;
	private Image box;
	private Image volume;
	private Image controls;
	private Image goBack;
	
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

	public class ControlBox{
		private String name;
		private int xpos;
		private int ypos;
		private Image img;
		public ControlBox(String n,int x,int y,Image i){
			name = n;
			ypos = y;
			xpos = x;
			img = i;
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

		title = new Image(TITLE);
		box = new Image(BOX);
		volume = new Image(VOLUME);
		controls = new Image(CONTROLS);
		goBack = new Image(MainMenu.GOBACK);
		titlePosX = gc.getWidth()/8;
		titlePosY = gc.getHeight()/6;
		volumePosX = gc.getWidth()/8;
		volumePosY = gc.getHeight()/3;
		controlsPosX = gc.getWidth()/8;
		controlsPosY = gc.getHeight()/2;
		goBackPosX = gc.getWidth()/8*7;
		goBackPosY = gc.getHeight()/6*5;
		goBackScale = 1;
		
		controlList = new ArrayList<ControlBox>();
		controlList.add(leftBox = new ControlBox("Move Left",gc.getWidth()/8,gc.getHeight()/2+60,box));
		controlList.add(rightBox = new ControlBox("Move Right",gc.getWidth()/8,gc.getHeight()/2+120,box));
		controlList.add(upBox = new ControlBox("Move Up",gc.getWidth()/8,gc.getHeight()/2+180,box));
		controlList.add(downBox = new ControlBox("Move Down",gc.getWidth()/8,gc.getHeight()/2+240,box));
		controlList.add(sleftBox = new ControlBox("Shoot Left",gc.getWidth()/8*3,gc.getHeight()/2+60,box));
		controlList.add(srightBox = new ControlBox("Shoot Right",gc.getWidth()/8*3,gc.getHeight()/2+120,box));
		controlList.add(supBox = new ControlBox("Shoot Up",gc.getWidth()/8*3,gc.getHeight()/2+180,box));
		controlList.add(sdownBox = new ControlBox("Shoot Down",gc.getWidth()/8*3,gc.getHeight()/2+240,box));
		controlList.add(pauseBox = new ControlBox("Pause Game",gc.getWidth()/8*5,gc.getHeight()/2+60,box));
		controlList.add(sprintBox = new ControlBox("Sprint",gc.getWidth()/8*5,gc.getHeight()/2+120,box));

		
		/*
		JSlider volumeSlider = new JSlider(VOL_MIN,VOL_MAX,VOL_MAX);
		volumeSlider.setMajorTickSpacing(10);
		volumeSlider.setPaintTicks(true);
		*/
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		title.draw(titlePosX,titlePosY);
		volume.draw(volumePosX,volumePosY);
		controls.draw(controlsPosX,controlsPosY);
		goBack.draw(goBackPosX,goBackPosY,goBackScale);
		for(ControlBox boxi : controlList){
			boxi.img.draw(boxi.xpos,boxi.ypos);
			g.drawString(boxi.name, boxi.xpos+100, boxi.ypos+(boxi.img.getHeight()/2));
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		int mouseX;
		int mouseY;
    	boolean onGoBack = false;
    	
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();
    	
    	if( ( mouseX >= goBackPosX && mouseX <= goBackPosX + goBack.getWidth()) &&
    		    ( mouseY >= goBackPosY && mouseY <= goBackPosY + goBack.getHeight()) ){
    		onGoBack = true;
    	}
    	
    	if(onGoBack){
    		if(goBackScale < 1.05f)
    			goBackScale += enlarge * delta;
    		
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.MAINMENUID);
    		}
    	}else{
    		if(goBackScale > 1.0f)
    			goBackScale -= enlarge *delta;
    	}
    	
	}

	@Override
	public int getID() {
		return id;
	}
	

}
