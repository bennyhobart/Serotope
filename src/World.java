import java.util.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class World {
	private Vector<GameObject> gameObjects;
	public World(String string) {
		gameObjects = new Vector<GameObject>();
		
	}

	public void render(Graphics g) {
		
	}

	public void update(int delta, GameContainer gc) {
		InputManager.update(gc);
		for(int i=0;i<gameObjects.size();i++) {
			gameObjects.get(i).update(delta, gc);
		}
		
	}

}
