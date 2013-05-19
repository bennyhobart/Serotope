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
import org.newdawn.slick.SlickException;

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
import Utils.Utils;

public class DNA extends GameObject {

	private ArrayList<Gene> genes = new ArrayList<Gene>();
	private boolean dropped = false;

	public DNA() throws SlickException {
		super(new Vec2(0, 0), Utils.dnaImage, true);
		GameWorld.getGameWorld().getGameObjects().remove(this);
		initialiseBodyDefinition();

		genes.add(new HealthGene());
		genes.add(new LifeSpanGene());
		genes.add(new ShieldGene());
		genes.add(new SpeedGene());
		genes.add(new AccelerationGene());
		genes.add(new HandlingGene());
		genes.add(new DamageGene());
		genes.add(new AttackSpeedGene());
		genes.add(new AttackTypeGene());
		genes.add(new AttackTypeGene());
		genes.add(new AttackTypeGene());
	}

	private void initialiseBodyDefinition() {
		BodyDef bd = new BodyDef();
		bd.fixedRotation = true;
		bd.type = BodyType.DYNAMIC;
		bd.userData = this;
		bd.linearDamping = 0.5f;
		bd.position.set(new Vec2(0, 0));
		setBody(GameWorld.getGameWorld().getPhysicsWorld().createBody(bd));
	}

	private void initialiseFixture() {

		CircleShape circle = new CircleShape();
		circle.setRadius((image.getWidth() / 2) / Utils.SCALE);
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
		if (this.getBody().getFixtureList() == null) {
			initialiseFixture();
		}

		ContactEdge contact = this.getBody().getContactList();
		ArrayList<Creature> creatures = Utils.getCreatures(contact);
		if (!creatures.isEmpty()) {
			creatures.get(0).pickUpDna(this);
			this.dropped = false;
			this.destroyFixture();
		}
		return;
	}

	private void destroyFixture() {
		this.getBody().destroyFixture(getBody().getFixtureList());
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
