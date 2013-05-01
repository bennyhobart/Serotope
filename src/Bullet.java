import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Bullet extends GameObject {
	
	int xDir;
	int yDir;
	int damage;
	int speed;
	private float size=5;
	
	public Bullet(int xShoot, int yShoot, double x, double y, int damage) {
		this.x=x;
		this.y=y;
		xDir=xShoot;
		yDir=yShoot;
		this.damage=damage;
		size=damage*2;
		speed=1;
		
	}

	@Override
	public void update(int delta, GameContainer gc) {
		x+=xDir*speed*delta;
		y+=yDir*speed*delta;
	}

	@Override
	public void render(Graphics g) {
		double xRenderLoc = -World.getWorld().getCamera().target.x + x + gPanel.PWIDTH/2 - size/2;
		double yRenderLoc = -World.getWorld().getCamera().target.y + y + gPanel.PHEIGHT/2 - size/2;
		g.setColor(Color.red);
		g.fillOval((float)xRenderLoc,(float)yRenderLoc,size, size);
	}

}
