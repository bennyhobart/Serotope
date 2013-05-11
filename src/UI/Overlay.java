package UI;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import Utils.Utils;
import Genes.Gene;
import Serotope.Creature;
import Serotope.GameWorld;

public class Overlay {
	Creature target;
	private static final int border=10;
	int backgroundsize;
	int traitBorderWidth;
	int traitBorderHeight;
	public Overlay() {
		traitBorderWidth=Utils.TRAITBORDER.getWidth();
		traitBorderHeight=Utils.TRAITBORDER.getHeight();
		backgroundsize=Utils.HEALTHBACKGROUND.getWidth();
		target = GameWorld.getGameWorld().getPlayer();
	}
	public void render(Graphics g) {
		target=GameWorld.getGameWorld().getPlayer();
		drawBase(g);
		drawDynamics(g);
		drawTraits(g);
	}
	private void drawTraits(Graphics g) {
		ArrayList<Gene> a =target.getDna().getGenes();
		int xRender = 4*border+3*backgroundsize;
		int yRender = border;
		if(a.isEmpty()) {
			return;
		}
		ArrayList<Gene> expressedGenes = new ArrayList<Gene>();
		for(int i=0;i<a.size();i++) {
			if(a.get(i).isExpressed()) {
				expressedGenes.add(a.get(i));
			}
		}
		g.setColor(Color.red);
		yRender+=backgroundsize/2;
		for(int i=0;i<expressedGenes.size();i++) {
			g.drawString("" + i, xRender, yRender);
			xRender+=border;
		}
	
		
	}
	private void drawDynamics(Graphics g) {
		int xRender = 0;
		int yRender = border;
		
		//draw Health stuff
		g.setColor(Color.white);
		drawBoxes((float)((float)target.getCurrHealth()/target.getHealth()),xRender,yRender,g);
		
		g.drawString("" + target.getCurrHealth(), xRender, yRender);
		
		xRender+=backgroundsize+border;
		//draw Speed stuff
		drawBoxes((float)(target.getCurrSprint()/target.getSprintTime()),xRender,yRender,g);

		g.drawString("" +(int)target.getTopSpeed(), xRender, yRender);

		xRender+=backgroundsize+border;
		
		//draw Damage stuff
		drawBoxes(((float)target.getTimeSinceLastAttack()/target.getCoolDown()),xRender,yRender,g);
		
		g.drawString(" "+(int)target.getDamage(), xRender, yRender);

		
		xRender+=backgroundsize;
		xRender+=border;
		
		xRender+=traitBorderWidth;
		xRender+=border;
		//draw Score stuff
		g.drawString(""+GameWorld.getGameWorld().getScore(), xRender, yRender);
		
	}
	private void drawBoxes(float i, int xRender, int yRender, Graphics g) {
		int y = yRender+1;
		int x = xRender+backgroundsize/2-1;
		if(i>7f/8f) {
			return;
		}
		Utils.FILLER.draw(x, y);
		x+=backgroundsize/4;
		y+=backgroundsize/8;
		if(i>6f/8f) {
			return;
		}
		Utils.FILLER.draw(x,y);
		x+=backgroundsize/8;
		y+=backgroundsize/4;
		if(i>5f/8f) {
			return;
		}
		Utils.FILLER.draw(x,y);
		x-=backgroundsize/8;
		y+=backgroundsize/4;
		if(i>4f/8f) {
			return;
		}
		Utils.FILLER.draw(x,y);
		x-=backgroundsize/4;
		y+=backgroundsize/8;
		if(i>3f/8f) {
			return;
		}
		Utils.FILLER.draw(x,y);
		x-=backgroundsize/4;
		y-=backgroundsize/8;
		if(i>2f/8f) {
			return;
		}
		Utils.FILLER.draw(x,y);
		x-=backgroundsize/8;
		y-=backgroundsize/4;
		Utils.FILLER.draw(x,y);
		if(i>1f/8f) {
			return;
		}
		x+=backgroundsize/8;
		y-=backgroundsize/4;
		Utils.FILLER.draw(x,y);
	}
	private void drawBase(Graphics g) {
		int xRender=border;
		int yRender=border;
		Utils.HEALTHBACKGROUND.draw(xRender,yRender);
		xRender+=backgroundsize;
		xRender+=border;
		Utils.SPEEDBACKGROUND.draw(xRender,yRender);
		xRender+=backgroundsize;
		xRender+=border;
		Utils.DAMAGEBACKGROUND.draw(xRender,yRender);
		xRender+=backgroundsize+border;
		yRender = border;
		Utils.TRAITBORDER.draw(xRender, yRender);
	}
	
}
