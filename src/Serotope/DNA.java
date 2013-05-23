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

	/**
	 * Create a new DNA object
	 * 
	 * @throws SlickException
	 */
	public DNA() throws SlickException {
		super(new Vec2(0, 0), Utils.dnaImage, true);
		// Remove the DNA object from the game world, as it is to be initially
		// associated with a creature
		GameWorld.getGameWorld().getGameObjects().remove(this);
		initialiseBodyDefinition();

		// Add each of the required genes
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

	/**
	 * Apply any modifiers to the given creature's stats based on the genes in
	 * its DNA
	 * 
	 * @param creature
	 *            The creature to be modified
	 */
	public void buffCreature(Creature creature) {
		for (Gene gene : genes) {
			gene.buffCreature(creature);
		}
	}

	@Override
	public void update(int delta, GameContainer gc) {
		// Only update if DNA object is in the game world, and not in a creature
		if (!dropped) {
			return;
		}
		// Create a body fixture if it doesn't have one, so it can partake
		// in physics in the game world (collisions, etc)
		if (this.getBody().getFixtureList() == null) {
			initialiseFixture();
		}

		// Check if a creature makes contact with this object
		ContactEdge contact = this.getBody().getContactList();
		ArrayList<Creature> creatures = Utils.getCreatures(contact);
		// Said creature picks up the dna
		if (!creatures.isEmpty()) {
			creatures.get(0).pickUpDna(this);
			this.dropped = false;
			this.destroyFixture();
		}
		return;
	}

	@Override
	// Render the DNA only if it is in the game world, and not in a creature
	void render(Graphics g, float xrender, float yrender) {
		if (dropped) {
			super.render(g, xrender, yrender);
		} else
			return;
	}

	private void destroyFixture() {
		this.getBody().destroyFixture(getBody().getFixtureList());
	}

	// Initialise BodyDef object with the required physical attributes
	private void initialiseBodyDefinition() {
		BodyDef bd = new BodyDef();
		bd.fixedRotation = true;
		bd.type = BodyType.DYNAMIC;
		bd.userData = this;
		bd.linearDamping = 0.5f;
		bd.position.set(new Vec2(0, 0));
		setBody(GameWorld.getGameWorld().getPhysicsWorld().createBody(bd));
	}

	// Initialise FictureDef object with the required physical attributes
	private void initialiseFixture() {

		CircleShape circle = new CircleShape();
		circle.setRadius((image.getWidth() / 2) / Utils.SCALE);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;

		this.getBody().createFixture(fixtureDef);
		getBody().setFixedRotation(true);

	}

	public ArrayList<Gene> getGenes() {
		return genes;
	}

	public void setDropped(boolean dropped) {
		this.dropped = dropped;

	}

	public int getNumGenesExpressed() {
		int count = 0;
		for (Gene gene : genes) {
			if (gene.isExpressed())
				count++;
		}
		return count;
	}

}
