package Menu;

import org.newdawn.slick.Graphics;

import java.awt.Font;
import java.awt.FontMetrics;

//Data structure that contains individual highscore data
public class Highscore{
	private String name;
	private int score;
	
	public Highscore(String n, int sc){
		name = n;
		score = sc;
	}

	public Highscore(String n, String sc){
		name = n;
		score = Integer.parseInt(sc);
	}
	
	//Draws the highscore to the position (x,y)
	public void drawLine(int x, int y, int rank, Graphics g){
		String pScore = Integer.toString(score);
		g.drawString(rank + ". " + name, x, y); 
		g.drawString(pScore, x+Utils.SPACING, y);
		
	}
	
	public String getName(){
		return name;
	}
	
	public int getScore(){
		return score;
	}
}
