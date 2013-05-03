import org.jbox2d.common.Vec2;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;






public class PlayerController extends Controller {
	public PlayerController(Creature creature) {
		super(creature);
	}
	@Override
	void move(int delta) {
		switch(InputManager.CONTROLDEVICE) {
		case InputManager.ANDROID:
			
			break;
		case InputManager.GAMEPAD:
		
			break;
		case InputManager.KEYBOARD:
			int verticleFlag=0;
			int horizontalFlag=0;
			if(InputManager.isMoveDown) {
				verticleFlag-=1;
			}
			if(InputManager.isMoveUp) {
				verticleFlag+=1;
			}
			if(InputManager.isMoveLeft) {
				horizontalFlag-=1;
			}
			if(InputManager.isMoveRight) {
				horizontalFlag+=1;
			}
			Vec2 move = new Vec2(horizontalFlag,verticleFlag);
			move.normalize();
			move.mulLocal((target.topSpeed-target.body.getLinearVelocity().clone().normalize()) * target.acceleration);
			move.mulLocal(target.body.getMass());
			target.body.applyLinearImpulse(move,target.body.getPosition());
			break;
		}	
	}
	@Override
	void shoot(int delta) {
		if(target.timeSinceLastAttack<target.attackSpeed) {
			return;
		}
		switch(InputManager.CONTROLDEVICE) {
		case InputManager.ANDROID:
			
			break;
		case InputManager.GAMEPAD:
		
			break;
		case InputManager.KEYBOARD:
			int verticleFlag=0;
			int horizontalFlag=0;
			if(InputManager.isShootDown) {
				verticleFlag-=1;
			}
			if(InputManager.isShootUp) {
				verticleFlag+=1;
			}
			if(InputManager.isShootLeft) {
				horizontalFlag-=1;
			}
			if(InputManager.isShootRight) {
				horizontalFlag+=1;
			}
			if(horizontalFlag==0&&verticleFlag==0) {
				return;
			}
			target.timeSinceLastAttack=0;
			Vec2 velocity = new Vec2(horizontalFlag,verticleFlag);
			Vec2 spawnLoc = new Vec2(target.body.getPosition());
			velocity.normalize();
			Vec2 tempAdd = new Vec2(velocity);
			try {
				tempAdd.mulLocal(new Image(Utils.bulletImage).getWidth()+25);
			} catch (SlickException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tempAdd.mulLocal(1/Utils.scale);			
			spawnLoc.addLocal(tempAdd);
			
			try {
				new Bullet(spawnLoc, velocity, target.damage,target.id);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			break;
		}	
	}
}
