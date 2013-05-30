package Serotope;

import java.util.ArrayList;
import java.util.Random;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Menu.InputManager;
import Menu.gPanel;
import UI.Overlay;
import Utils.Utils;

public class GameWorld {

	private Image background;

	private static World physicsWorld;
	static GameWorld gameWorld;
	private static ArrayList<GameObject> gameObjects;
	private static Camera focus;
	static int idNum = 0;
	public static GameStats gameStats;
	private static Random randomGenerator;
	private static int playerId;
	private Overlay ui;
	private static int score = 0;

	public static final float MAX_DISTANCE = 10000;

	public static GameWorld getGameWorld() {
		if (gameWorld == null) {
			gameWorld = new GameWorld("test world");
		}
		return gameWorld;
	}

	public GameWorld(String string) {
		gameStats = new GameStats();
		try {
			Utils.initImages();
		} catch (SlickException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setRandomGenerator(new Random(System.nanoTime()));
		gameWorld = this;
		try {
			background = new Image(Utils.BACKGROUND);
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		physicsWorld = new World(new Vec2(0, 0));
		setGameObjects(new ArrayList<GameObject>());
		try {
			Creature creature = new Creature(new Vec2(0, 0), true, new DNA(true));
			focus = new Camera(getPlayer());
			playerId = creature.getId();
			new CreatureSpawner(new Vec2(0, 0));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		ui = new Overlay();
	}

	public void render(Graphics g) {
		Vec2 objectScreenLoc;
		GameObject target;
		background.draw(-64 - focus.target.getBody().getPosition().x
				* Utils.SCALE % 64, -64
				+ focus.target.getBody().getPosition().y * Utils.SCALE % 64,
				Color.gray);
		for (int i = 0; i < gameObjects.size(); i++) {
			target = gameObjects.get(i);
			objectScreenLoc = worldToScreen(target.getBody().getPosition());
			float xRender = objectScreenLoc.x;
			float yRender = objectScreenLoc.y;
			if (isOnscreen(xRender,yRender,target));
				getGameObjects().get(i).render(g, xRender, yRender);
		}
		ui.render(g);
	}


	public boolean isOnscreen(float xRender, float yRender, GameObject target) {
		if(target.image == null || xRender < -target.image.getWidth() / 2
					|| xRender > gPanel.PWIDTH + target.image.getWidth() / 2
					|| yRender < -target.image.getHeight() / 2
					|| yRender > gPanel.PHEIGHT + target.image.getHeight() / 2){ 
			return false;
		}
		return true;
	}

	public void update(int delta, GameContainer gc) {
		InputManager.update(gc);
		setScore(getScore() + delta);
		// Stats collection and garbage collection
		for (int i = 0; i < getGameObjects().size(); i++) {
			float distanceFromPlayer = gameObjects.get(i).getBody()
					.getPosition().sub(focus.target.getBody().getPosition())
					.lengthSquared();
			if (distanceFromPlayer > MAX_DISTANCE) {
				gameObjects.get(i).doomed = true;
			}
			if (gameObjects.get(i).doomed) {
				physicsWorld.destroyBody(gameObjects.get(i).getBody());
				gameObjects.remove(i);
			}
		}
		// Game Update
		for (int i = 0; i < getGameObjects().size(); i++) {
			getGameObjects().get(i).update(delta, gc);
		}
		// Physics Update
		physicsWorld.step((float) delta / 1000, 6, 3);

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
		Vec2 renderLoc = new Vec2(position).mul(Utils.SCALE);
		renderLoc.subLocal(focus.target.getBody().getPosition().mul(Utils.SCALE));
		renderLoc.y=-renderLoc.y;
		renderLoc.addLocal(new Vec2((gPanel.PWIDTH/2),(gPanel.PHEIGHT/2)));
		
		return renderLoc;
	}

	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}

	public void setGameObjects(ArrayList<GameObject> gameObjects) {
		GameWorld.gameObjects = gameObjects;
	}

	public static Random getRandomGenerator() {
		return randomGenerator;
	}

	public static void setRandomGenerator(Random randomGenerator) {
		GameWorld.randomGenerator = randomGenerator;
	}

	public GameObject getGameObject(int id) {
		GameObject target = null;
		for (int i = 0; i < gameObjects.size(); i++) {
			if (gameObjects.get(i).id == id) {
				target = gameObjects.get(i);
				break;
			}
		}
		return target;
	}

	Creature player;

	public Creature getPlayer() {
		// player is yet to be initialized
		if (player == null) {
			setPlayer(playerId);
			return player;
		} else {
			return player;
		}
	}

	public boolean setPlayer(int id) {
		playerId = id;
		gameStats.incrementGenerations();
		for (int i = 0; i < gameObjects.size(); i++) {
			if (gameObjects.get(i).id == playerId) {
				player = (Creature) gameObjects.get(i);
				return true;
			}
		}
		return false;
	}

	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public static void setScore(int score) {
		GameWorld.score = score;
	}

}
