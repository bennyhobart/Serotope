import org.newdawn.slick.GameContainer;
import org.lwjgl.input.Keyboard;

public class InputManager {

	static int KeyForward;
	static int KeyDown;
	static int KeyUp;
	static int KeyBack;
	static int KeyJump;
	static int KeyEscape;
	static int KeyStart;
	
	static boolean isKeyForward;
	static boolean isKeyDown;
	static boolean isKeyUp;
	static boolean isKeyBack;
	static boolean isKeyJump;
	static boolean isKeyEscape;
	static boolean isKeyStart;
	
	static private final int KEYBOARD =0;
	static private final int GAMEPAD=1;
	static private final int ANDROID=2;
	static int CONTROLDEVICE;
	GameContainer gc;
	public InputManager(GameContainer gc) {
		this.gc=gc;
		//assuming keyboard
		CONTROLDEVICE=KEYBOARD;
		KeyForward =Keyboard.KEY_RIGHT;
		KeyBack = Keyboard.KEY_LEFT;
		KeyDown = Keyboard.KEY_DOWN;
		KeyUp = Keyboard.KEY_UP;
		KeyJump = Keyboard.KEY_UP;
		KeyEscape = Keyboard.KEY_ESCAPE;
		KeyStart = Keyboard.KEY_RETURN;
		
	}
	
}
