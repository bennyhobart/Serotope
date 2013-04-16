import java.util.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class World {
	static World world;
	public static World getWorld() {
		if(world==null) {
			world=new World("test world");
		}
		return world;
	}
	private Vector<GameObject> gameObjects;
	public World(String string) {
		gameObjects = new Vector<GameObject>();
		gameObjects.add(new Creature(100,100));
	}

	public void render(Graphics g) {
		for(int i=0;i<gameObjects.size();i++) {
			gameObjects.get(i).render(g);
		}
	}

	public void update(int delta, GameContainer gc) {
		InputManager.update(gc);
		for(int i=0;i<gameObjects.size();i++) {
			gameObjects.get(i).update(delta, gc);
		}
		
	}

}
