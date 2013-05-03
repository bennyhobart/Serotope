



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;


public class Marker extends GameObject {
	float xVel;
	float yVel;
	int damage;
	
	public Marker(int id,int x,int y, Image picture,float xVel,float yVel,int damage) {
		super(id,x,y,picture,false);
		this.xVel=xVel;
		this.yVel=yVel;
		this.damage=damage;
	}

	@Override
	public void update(int delta, GameContainer gc) {
		
	}


}
