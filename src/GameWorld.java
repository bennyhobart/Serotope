


import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;



public class GameWorld {
	
	private World physicsWorld;
	static GameWorld gameWorld;
	private ArrayList<GameObject> gameObjects;
	private Camera focus;
	int idnum=0;
	
	public static GameWorld getGameWorld() {
		if(gameWorld==null) {
			gameWorld=new GameWorld("test world");
		}
		return gameWorld;
	}
	public GameWorld(String string) {
		gameWorld=this;
		physicsWorld = new World(new Vec2(0,0));
		setGameObjects(new ArrayList<GameObject>());
		try {
			new Creature(new Vec2(10,0), true);
			new Marker(new Vec2(0,0));
			new Creature(new Vec2(0,0), false);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setFocus(new Camera(getGameObjects().get(0)));
	}

	public void render(Graphics g) {
		Vec2 camScreenLoc = worldToScreen(focus.target.body.getPosition());
		Vec2 objectScreenLoc;
		GameObject target;
		for(int i=0;i<gameObjects.size();i++) {
			target=gameObjects.get(i);
			objectScreenLoc = worldToScreen(getGameObjects().get(i).body.getPosition());
			float xRender = objectScreenLoc.x-camScreenLoc.x+gPanel.PWIDTH/2;
			float yRender= gPanel.PHEIGHT/2-objectScreenLoc.y+camScreenLoc.y;
			if(xRender<-target.image.getWidth()/2||xRender>gPanel.PWIDTH+target.image.getWidth()/2||yRender<-target.image.getHeight()/2||yRender>gPanel.PHEIGHT+target.image.getHeight()/2);
			else
				getGameObjects().get(i).render(g, xRender,yRender);
		}
	}

	public void update(int delta, GameContainer gc) {
		InputManager.update(gc);
		//Game Update
		for(int i=0;i<getGameObjects().size();i++) {
			getGameObjects().get(i).update(delta, gc);
		}
		//Physics Update
		physicsWorld.step((float)delta/1000, 6, 3);
		
	}
	public Camera getFocus() {
		return focus;
	}
	public void setFocus(Camera focus) {
		this.focus = focus;
	}

	public World getPhysicsWorld() {
		return physicsWorld;
	}
	public void setPhysicsWorld(World physicsWorld) {
		this.physicsWorld = physicsWorld;
	}
	public Vec2 worldToScreen(Vec2 position) {
		return new Vec2(position).mul(Utils.scale);
	}
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}
	public void setGameObjects(ArrayList<GameObject> gameObjects) {
		this.gameObjects = gameObjects;
	}


}
