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
			if(prevMove==null) {
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
			Creature closest=null;
			float distanceClosest;
			distanceClosest = -1;
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
			if(closest==null) {
				//no creatures found
				currentState=STATEBORED;
			}
			else {
				//creatures found
				if(distanceClosest<3) {
					otherCreature = closest;
					currentState = STATECHASING;
				}
				else {
					currentState=STATEBORED;
				}
			}
			
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
			fightingShoot();
			break;
		case STATESCARED:
			scaredShoot();
			break;
		case STATEBORED:
			boredShoot();
			break;
		case STATEDISTRACTED:
			distractedShoot();
			break;
		case STATECHASING:
			chasingShoot();
			break;
		default:
			defaultShoot();
			break;
		}

	}
	void move(int delta) {
		switch(currentState) { 
		case STATEFIGHTING:
			fightingMove();
			break;
		case STATESCARED:
			scaredMove();
			return;
		case STATEBORED:
			boredMove();
			break;
		case STATEDISTRACTED:
			distractedMove();
			break;
		case STATECHASING:
			chasingMove();
			break;
		default:
			defaultMove();
			break;
		}
		
	}
	void goToState(int state, int time) {
		currentState=state;
		stateTime=time;
		currentStateTime=0;
	}
	void defaultMove() {
		return;
	}
	void defaultShoot() {
		return;
	}
	void fightingMove() {
		
	}
	void fightingShoot() {
		
	}
	void scaredMove() {
		prevMove=Utils.vectorBetween(otherCreature.body.getPosition(), target.body.getPosition());
		target.move(prevMove);
	}
	void scaredShoot() {
		double randomAngle = GameWorld.randomGenerator.nextDouble();
		randomAngle%=Math.PI/8;
		randomAngle-=Math.PI/16;
		prevShoot = Utils.rotateVector(prevMove,randomAngle);
		target.shoot(prevShoot);
		return;
	}
	void boredMove() {
		//double randomAngle = GameWorld.randomGenerator.nextDouble();
		//randomAngle=randomAngle%Math.PI/8;
		//randomAngle-=Math.PI/16;
		//prevMove = Utils.rotateVector(prevMove,Math.PI/16);
		//target.move(prevMove);
	}
	void boredShoot() {
		return;
	}
	void distractedMove() {
		return;
	}
	void distractedShoot() {
		double randomAngle = GameWorld.randomGenerator.nextDouble();
		randomAngle%=Math.PI/8;
		randomAngle-=Math.PI/16;
		prevShoot = Utils.rotateVector(prevShoot, randomAngle);
		target.shoot(prevShoot);
		return;
	}
	void chasingMove() {
		prevMove=Utils.vectorBetween(target.body.getPosition(), otherCreature.body.getPosition());
		target.move(prevMove);
	}
	void chasingShoot() {
		prevShoot=Utils.vectorBetween(target.body.getPosition(), otherCreature.body.getPosition());
		target.shoot(prevShoot);
		return;
	}
}

