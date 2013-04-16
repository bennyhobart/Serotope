
public class PlayerController extends Controller {

	

	public PlayerController(Creature creature) {
		super(creature);
	}

	@Override
	void shoot(int delta) {
		
	}

	@Override
	void move(int delta) {
		double addMove=0;
		double addSpeed=target.acceleration;
		boolean moving=false;
		
		switch(InputManager.CONTROLDEVICE) {
		case InputManager.ANDROID:
			
			break;
		case InputManager.GAMEPAD:
		
			break;
		case InputManager.KEYBOARD:
			if(InputManager.isMoveUp) {
				moving=true;
			}
			if(InputManager.isMoveRight) {
				addMove+=90;
				moving=true;
			}
			if(InputManager.isMoveDown) {
				addMove+=180;
				moving=true;
			}
			if(InputManager.isMoveLeft) {
				addMove+=270;
				addMove%=360;
				moving=true;
			}
			break;
		}
		
		
	}

	@Override
	void update(int delta) {
		move(delta);
		shoot(delta);
	}



}
