package Menu;

import java.util.ArrayList;

import javax.swing.JPanel;
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
	private static int volumeLevel;
	private ArrayList<Heading> headingList;
	private Button goBack;
	private ArrayList<ControlBox> controlList;
	
	private JSlider volumeSlider;
	private JPanel volumeControl;

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

	public class VolumeChanged implements ChangeListener{	
		public void stateChanged(ChangeEvent ce){
			JSlider source = (JSlider) ce.getSource();
			if (!source.getValueIsAdjusting()) {
	            volumeLevel = (int)source.getValue();
	        }
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
		headingList.add(new Heading(Utils.SETTINGSTITLE,gc.getWidth()/8,gc.getHeight()/6));
		headingList.add(new Heading(Utils.SETTINGSVOLUME,gc.getWidth()/8,gc.getHeight()/3));
		headingList.add(new Heading(Utils.SETTINGSCONTROLS,gc.getWidth()/8,gc.getHeight()/2));
		headingList.add(goBack = new Button(Utils.GOBACK,gc.getWidth()/8*7,gc.getHeight()/12*11,Utils.STARTSCALE,Utils.ENLARGE,gPanel.MAINMENUID));

		controlList = new ArrayList<ControlBox>();
		controlList.add(new ControlBox("Move Left",gc.getWidth()/8,gc.getHeight()/2+Utils.BUFFER,Utils.SETTINGSBOX,InputManager.MoveLeft));
		controlList.add(new ControlBox("Move Right",gc.getWidth()/8,gc.getHeight()/2+Utils.BUFFER*2,Utils.SETTINGSBOX,InputManager.MoveRight));
		controlList.add(new ControlBox("Move Up",gc.getWidth()/8,gc.getHeight()/2+Utils.BUFFER*3,Utils.SETTINGSBOX,InputManager.MoveUp));
		controlList.add(new ControlBox("Move Down",gc.getWidth()/8,gc.getHeight()/2+Utils.BUFFER*4,Utils.SETTINGSBOX,InputManager.MoveDown));
		controlList.add(new ControlBox("Shoot Left",gc.getWidth()/8*3,gc.getHeight()/2+Utils.BUFFER,Utils.SETTINGSBOX,InputManager.ShootLeft));
		controlList.add(new ControlBox("Shoot Right",gc.getWidth()/8*3,gc.getHeight()/2+Utils.BUFFER*2,Utils.SETTINGSBOX,InputManager.ShootRight));
		controlList.add(new ControlBox("Shoot Up",gc.getWidth()/8*3,gc.getHeight()/2+Utils.BUFFER*3,Utils.SETTINGSBOX,InputManager.ShootUp));
		controlList.add(new ControlBox("Shoot Down",gc.getWidth()/8*3,gc.getHeight()/2+Utils.BUFFER*4,Utils.SETTINGSBOX,InputManager.ShootDown));
		controlList.add(new ControlBox("Pause Game",gc.getWidth()/8*5,gc.getHeight()/2+Utils.BUFFER,Utils.SETTINGSBOX,InputManager.KeyEscape));
		controlList.add(new ControlBox("Sprint",gc.getWidth()/8*5,gc.getHeight()/2+Utils.BUFFER*2,Utils.SETTINGSBOX,InputManager.KeySprint));

		volumeLevel = Utils.VOL_MAX;
		volumeSlider = new JSlider(Utils.VOL_MIN,Utils.VOL_MAX,volumeLevel);
		volumeSlider.setMajorTickSpacing(10);
		volumeSlider.setPaintTicks(true);
		volumeSlider.addChangeListener(new VolumeChanged());
		volumeControl = new JPanel();
		volumeControl.add(volumeSlider);
		volumeControl.setLocation(gc.getWidth()/8,gc.getHeight()/2);
		volumeControl.setSize(gc.getWidth()/4*3, gc.getHeight()/8);
		volumeControl.setVisible(true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		for(Heading heading : headingList)
			heading.draw();
		for(ControlBox boxi : controlList){
			boxi.draw();
			g.drawString(boxi.name, boxi.xpos+Utils.STRINGBUFFER, boxi.ypos+(boxi.img.getHeight()/2)-Utils.SETTINGSBUFFER);
			g.drawString(Input.getKeyName(boxi.curKey), boxi.xpos+Utils.BOXBUFFER, boxi.ypos+boxi.img.getHeight()/2-Utils.SETTINGSBUFFER);
		}		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		int mouseX;
		int mouseY;
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY(); 
    	
    	Utils.buttonPressed(delta, mouseX, mouseY, goBack, gc, sbg);
    	
    	for(ControlBox control : controlList)
    		changeControl(control, mouseX, mouseY, gc);
    	
	}
	
	@Override
	public int getID() {
		return id;
	}
	
	private static void changeControl(ControlBox control, int x, int y, GameContainer gc){
		int newKey;
		if(control.isInside(x, y)){
			if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				newKey = InputManager.returnKeyPressed(gc.getInput());
				if(newKey != -1){
					InputManager.KeyEscape = newKey;
					control.curKey = newKey;
				}
			}
		}
	}
	

}
