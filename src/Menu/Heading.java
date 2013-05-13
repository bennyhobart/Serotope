package Menu;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Heading {

	Image img;
	int xpos;
	int ypos;
	
	public Heading(String src, int x, int y) throws SlickException{
		img = new Image(src);
		xpos = x;
		ypos = y;
				
	}
	
	public void draw(){
		img.draw(xpos,ypos);
	}
}
