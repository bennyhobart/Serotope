import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.ContactEdge;

public class AIController extends Controller {
	boolean scared;
	int scaredTime;
	Creature scaredOfCreature;
	int currentScaredTime;
	boolean fighting;
	int fightingTime;
	Creature fightingCreature;
	int currentFightingTime;
	
	float chaseRadius=2;
	
	
	
	public AIController(Creature creature) {
		super(creature);
	}

	@Override
	void shoot(int delta) {
		if(fightingCheck(delta)) {
			return;
		}
		ContactEdge contacts = target.body.getContactList();
		Vec2 shoot = new Vec2(0,0);
		while(contacts!=null) {
			if(contacts.other.getUserData() instanceof Creature) {
				Creature creature = (Creature) contacts.other.getUserData();
				shoot.addLocal(Utils.vectorBetween(target.body.getPosition(), creature.body.getPosition()));
				fightingTime=3000;
				fighting=true;
				currentFightingTime=0;
				fightingCreature=creature;

			}
			contacts=contacts.next;
		}
		target.shoot(shoot);
	}

	private boolean fightingCheck(int delta) {
		if(fighting) {
			if(fightingTime<currentFightingTime) {
				fighting=false;
				fightingTime=0;
				currentFightingTime=0;
			}
			else {
				target.shoot(Utils.vectorBetween(target.body.getPosition(), fightingCreature.body.getPosition()));
				currentFightingTime+=delta;
				return true;
			}
		}
		return false;
	}

	

	@Override
	void move(int delta) {
		/*if(scaredCheck(delta)) {
			return;
		}*/
		if(fightingMoveCheck(delta)) {
			return;
		}
		ContactEdge contacts = target.body.getContactList();
		Vec2 move = new Vec2(0,0);
		while(contacts!=null) {
			if(contacts.other.getUserData() instanceof Creature) {
				Creature creature = (Creature) contacts.other.getUserData();
				move.addLocal(Utils.vectorBetween(creature.body.getPosition(), target.body.getPosition()));
				scaredTime=3000;
				scared=true;
				currentScaredTime=0;
				scaredOfCreature=creature;

			}
			contacts=contacts.next;
		}
		target.move(move);
	}
	private boolean fightingMoveCheck(int delta) {
		if(fighting) {
			Vec2 move;
			if(Utils.lengthBetween(target.body.getPosition(), fightingCreature.body.getPosition())<chaseRadius) {
				move = Utils.tangentialVector(target.body.getPosition(),fightingCreature.body.getPosition());
			}
			else {
				target.move(Utils.vectorBetween(target.body.getPosition(),fightingCreature.body.getPosition()));
			}
			return true;
		}
		return false;
	}
	private boolean scaredCheck(int delta) {
		if(scared) {
			if(scaredTime<currentScaredTime) {
				scared=false;
				scaredTime=0;
				currentScaredTime=0;
			}
			else {
				target.move(Utils.vectorBetween(scaredOfCreature.body.getPosition(),target.body.getPosition()));
				currentScaredTime+=delta;
				return true;
			}
		}
		return false;
	}
	

}
