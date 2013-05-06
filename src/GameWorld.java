


import java.util.ArrayList;
import java.util.Random;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;



public class GameWorld {
	
	private static World physicsWorld;
	static GameWorld gameWorld;
	private static ArrayList<GameObject> gameObjects;
	private static Camera focus;
	static int idnum=0;
	static Random randomGenerator;
	
	public GameObject getGameObject(int id) {
		for(int i=0;i<gameObjects.size();i++) {
			if(gameObjects.get(i).id==id) {
				return gameObjects.get(i);
			}
		}
		return null;
	}
	
	public static GameWorld getGameWorld() {
		if(gameWorld==null) {
			gameWorld=new GameWorld("test world");
		}
		return gameWorld;
	}
	public GameWorld(String string) {
		randomGenerator = new Random(System.nanoTime());
		gameWorld=this;
		physicsWorld = new World(new Vec2(0,0));
		setGameObjects(new ArrayList<GameObject>());
		try {
			//always spawn player creature first
			//testing ai so i ignored the above rule
			new Creature(new Vec2(0,0), true);
			/*for(int i=-5;i<5;i++) {
				for(int j=-5;j<5;j++) {
					if(i==0&&j==0) {
						continue;
					}
					new Creature(new Vec2(i*2,j*2),false);
					
				}
			}*/
			new Creature(new Vec2(0,0),false);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			new Marker(new Vec2(0,0));
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
			if(xRender<-target.image.getWidth()/2
					||xRender>gPanel.PWIDTH+target.image.getWidth()/2
					||yRender<-target.image.getHeight()/2
					||yRender>gPanel.PHEIGHT+target.image.getHeight()/2);
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
		GameWorld.focus = focus;
	}

	public World getPhysicsWorld() {
		return physicsWorld;
	}
	public void setPhysicsWorld(World physicsWorld) {
		GameWorld.physicsWorld = physicsWorld;
	}
	public Vec2 worldToScreen(Vec2 position) {
		return new Vec2(position).mul(Utils.SCALE);
	}
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}
	public void setGameObjects(ArrayList<GameObject> gameObjects) {
		GameWorld.gameObjects = gameObjects;
	}


}
