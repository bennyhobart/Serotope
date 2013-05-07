package GAME;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Utils.Utils;



public class CreatureSpawner extends GameObject {

	int spawnTimer;
	int spawnTime;
	CreatureSpawner(Vec2 position) throws SlickException {
		super(position, new Image(Utils.CREATUREIMAGE7), false);
		spawnTime=3000;
		spawnTimer=0;
	}

	@Override
	public void update(int delta, GameContainer gc) {
		spawnTimer+=delta;
		if(spawnTime>spawnTimer) {
			return;
		}
		spawnTimer=0;
		try {
			new Creature(getBody().getPosition(), false);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
