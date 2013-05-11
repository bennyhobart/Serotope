package ParticleEffects;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Serotope.GameObject;
import Utils.Utils;
public class ExplosionEffect extends GameObject {
	int numParticles;
	public ExplosionEffect(Vec2 position,int numParticles, int lifeSpan) {
		super(position, null, false);
		double angleDiff = (Math.PI*2/numParticles);
		Vec2 velocity = new Vec2(1,1);
		
		for(int i=0;i<numParticles;i++) {
			velocity=Utils.rotateVector(velocity, angleDiff);
			try {
				new Speck(position.add(velocity.mul(1/(Utils.PARTICLESPEED*Utils.SCALE))), velocity, lifeSpan);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(int delta, GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
