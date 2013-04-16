import org.newdawn.slick.GameContainer;
import org.lwjgl.input.Keyboard;

public class InputManager {

	static int MoveRight;
	static int MoveDown;
	static int MoveUp;
	static int MoveLeft;
	static int ShootRight;
	static int ShootDown;
	static int ShootUp;
	static int ShootLeft;
	static int KeyEscape;
	static int KeyStart;
	
	static boolean isMoveRight;
	static boolean isMoveDown;
	static boolean isMoveUp;
	static boolean isMoveLeft;
	static boolean isShootRight;
	static boolean isShootDown;
	static boolean isShootUp;
	static boolean isShootLeft;
	static boolean isKeyEscape;
	static boolean isKeyStart;
	
	static public final int KEYBOARD =0;
	static public final int GAMEPAD=1;
	static public final int ANDROID=2;
	static int CONTROLDEVICE;
	GameContainer gc;
	public InputManager(GameContainer gc) {
		this.gc=gc;
		//assuming keyboard
		CONTROLDEVICE=KEYBOARD;
		MoveRight =Keyboard.KEY_D;
		MoveLeft = Keyboard.KEY_A;
		MoveDown = Keyboard.KEY_S;
		MoveUp = Keyboard.KEY_W;
		ShootRight = Keyboard.KEY_RIGHT;
		ShootDown = Keyboard.KEY_DOWN;
		ShootUp = Keyboard.KEY_UP;
		ShootLeft = Keyboard.KEY_LEFT;
		KeyEscape = Keyboard.KEY_ESCAPE;
		KeyStart = Keyboard.KEY_RETURN;
	}
	public static void update(GameContainer gc) {
		//input
		if(gc.getInput().isKeyDown(MoveRight)) {
			isMoveRight=true;
		}
		else {
			isMoveRight=false;
		}
		if(gc.getInput().isKeyDown(MoveLeft)) {
			isMoveLeft=true;
		}
		else {
			isMoveLeft=false;
		}
		if(gc.getInput().isKeyDown(MoveDown)) {
			isMoveDown=true;
		}
		else {
			isMoveDown=false;
		}
		if(gc.getInput().isKeyDown(MoveUp)) {
			isMoveUp=true;
		}
		else {
			isMoveUp=false;
		}
		if(gc.getInput().isKeyDown(ShootRight)) {
			isShootRight=true;
		}
		else {
			isShootRight=false;
		}
		if(gc.getInput().isKeyDown(ShootLeft)) {
			isShootLeft=true;
		}
		else {
			isShootLeft=false;
		}
		if(gc.getInput().isKeyDown(ShootDown)) {
			isShootDown=true;
		}
		else {
			isShootDown=false;
		}
		if(gc.getInput().isKeyDown(ShootUp)) {
			isShootUp=true;
		}
		else {
			isShootDown=false;
		}
		if(gc.getInput().isKeyDown(KeyEscape)) {
			isKeyEscape=true;
		}
		else {
			isKeyEscape=false;
		}
		if(gc.getInput().isKeyDown(KeyStart)) {
			isKeyStart=true;
		}
		else {
			isKeyStart=false;
		}
	}
	
}
