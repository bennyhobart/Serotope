import java.util.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Creature extends GameObject {
	//Draw Variables
	private int size;
	
	
	//Move Variables
	double topSpeed = 0.5;
	double acceleration=0.05;
	double currSpeed= 0;
	double currMove = 0;
	
	//Health Variables
	int maxHealth = 100;
	int currHealth = 100;
	int defence = 5;
	
	
	//Damage Variables
	double attackSpeed=0.2;
	int timeSinceLastAttack=0;
	int damage=5;
	
	Vector<Component> components;
	
	public Creature(int x, int y) {
		this.x=x;
		this.y=y;
		components = new Vector<Component>();
		components.add(new PlayerController(this));
	}

	@Override
	public void update(int delta, GameContainer gc) {

		for(int i=0;i<components.size();i++) {
			components.get(i).update(delta);
		}
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.drawOval((float)x,(float)y, (float)10, (float)10);
	}
	
	public void hit(int damage) {
		currHealth-=(damage-defence);
	}

}
