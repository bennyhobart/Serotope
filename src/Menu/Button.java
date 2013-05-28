package Menu;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

//Extension of Heading except this checks if mouse is within its bounds and draws according to scale
public class Button extends Heading {
	private float scale;
	private float enlarge;
	private int enterState;
	private boolean selected;
	private final float scaleMin = 1f;
	private final float scaleMax = 1.05f;
	
	public Button(String src, int x, int y, float s, float e, int es) throws SlickException {
		super(src, x, y);
		scale = s;
		enlarge = e;
		enterState = es;
		selected = false;
	}

	//Overides Heading draw method taking into account scale
	public void draw(){
		if(selected){
			img.draw(xpos,ypos,scale,Color.blue);
		}else{
			img.draw(xpos, ypos, scale);
		}
	}
	
	//Checks if co-ordinate is inside button's bounds
	public boolean isInside(int x, int y){
    	if( ( x >= xpos && x <= xpos + img.getWidth()) &&
    		    ( y >= ypos && y <= ypos + img.getHeight()) ){
    		return true;
    	}else
    		return false;
	}
	
	//Increases the size of Button's image
	public void increaseSize(int delta){
		if(scale < scaleMax)
			scale += enlarge * delta;
	}
	
	//Decreases the size of Button's image
	public void decreaseSize(int delta){
		if(scale > scaleMin)
			scale -= enlarge *delta;
	}
	
	//Returns the game state the game will enter if button is pressed
	public int getEnterState(){
		return enterState;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public void setSelected(boolean select){
		selected = select;
	}
	
	//General funcction to find if a button is pressed and enter a new game State
	public void buttonPressed(int delta, int x, int y, GameContainer gc, StateBasedGame sbg){
		if(isInside(x, y)){
			increaseSize(delta);
			if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				gc.getInput().clearKeyPressedRecord();
				sbg.enterState(enterState);
			}
		}else{
			decreaseSize(delta);
		}
		if(selected && gc.getInput().isKeyPressed(Input.KEY_ENTER)){
			gc.getInput().clearKeyPressedRecord();
			sbg.enterState(enterState);
		}
	}
}
