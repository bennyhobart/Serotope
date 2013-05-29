package UI;

import java.util.ArrayList;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


import Utils.Utils;
import Genes.Gene;
import Menu.gPanel;
import Serotope.Creature;
import Serotope.GameWorld;

public class Overlay {
	Creature target;
	private static final int border = 28;
	int statSize;
	int traitBorderWidth;
	int traitBorderHeight;

	public Overlay() {
		traitBorderWidth = Utils.TRAITBORDER.getWidth();
		traitBorderHeight = Utils.TRAITBORDER.getHeight();
		statSize = Utils.HEALTHBACKGROUND.getWidth();
		target = GameWorld.getGameWorld().getPlayer();
	}

	public void render(Graphics g) {
		target = GameWorld.getGameWorld().getPlayer();
		drawBase(g);
		drawDynamics(g);
		drawTraits(g);
	}

	private void drawTraits(Graphics g) {
		ArrayList<Gene> expressedGenes = target.getExpressedGenes();
		int xRender = 6 * border + 3 * statSize;
		int yRender = 2 * border + 5;
		if (expressedGenes.isEmpty()) {
			return;
		}
		for (Gene gene : expressedGenes) {
			gene.renderIcon(g, xRender, yRender);
			if (gene.getIcon() != null) {
				xRender += gene.getIcon().getWidth() + border - 5;
			}
		}
	}

	private void drawDynamics(Graphics g) {
		int xRender = border;
		int yRender = border;

		// draw Health stuff
		g.setColor(Color.white);
		drawBoxes(
				(float) ((float) target.getCurrHealth() / target.getHealth()),
				xRender, yRender, g);

		g.drawString(String.format("%.0f", target.getCurrHealth()), xRender, yRender);
		if (target.isShield()) {
			g.drawString(String.format("%.0f", target.getCurrShield()), xRender
					+ border + 10, yRender);
		}

		xRender += (statSize + border);
		// draw Speed stuff
		drawBoxes((float) (target.getCurrSprint() / target.getSprintTime()),
				xRender, yRender, g);

		g.drawString("" + (int) target.getTopSpeed(), xRender, yRender);

		xRender += (statSize + border);

		// draw Damage stuff
		drawBoxes(
				((float) target.getTimeSinceLastAttack() / target.getCoolDown()),
				xRender, yRender, g);
		g.drawString("" + (int) target.getDamage(), xRender, yRender);

		xRender += (statSize + border);
		xRender += traitBorderWidth + border;

		//draw local creatures
		AABB zone = new AABB(new Vec2(GameWorld.getGameWorld().getFocus().getBody().getPosition().sub(new Vec2(10,10))), new Vec2(GameWorld.getGameWorld().getFocus().getBody().getPosition().add(new Vec2(10,10))));
		ArrayList<Creature> localCreatures = Utils.getCreatures(Utils.getGameObjectsAABB(zone));
		for(int i=0;i<localCreatures.size();i++) {
			if(localCreatures.get(i).id==GameWorld.getGameWorld().getPlayerId()) {
				continue;
			}
			Vec2 screenLoc = GameWorld.getGameWorld().worldToScreen(localCreatures.get(i).getBody().getPosition());
			if(GameWorld.getGameWorld().isOnscreen(screenLoc.x,screenLoc.y,localCreatures.get(i))) {
				continue;
			}
			Vec2 between = Utils.vectorBetween(GameWorld.getGameWorld().getFocus().getBody().getPosition(), localCreatures.get(i).getBody().getPosition());
			between.normalize();
			between.set(between.x,between.y*-1);
			between.mulLocal(Utils.CREATUREIMAGE1.getWidth());
			between.addLocal(new Vec2((gPanel.PWIDTH/2),(gPanel.PHEIGHT/2)));
			Utils.MARKERIMAGE.drawCentered(between.x, between.y);
		}
		// draw Score stuff
		g.drawString("" + GameWorld.getGameWorld().getScore(), xRender, yRender);

	}
	

	private void drawBoxes(float i, int xRender, int yRender, Graphics g) {
		int y = yRender;
		int x = xRender + statSize / 2 - Utils.FILLER.getWidth() / 2;
		if (i > 7f / 8f) {
			return;
		}
		Utils.FILLER.draw(x, y);
		x += statSize / 4;
		y += statSize / 8;
		if (i > 6f / 8f) {
			return;
		}
		Utils.FILLER.draw(x, y);
		x += statSize / 8;
		y += statSize / 4;
		if (i > 5f / 8f) {
			return;
		}
		Utils.FILLER.draw(x, y);
		x -= statSize / 8;
		y += statSize / 4;
		if (i > 4f / 8f) {
			return;
		}
		Utils.FILLER.draw(x, y);
		x -= statSize / 4;
		y += statSize / 8;
		if (i > 3f / 8f) {
			return;
		}
		Utils.FILLER.draw(x, y);
		x -= statSize / 4;
		y -= statSize / 8;
		if (i > 2f / 8f) {
			return;
		}
		Utils.FILLER.draw(x, y);
		x -= statSize / 8;
		y -= statSize / 4;
		Utils.FILLER.draw(x, y);
		if (i > 1f / 8f) {
			return;
		}
		x += statSize / 8;
		y -= statSize / 4;
		Utils.FILLER.draw(x, y);
	}

	private void drawBase(Graphics g) {
		int xRender = border;
		int yRender = border;
		Utils.HEALTHBACKGROUND.draw(xRender, yRender);
		xRender += statSize;
		xRender += border;
		Utils.SPEEDBACKGROUND.draw(xRender, yRender);
		xRender += statSize;
		xRender += border;
		Utils.DAMAGEBACKGROUND.draw(xRender, yRender);
		xRender += statSize + border;
		yRender = border;
		Utils.TRAITBORDER.draw(xRender, yRender);
	}

}
