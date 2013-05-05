import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;


public class Utils {
	public static final float SCALE = 100;
	public static final String CREATUREIMAGE1 = "assets/image/Creature1.png";
	public static final String CREATUREIMAGE2 = "assets/image/Creature2.png";
	public static final String CREATUREIMAGE3 = "assets/image/Creature3.png";
	public static final String CREATUREIMAGE4 = "assets/image/Creature4.png";
	public static final String CREATUREIMAGE5 = "assets/image/Creature5.png";
	public static final String CREATUREIMAGE6 = "assets/image/Creature6.png";
	public static final String CREATUREIMAGE7 = "assets/image/Creature7.png";
	public static final String[] CREATUREIMAGES = {CREATUREIMAGE1,CREATUREIMAGE2,CREATUREIMAGE3,CREATUREIMAGE4,CREATUREIMAGE5,CREATUREIMAGE6,CREATUREIMAGE7};
	public static final String bulletImage = "assets/image/Bullet.png";
	public static final int bullet1Width = 16;
	//creature default stats
	//Move Variables
	public static final float topSpeed=5f;
	public static final float acceleration=1f;
	public static final float handling=3f;
	//Health Variables
	public static final int health=100;
	public static final int stamina=1;
	public static final boolean shield=false;
	//Damage Variables
	public static final int damage=10;
	public static final int attackSpeed=200;
	public static final int attackType=1;
	
	//Body Utils
	//clockwise to the vector between a and b
	public static Vec2 tangentialVector(Vec2 a,Vec2 b) {
		Vec2 dir=vectorBetween(a,b);
		dir.set(dir.y, -dir.x);
		dir.normalize();
		return dir;
	}
	//returns a unit vector directed from a to be
	public static Vec2 vectorBetween(Vec2 a, Vec2 b) {
		Vec2 dir = new Vec2(b.sub(a));
		dir.normalize();
		return dir;
	}
	public static float lengthBetween(Vec2 a, Vec2 b) {
		return a.sub(b).length();
		
	}

}