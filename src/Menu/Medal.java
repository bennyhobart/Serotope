package Menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
	
	public void drawLine(int x, int y, int buffer, int nameLength, int boxLength, Graphics g) throws SlickException{
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
	}
	
	public void updateMedal(){
		if(current>platinum){
			platinumUnlocked = true;
			goldUnlocked = true;
			silverUnlocked = true;
			bronzeUnlocked = true;
		}else if(current>gold){
			goldUnlocked = true;
			silverUnlocked = true;
			bronzeUnlocked = true;
		}else if(current>silver){
			silverUnlocked = true;
			bronzeUnlocked = true;
		}else if(current>bronze)
			bronzeUnlocked = true;
	}
	
	public int getCurrent(){
		return current;
	}
	
	public void setCurrent(int newCurrent){
		current = newCurrent;
	}
}
