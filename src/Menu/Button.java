package Menu;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Button extends Heading {
	private float scale;
	private float enlarge;
	private final float scaleMin = 1f;
	private final float scaleMax = 1.05f;
	
	public Button(String src, int x, int y, float s, float e) throws SlickException {
		super(src, x, y);
		scale = s;
		enlarge = e;
	}

	public void draw(){
		img.draw(xpos, ypos, scale);
	}
	
	public boolean isInside(int x, int y){
    	if( ( x >= xpos && x <= xpos + img.getWidth()) &&
    		    ( y >= ypos && y <= ypos + img.getHeight()) ){
    		return true;
    	}else
    		return false;
	}
	
	public void increaseSize(int delta){
		if(scale < scaleMax)
			scale += enlarge * delta;
	}
	
	public void decreaseSize(int delta){
		if(scale > scaleMin)
			scale -= enlarge *delta;
	}
}
