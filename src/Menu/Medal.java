package Menu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

//Data structure that holds information on individidual achievements
public class Medal {
	private String name;
	private int current;
	private int bronze;
	private int silver;
	private int gold;
	private int platinum;
	private boolean bronzeUnlocked;
	private boolean silverUnlocked;
	private boolean goldUnlocked;
	private boolean platinumUnlocked;
	
	public Medal(String n, int c, int b, int s, int g, int p) {
		name = n;
		current = c;
		bronze = b;
		silver = s;
		gold = g;
		platinum = p;
		updateMedal();
	}

	public Medal(String n, String c, String b, String s, String g, String p){
		name = n;
		current = Integer.parseInt(c);
		bronze = Integer.parseInt(b);
		silver = Integer.parseInt(s);
		gold = Integer.parseInt(g);
		platinum = Integer.parseInt(p);
		updateMedal();
	}
	
	//Draws the medal achievement line to the screen from position (x,y)
	public void drawLine(int x, int y, int buffer, int nameLength, int boxLength, Graphics g, GameContainer gc) throws SlickException{
		int boxX = x + nameLength;
		int boxY = y;
		
		g.drawString(name, x + buffer, y+boxLength/2);
		x += nameLength;
		Image locked = new Image(Utils.ACHIEVEMENTSLOCKED);
		Image bronzeMedal = new Image(Utils.BRONZEMEDAL);
		Image silverMedal = new Image(Utils.SILVERMEDAL);
		Image goldMedal = new Image(Utils.GOLDMEDAL);
		Image platinumMedal = new Image(Utils.PLATINUMMEDAL);
		if(bronzeUnlocked)
			g.drawImage(bronzeMedal, x, y, x+boxLength, y+boxLength, 0, 0, bronzeMedal.getWidth(), bronzeMedal.getHeight());
		else
			g.drawImage(locked, x, y, x+boxLength, y+boxLength, 0, 0, locked.getWidth(), locked.getHeight());
		x += boxLength;
		if(silverUnlocked)
			g.drawImage(silverMedal, x, y, x+boxLength, y+boxLength, 0, 0, silverMedal.getWidth(), silverMedal.getHeight());
		else
			g.drawImage(locked, x, y, x+boxLength, y+boxLength, 0, 0, locked.getWidth(), locked.getHeight());
		x += boxLength;
		if(goldUnlocked)
			g.drawImage(goldMedal, x, y, x+boxLength, y+boxLength, 0, 0, goldMedal.getWidth(), goldMedal.getHeight());
		else
			g.drawImage(locked, x, y, x+boxLength, y+boxLength, 0, 0, locked.getWidth(), locked.getHeight());
		x += boxLength;
		if(platinumUnlocked)
			g.drawImage(platinumMedal, x, y, x+boxLength, y+boxLength, 0, 0, platinumMedal.getWidth(), platinumMedal.getHeight());
		else
			g.drawImage(locked, x, y, x+boxLength, y+boxLength, 0, 0, locked.getWidth(), locked.getHeight());

		renderMouseOver(boxX,boxY,boxLength,g,gc);
	}
	
	private void renderMouseOver(int x, int y, int boxLength, Graphics g, GameContainer gc){
		String text;
		int mouseY = gc.getInput().getMouseY();
		if(mouseY>y && mouseY<y+boxLength){
			int mouseX = gc.getInput().getMouseX();
			if(mouseX>x && mouseX<x+4*boxLength){
				if(mouseX<x+boxLength){
					text = Integer.toString(current) + '/' + Integer.toString(bronze);
					printBoxMsg(text,mouseX,mouseY,g);
				}else if(mouseX<x+2*boxLength){
					text = Integer.toString(current) + '/' + Integer.toString(silver);
					printBoxMsg(text,mouseX,mouseY,g);
				}else if(mouseX<x+3*boxLength){
					text = Integer.toString(current) + '/' + Integer.toString(gold);
					printBoxMsg(text,mouseX,mouseY,g);
				}else {
					text = Integer.toString(current) + '/' + Integer.toString(platinum);
					printBoxMsg(text,mouseX,mouseY,g);
				}
			}
		}
	}
	
	private void printBoxMsg(String text, int x, int y, Graphics g){
		int tWidth = g.getFont().getWidth(text);
		int tHeight = g.getFont().getHeight(text);
		g.setColor(Color.black);
		g.fillRect(x-tWidth, y-tHeight, tWidth, tHeight);
		g.setColor(Color.white);
		g.drawString(text, x-tWidth, y-tHeight);
	}
	
	//Checks which medals in the achievement are unlocked
	public void updateMedal(){
		if(current>=platinum){
			platinumUnlocked = true;
			goldUnlocked = true;
			silverUnlocked = true;
			bronzeUnlocked = true;
		}else if(current>=gold){
			goldUnlocked = true;
			silverUnlocked = true;
			bronzeUnlocked = true;
		}else if(current>=silver){
			silverUnlocked = true;
			bronzeUnlocked = true;
		}else if(current>=bronze)
			bronzeUnlocked = true;
	}
	
	public int getCurrent(){
		return current;
	}
	
	public void setCurrent(int newCurrent){
		current = newCurrent;
	}
}
