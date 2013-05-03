import org.jbox2d.common.Vec2;


public class Utils {
	public static final float scale = 100;
	public static final String creatureImage = "assets/image/Creature1.png";
	public static final String bulletImage = "assets/image/Bullet.png";
	public static float distBetween(Vec2 a, Vec2 b) {
		
		return (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
	}
}
