package Menu;


import org.newdawn.slick.SlickException;

//Extension of Heading except this checks if mouse is within its bounds and draws according to scale
public class Button extends Heading {
	private float scale;
	private float enlarge;
	private int enterState;
	private final float scaleMin = 1f;
	private final float scaleMax = 1.05f;
	
	public Button(String src, int x, int y, float s, float e, int es) throws SlickException {
		super(src, x, y);
		scale = s;
		enlarge = e;
		enterState = es;
	}

	//Overides Heading draw method taking into account scale
	public void draw(){
		img.draw(xpos, ypos, scale);
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
}
