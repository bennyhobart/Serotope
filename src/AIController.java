import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.ContactEdge;




public class AIController extends Controller {
	boolean scared;
	int scaredTime;
	Creature scaredOf;
	int currentScaredTime;
	public AIController(Creature creature) {
		super(creature);
	}

	@Override
	void shoot(int delta) {
		
	}

	@Override
	void move(int delta) {
		if(scaredCheck(delta)) {
			return;
		}
		ContactEdge contacts = target.body.getContactList();
		Vec2 move = new Vec2(0,0);
		while(contacts!=null) {
			if(contacts.other.getUserData() instanceof Creature) {
				Creature creature = (Creature) contacts.other.getUserData();
				move.addLocal(runFrom(creature));
				scaredTime=3000;
				scared=true;
				currentScaredTime=0;
				scaredOf=creature;

			}
			contacts=contacts.next;
		}
		target.move(move);
	}

	private boolean scaredCheck(int delta) {
		if(scared) {
			if(scaredTime<currentScaredTime) {
				scared=false;
				scaredTime=0;
				currentScaredTime=0;
			}
			else {
				target.move(runFrom(scaredOf));
				currentScaredTime+=delta;
				return true;
			}
		}
		return false;
	}

	private Vec2 runFrom(Creature creature) {
		Vec2 moveDir = new Vec2(target.body.getPosition().sub(creature.body.getPosition()));
		moveDir.normalize();
		return moveDir;
	}

}
