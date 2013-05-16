package Serotope;

import org.jbox2d.common.Vec2;

import Menu.InputManager;

public class PlayerController extends Controller {

	public PlayerController(Creature creature) {
		super(creature);
	}

	public void update(int delta) {
		move(delta);
		shoot(delta);
	}

	void move(int delta) {
		switch (InputManager.CONTROLDEVICE) {
		case InputManager.ANDROID:

			break;
		case InputManager.GAMEPAD:

			break;
		case InputManager.KEYBOARD:
			int verticleFlag = 0;
			int horizontalFlag = 0;
			target.setSprinting(InputManager.isSprint);
			if (InputManager.isMoveDown) {
				verticleFlag -= 1;
			}
			if (InputManager.isMoveUp) {
				verticleFlag += 1;
			}
			if (InputManager.isMoveLeft) {
				horizontalFlag -= 1;
			}
			if (InputManager.isMoveRight) {
				horizontalFlag += 1;
			}
			if (horizontalFlag == 0 && verticleFlag == 0) {
				return;
			}
			Vec2 move = new Vec2(horizontalFlag, verticleFlag);
			move.normalize();
			target.move(move);
		}
	}

	void shoot(int delta) {
		Vec2 velocity = new Vec2(0, 0);
		switch (InputManager.CONTROLDEVICE) {
		case InputManager.ANDROID:

			break;
		case InputManager.GAMEPAD:

			break;
		case InputManager.KEYBOARD:
			int verticleFlag = 0;
			int horizontalFlag = 0;
			if (InputManager.isShootDown) {
				verticleFlag -= 1;
			}
			if (InputManager.isShootUp) {
				verticleFlag += 1;
			}
			if (InputManager.isShootLeft) {
				horizontalFlag -= 1;
			}
			if (InputManager.isShootRight) {
				horizontalFlag += 1;
			}
			if (horizontalFlag == 0 && verticleFlag == 0) {
				return;
			}
			velocity.x = horizontalFlag;
			velocity.y = verticleFlag;
			velocity.normalize();
		}
		target.shoot(velocity);
	}
}
