import java.util.ArrayList;
import java.util.Stack;

import org.jbox2d.callbacks.QueryCallback;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;


public class AIController extends Controller implements QueryCallback {
	
	private static final int STATEDEFAULT = -1;
	private static final int STATEFIGHTING = 0;
	private static final int STATESCARED = 1;
	private static final int STATEBORED = 2;
	private static final int STATEDISTRACTED = 3;
	private static final int STATECHASING = 4;
	
	private static final int AISENSOR = 5;
	
	Creature otherCreature;
	
	
	//State Stack Details
	Stack<int[]> stateList;
	
	
	//Current State Details
	private int currentState;
	private int stateTime;
	private int currentStateTime;
	
	
	//AI timers
	private static final int REFRESHTIME = 2000;
	private int AIRefresh;
	private Vec2 prevMove;
	private Vec2 prevShoot;
	
	ArrayList<GameObject> local;
	public boolean reportFixture(Fixture fixture) {
		local.add((GameObject) fixture.getBody().getUserData());
		return true;
	}
	public AIController(Creature creature) {
		super(creature);
		local = new ArrayList<GameObject>();
	}
	public void update(int delta) {
		if(AIRefresh<REFRESHTIME) {
			prevMove=new Vec2(1,1);
			prevShoot=new Vec2(1,1);
			currentState=STATEBORED;
			/*if(prevMove==null) {
				prevMove=new Vec2(1,0);
			}
			if(prevShoot==null) {
				prevShoot = new Vec2(1,0);
			}
			ArrayList<Creature> creatures;
			Vec2 topLeft = new Vec2(target.body.getPosition().x+AISENSOR,target.body.getPosition().y+AISENSOR);
			Vec2 bottomRight = new Vec2(target.body.getPosition().x-AISENSOR,target.body.getPosition().y-AISENSOR);
			AABB zone = new AABB(bottomRight,topLeft);
			
			
			
			
			
			//find closest creature
			creatures = Utils.getCreatures(Utils.getGameObjectsAABB(zone));
			Creature closest=creatures.get(0);
			float distanceClosest = Utils.vectorBetween(target.body.getPosition(), closest.body.getPosition()).length();
			Creature compare;
			for(int i=0;i<creatures.size();i++) {
				if(creatures.get(i).id==target.id) {
					continue;
				}
				if(closest==null) {
					closest = creatures.get(i);
					distanceClosest = Utils.lengthBetween(target.body.getPosition(), closest.body.getPosition());
					continue;
				}
				compare = creatures.get(i);
				if(Utils.lengthBetween(target.body.getPosition(), compare.body.getPosition())<distanceClosest) {
					closest=compare;
					Utils.lengthBetween(target.body.getPosition(), closest.body.getPosition());
				}
			}
			//creatures found
			if(distanceClosest<3) {
				otherCreature = closest;
				currentState = STATECHASING;
			}
			else {
				currentState=STATEBORED;
			}*/
			
		}
		else {
			AIRefresh=0;
		}
		move(delta);
		shoot(delta);
		AIRefresh+=delta;
	}
	void shoot(int delta) {
		switch(currentState) { 
		case STATEFIGHTING:
			fightingShoot(delta);
			break;
		case STATESCARED:
			scaredShoot(delta);
			break;
		case STATEBORED:
			boredShoot(delta);
			break;
		case STATEDISTRACTED:
			distractedShoot(delta);
			break;
		case STATECHASING:
			chasingShoot(delta);
			break;
		default:
			defaultShoot(delta);
			break;
		}

	}
	void move(int delta) {
		switch(currentState) { 
		case STATEFIGHTING:
			fightingMove(delta);
			break;
		case STATESCARED:
			scaredMove(delta);
			return;
		case STATEBORED:
			boredMove(delta);
			break;
		case STATEDISTRACTED:
			distractedMove(delta);
			break;
		case STATECHASING:
			chasingMove(delta);
			break;
		default:
			defaultMove(delta);
			break;
		}
		
	}
	void goToState(int state, int time) {
		currentState=state;
		stateTime=time;
		currentStateTime=0;
	}
	void defaultMove(int delta) {
		return;
	}
	void defaultShoot(int delta) {
		return;
	}
	void fightingMove(int delta) {
		
	}
	void fightingShoot(int delta) {
		
	}
	void scaredMove(int delta) {
		prevMove=Utils.vectorBetween(otherCreature.body.getPosition(), target.body.getPosition());
		target.move(prevMove);
	}
	void scaredShoot(int delta) {
		double randomAngle = GameWorld.randomGenerator.nextDouble();
		randomAngle%=Math.PI/8;
		randomAngle-=Math.PI/16;
		prevShoot = Utils.rotateVector(prevMove,randomAngle);
		target.shoot(prevShoot);
		return;
	}
	void boredMove(int delta) {
		int randomAngle = GameWorld.randomGenerator.nextInt(16);
		switch(randomAngle) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			target.move(prevMove);
			break;
		case 8:
		case 9:
		case 10:
		case 11:
			prevMove = Utils.rotateVector(prevMove, Math.PI/delta);
			target.move(prevMove);
			break;
		case 12:
		case 13:
		case 14:
		case 15:
			prevMove = Utils.rotateVector(prevMove, -Math.PI/delta);
			target.move(prevMove);
			break;
		}
	}
	void boredShoot(int delta) {
		return;
	}
	void distractedMove(int delta) {
		return;
	}
	void distractedShoot(int delta) {
		double randomAngle = GameWorld.randomGenerator.nextDouble();
		randomAngle%=Math.PI/8;
		randomAngle-=Math.PI/16;
		prevShoot = Utils.rotateVector(prevShoot, randomAngle);
		target.shoot(prevShoot);
		return;
	}
	void chasingMove(int delta) {
		prevMove=Utils.vectorBetween(target.body.getPosition(), otherCreature.body.getPosition());
		target.move(prevMove);
	}
	void chasingShoot(int delta) {
		prevShoot=Utils.vectorBetween(target.body.getPosition(), otherCreature.body.getPosition());
		target.shoot(prevShoot);
		return;
	}
}

