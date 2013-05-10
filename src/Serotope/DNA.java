package Serotope;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.ContactEdge;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Utils.Utils;

import Genes.AccelerationGene;
import Genes.AttackSpeedGene;
import Genes.AttackTypeGene;
import Genes.DamageGene;
import Genes.Gene;
import Genes.HandlingGene;
import Genes.HealthGene;
import Genes.LifeSpanGene;
import Genes.ShieldGene;
import Genes.SpeedGene;

public class DNA extends GameObject {

	private ArrayList<Gene> genes = new ArrayList<Gene>();
	private boolean dropped = false;

	public DNA() throws SlickException {
		super(new Vec2(0, 0), new Image(Utils.dnaImage), true);

		initialiseBodyDefinition();
		initialiseFixture();
		this.getBody().setType(BodyType.STATIC);

		genes.add(new HealthGene());
		genes.add(new LifeSpanGene());
		genes.add(new ShieldGene());
		genes.add(new SpeedGene());
		genes.add(new AccelerationGene());
		genes.add(new HandlingGene());
		genes.add(new DamageGene());
		genes.add(new AttackSpeedGene());
		genes.add(new AttackTypeGene());
	}

	private void initialiseBodyDefinition() {
		BodyDef bd = new BodyDef();
		bd.fixedRotation = true;
		bd.type = BodyType.KINEMATIC;
		bd.userData = this;
		bd.position.set(new Vec2(0, 0));

		setBody(GameWorld.getGameWorld().getPhysicsWorld().createBody(bd));
	}

	private void initialiseFixture() {

		CircleShape circle = new CircleShape();
		circle.m_radius = (image.getWidth() / 2) / Utils.SCALE;
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;

		this.getBody().createFixture(fixtureDef);

		getBody().setFixedRotation(true);

	}

	public void buffCreature(Creature creature) {
		for (Gene gene : genes) {
			gene.buffCreature(creature);
		}
	}

	public ArrayList<Gene> getGenes() {
		return genes;
	}

	@Override
	public void update(int delta, GameContainer gc) {
		if (!dropped) {
			return;
		}

		ContactEdge contact = this.getBody().getContactList();

		while (contact != null) {
			if (contact.other.getUserData() instanceof Creature) {
				//System.out.println("creature");
				Creature creature = (Creature) contact.other.getUserData();
				creature.pickUpDna();

				this.setDropped(false);
				
				return;
			}
			contact = contact.next;
		}
		return;
	}

	@Override
	void render(Graphics g, float xrender, float yrender) {
		if (dropped) {
			super.render(g, xrender, yrender);
		} else
			return;
	}

	public void setDropped(boolean b) {
		this.dropped = b;

	}

}
