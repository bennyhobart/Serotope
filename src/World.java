import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class World {

	public World(String string) {

	}

	public void render(Graphics g) {
		
	}

	public void update(int delta, GameContainer gc) {
		//input
		if (gc.getInput().isKeyDown(InputManager.KeyForward)) {
			InputManager.isKeyForward=true;
		}
		else {
			InputManager.isKeyForward=false;
		}
	
		if(gc.getInput().isKeyDown(InputManager.KeyBack)) {
			InputManager.isKeyBack=true;
		}
		else {
			InputManager.isKeyBack=false;
		}
		
		if (gc.getInput().isKeyDown(InputManager.KeyUp)) {
			InputManager.isKeyUp=true;
		}
		else {
			InputManager.isKeyUp=false;
		}
		
		if(gc.getInput().isKeyDown(InputManager.KeyDown)) {
			InputManager.isKeyDown=true;
		}
		else {
			InputManager.isKeyDown=false;
		}
		
	}

}
