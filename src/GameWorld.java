


import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
		gameObjects = new ArrayList<GameObject>();
		try {
			gameObjects.add(new Creature(idnum++,0,0,new Image("assets/image/placeholder.png"), true));
			gameObjects.add(new Marker(idnum++,0,0,new Image("assets/image/placeholder.png").getScaledCopy( 0.5f),1f,1f,5));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setFocus(new Camera(gameObjects.get(0)));
	}

	public void render(Graphics g) {
		Vec2 camScreenLoc = worldToScreen(focus.target.body.getPosition());
		Vec2 objectScreenLoc;
		for(int i=0;i<gameObjects.size();i++) {
			objectScreenLoc = worldToScreen(gameObjects.get(i).body.getPosition());
			float xRender = objectScreenLoc.x-camScreenLoc.x+gPanel.PWIDTH/2;
			float yRender= gPanel.PHEIGHT/2-objectScreenLoc.y+camScreenLoc.y;
			if(xRender<0
					||xRender>gPanel.PWIDTH
					||yRender<0
					||yRender>gPanel.PHEIGHT);
			else
				gameObjects.get(i).render(g, xRender,yRender);
		}
	}

	public void update(int delta, GameContainer gc) {
		InputManager.update(gc);
		//Game Update
		for(int i=0;i<gameObjects.size();i++) {
			gameObjects.get(i).update(delta, gc);
		}
		//Physics Update
		physicsWorld.step(delta, 6, 3);

		
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


}
