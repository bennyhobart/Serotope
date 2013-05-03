import org.jbox2d.common.Vec2;






public class PlayerController extends Controller {
	public PlayerController(Creature creature) {
		super(creature);
	}
	@Override
	void shoot(int delta) {
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
			move.mul(target.topSpeed);
			target.body.applyLinearImpulse(move, target.body.getPosition());
			break;
		}	
	}
	@Override
	void move(int delta) {
		switch(InputManager.CONTROLDEVICE) {
		case InputManager.ANDROID:
			
			break;
		case InputManager.GAMEPAD:
		
			break;
		case InputManager.KEYBOARD:
			
			break;
		}	
	}
}
