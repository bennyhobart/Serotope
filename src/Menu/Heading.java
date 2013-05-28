package Menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

//Helper class for all the game's headings
public class Heading {

	Image img;
	int xpos;
	int ypos;
	
	public Heading(String src, int x, int y) throws SlickException{
		img = new Image(src);
		xpos = x;
		ypos = y;
				
	}
	
	//Draws Headings image to the screen
	public void draw(){
		img.draw(xpos,ypos);
	}
}
