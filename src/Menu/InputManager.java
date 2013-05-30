package Menu;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
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
	static int KeySprint;
	
	static final int  MoveRightRef = 1;
	static final int MoveDownRef = 2;
	static final int MoveUpRef = 3;
	static final int MoveLeftRef = 4;
	static final int ShootRightRef = 5;
	static final int ShootDownRef = 6;
	static final int ShootUpRef = 7;
	static final int ShootLeftRef = 8;
	static final int SprintRef = 9;
	static final int PauseRef = 10;
	
	public static boolean isSprint;
	public static boolean isMoveRight;
	public static boolean isMoveDown;
	public static boolean isMoveUp;
	public static boolean isMoveLeft;
	public static boolean isShootRight;
	public static boolean isShootDown;
	public static boolean isShootUp;
	public static boolean isShootLeft;
	public static boolean isKeyEscape;
	public static boolean isKeyStart;
	
	static public final int KEYBOARD =0;
	static public final int GAMEPAD=1;
	static public final int ANDROID=2;
	public static int CONTROLDEVICE;
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
		KeySprint = Keyboard.KEY_LSHIFT;
	}
	
	//Changes the current key assignment
	static void changeKey(int from, int to) {
		switch(from) {
		case MoveRightRef: MoveRight = to;
		break;
		case MoveDownRef: MoveDown = to;
		break;
		case MoveUpRef: MoveUp = to;
		break;
		case MoveLeftRef: MoveLeft = to;
		break;
		case ShootRightRef: ShootRight = to;
		break;
		case ShootDownRef: ShootDown = to;
		break;
		case ShootUpRef: ShootUp = to;
		break;
		case ShootLeftRef: ShootLeft = to;
		break;
		case SprintRef: KeySprint = to;
		break;
		case PauseRef: KeyEscape = to;
		
		}
	}
	
	//Returns the string of the respective key
	static String returnkey(int ref){
		switch(ref) {
		case MoveRightRef: return Keyboard.getKeyName(MoveRight);
		case MoveDownRef: return Keyboard.getKeyName(MoveDown);
		case MoveUpRef: return Keyboard.getKeyName(MoveUp);
		case MoveLeftRef: return Keyboard.getKeyName(MoveLeft);
		case ShootRightRef: return Keyboard.getKeyName(ShootRight);
		case ShootDownRef: return Keyboard.getKeyName(ShootDown);
		case ShootUpRef: return Keyboard.getKeyName(ShootUp);
		case ShootLeftRef: return Keyboard.getKeyName(ShootLeft);
		case SprintRef: return Keyboard.getKeyName(KeySprint);
		case PauseRef: return Keyboard.getKeyName(KeyEscape);
		}
		return "";
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
			isShootUp=false;
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
		if(gc.getInput().isKeyDown(KeySprint)) {
			isSprint=true;
		}
		else {
			isSprint=false;
		}
	}
	
	//Returns the current key that is pressed
	public static int returnKeyPressed(Input input){
		if(input.isKeyDown(Keyboard.KEY_0))
			return Keyboard.KEY_0;
		else if(input.isKeyDown(Keyboard.KEY_1))
			return Keyboard.KEY_1;
		else if(input.isKeyDown(Keyboard.KEY_2))
			return Keyboard.KEY_2;
		else if(input.isKeyDown(Keyboard.KEY_3))
			return Keyboard.KEY_3;
		else if(input.isKeyDown(Keyboard.KEY_4))
			return Keyboard.KEY_4;
		else if(input.isKeyDown(Keyboard.KEY_5))
			return Keyboard.KEY_5;
		else if(input.isKeyDown(Keyboard.KEY_6))
			return Keyboard.KEY_6;
		else if(input.isKeyDown(Keyboard.KEY_7))
			return Keyboard.KEY_7;
		else if(input.isKeyDown(Keyboard.KEY_8))
			return Keyboard.KEY_8;
		else if(input.isKeyDown(Keyboard.KEY_9))
			return Keyboard.KEY_9;
		else if(input.isKeyDown(Keyboard.KEY_Q))
			return Keyboard.KEY_Q;
		else if(input.isKeyDown(Keyboard.KEY_W))
			return Keyboard.KEY_W;
		else if(input.isKeyDown(Keyboard.KEY_E))
			return Keyboard.KEY_E;
		else if(input.isKeyDown(Keyboard.KEY_R))
			return Keyboard.KEY_R;
		else if(input.isKeyDown(Keyboard.KEY_T))
			return Keyboard.KEY_T;
		else if(input.isKeyDown(Keyboard.KEY_Y))
			return Keyboard.KEY_Y;
		else if(input.isKeyDown(Keyboard.KEY_U))
			return Keyboard.KEY_U;
		else if(input.isKeyDown(Keyboard.KEY_I))
			return Keyboard.KEY_I;
		else if(input.isKeyDown(Keyboard.KEY_O))
			return Keyboard.KEY_O;
		else if(input.isKeyDown(Keyboard.KEY_P))
			return Keyboard.KEY_P;
		else if(input.isKeyDown(Keyboard.KEY_A))
			return Keyboard.KEY_A;
		else if(input.isKeyDown(Keyboard.KEY_S))
			return Keyboard.KEY_S;
		else if(input.isKeyDown(Keyboard.KEY_D))
			return Keyboard.KEY_D;
		else if(input.isKeyDown(Keyboard.KEY_F))
			return Keyboard.KEY_F;
		else if(input.isKeyDown(Keyboard.KEY_G))
			return Keyboard.KEY_G;
		else if(input.isKeyDown(Keyboard.KEY_H))
			return Keyboard.KEY_H;
		else if(input.isKeyDown(Keyboard.KEY_J))
			return Keyboard.KEY_J;
		else if(input.isKeyDown(Keyboard.KEY_K))
			return Keyboard.KEY_K;
		else if(input.isKeyDown(Keyboard.KEY_L))
			return Keyboard.KEY_L;
		else if(input.isKeyDown(Keyboard.KEY_Z))
			return Keyboard.KEY_Z;
		else if(input.isKeyDown(Keyboard.KEY_X))
			return Keyboard.KEY_X;
		else if(input.isKeyDown(Keyboard.KEY_C))
			return Keyboard.KEY_C;
		else if(input.isKeyDown(Keyboard.KEY_V))
			return Keyboard.KEY_V;
		else if(input.isKeyDown(Keyboard.KEY_B))
			return Keyboard.KEY_B;
		else if(input.isKeyDown(Keyboard.KEY_N))
			return Keyboard.KEY_N;
		else if(input.isKeyDown(Keyboard.KEY_M))
			return Keyboard.KEY_M;
		else if(input.isKeyDown(Keyboard.KEY_ADD))
			return Keyboard.KEY_ADD;
		else if(input.isKeyDown(Keyboard.KEY_APOSTROPHE))
			return Keyboard.KEY_APOSTROPHE;
		else if(input.isKeyDown(Keyboard.KEY_APPS))
			return Keyboard.KEY_APPS;
		else if(input.isKeyDown(Keyboard.KEY_AT))
			return Keyboard.KEY_AT;
		else if(input.isKeyDown(Keyboard.KEY_AX))
			return Keyboard.KEY_AX;
		else if(input.isKeyDown(Keyboard.KEY_BACK))
			return Keyboard.KEY_BACK;
		else if(input.isKeyDown(Keyboard.KEY_BACKSLASH))
			return Keyboard.KEY_BACKSLASH;
		else if(input.isKeyDown(Keyboard.KEY_CAPITAL))
			return Keyboard.KEY_CAPITAL;
		else if(input.isKeyDown(Keyboard.KEY_CIRCUMFLEX))
			return Keyboard.KEY_CIRCUMFLEX;
		else if(input.isKeyDown(Keyboard.KEY_COLON))
			return Keyboard.KEY_COLON;
		else if(input.isKeyDown(Keyboard.KEY_COMMA))
			return Keyboard.KEY_COMMA;
		else if(input.isKeyDown(Keyboard.KEY_CONVERT))
			return Keyboard.KEY_CONVERT;
		else if(input.isKeyDown(Keyboard.KEY_DECIMAL))
			return Keyboard.KEY_DECIMAL;
		else if(input.isKeyDown(Keyboard.KEY_DELETE))
			return Keyboard.KEY_DELETE;
		else if(input.isKeyDown(Keyboard.KEY_END))
			return Keyboard.KEY_END;
		else if(input.isKeyDown(Keyboard.KEY_EQUALS))
			return Keyboard.KEY_EQUALS;
		else if(input.isKeyDown(Keyboard.KEY_ESCAPE))
			return Keyboard.KEY_ESCAPE;
		else if(input.isKeyDown(Keyboard.KEY_GRAVE))
			return Keyboard.KEY_GRAVE;
		else if(input.isKeyDown(Keyboard.KEY_HOME))
			return Keyboard.KEY_HOME;
		else if(input.isKeyDown(Keyboard.KEY_INSERT))
			return Keyboard.KEY_INSERT;
		else if(input.isKeyDown(Keyboard.KEY_KANA))
			return Keyboard.KEY_KANA;
		else if(input.isKeyDown(Keyboard.KEY_KANJI))
			return Keyboard.KEY_KANJI;
		else if(input.isKeyDown(Keyboard.KEY_LBRACKET))
			return Keyboard.KEY_LBRACKET;
		else if(input.isKeyDown(Keyboard.KEY_LCONTROL))
			return Keyboard.KEY_LCONTROL;
		else if(input.isKeyDown(Keyboard.KEY_LEFT))
			return Keyboard.KEY_LEFT;
		else if(input.isKeyDown(Keyboard.KEY_LMENU))
			return Keyboard.KEY_LMENU;
		else if(input.isKeyDown(Keyboard.KEY_LMETA))
			return Keyboard.KEY_LMETA;
		else if(input.isKeyDown(Keyboard.KEY_LSHIFT))
			return Keyboard.KEY_LSHIFT;
		else if(input.isKeyDown(Keyboard.KEY_MINUS))
			return Keyboard.KEY_MINUS;
		else if(input.isKeyDown(Keyboard.KEY_MULTIPLY))
			return Keyboard.KEY_MULTIPLY;
		else if(input.isKeyDown(Keyboard.KEY_NEXT))
			return Keyboard.KEY_NEXT;
		else if(input.isKeyDown(Keyboard.KEY_NOCONVERT))
			return Keyboard.KEY_NOCONVERT;
		else if(input.isKeyDown(Keyboard.KEY_NONE))
			return Keyboard.KEY_NONE;
		else if(input.isKeyDown(Keyboard.KEY_NUMLOCK))
			return Keyboard.KEY_NUMLOCK;
		else if(input.isKeyDown(Keyboard.KEY_NUMPAD0))
			return Keyboard.KEY_NUMPAD0;
		else if(input.isKeyDown(Keyboard.KEY_NUMPAD1))
			return Keyboard.KEY_NUMPAD1;
		else if(input.isKeyDown(Keyboard.KEY_NUMPAD2))
			return Keyboard.KEY_NUMPAD2;
		else if(input.isKeyDown(Keyboard.KEY_NUMPAD3))
			return Keyboard.KEY_NUMPAD3;
		else if(input.isKeyDown(Keyboard.KEY_NUMPAD4))
			return Keyboard.KEY_NUMPAD4;
		else if(input.isKeyDown(Keyboard.KEY_NUMPAD5))
			return Keyboard.KEY_NUMPAD5;
		else if(input.isKeyDown(Keyboard.KEY_NUMPAD6))
			return Keyboard.KEY_NUMPAD6;
		else if(input.isKeyDown(Keyboard.KEY_NUMPAD7))
			return Keyboard.KEY_NUMPAD7;
		else if(input.isKeyDown(Keyboard.KEY_NUMPAD8))
			return Keyboard.KEY_NUMPAD8;
		else if(input.isKeyDown(Keyboard.KEY_NUMPAD9))
			return Keyboard.KEY_NUMPAD9;
		else if(input.isKeyDown(Keyboard.KEY_NUMPADCOMMA))
			return Keyboard.KEY_NUMPADCOMMA;
		else if(input.isKeyDown(Keyboard.KEY_NUMPADENTER))
			return Keyboard.KEY_NUMPADENTER;
		else if(input.isKeyDown(Keyboard.KEY_NUMPADEQUALS))
			return Keyboard.KEY_NUMPADEQUALS;
		else if(input.isKeyDown(Keyboard.KEY_PAUSE))
			return Keyboard.KEY_PAUSE;
		else if(input.isKeyDown(Keyboard.KEY_PERIOD))
			return Keyboard.KEY_PERIOD;
		else if(input.isKeyDown(Keyboard.KEY_POWER))
			return Keyboard.KEY_POWER;
		else if(input.isKeyDown(Keyboard.KEY_PRIOR))
			return Keyboard.KEY_PRIOR;
		else if(input.isKeyDown(Keyboard.KEY_RBRACKET))
			return Keyboard.KEY_RBRACKET;
		else if(input.isKeyDown(Keyboard.KEY_RCONTROL))
			return Keyboard.KEY_RCONTROL;
		else if(input.isKeyDown(Keyboard.KEY_RIGHT))
			return Keyboard.KEY_RIGHT;
		else if(input.isKeyDown(Keyboard.KEY_RMENU))
			return Keyboard.KEY_RMENU;
		else if(input.isKeyDown(Keyboard.KEY_RMETA))
			return Keyboard.KEY_RMETA;
		else if(input.isKeyDown(Keyboard.KEY_RSHIFT))
			return Keyboard.KEY_RSHIFT;
		else if(input.isKeyDown(Keyboard.KEY_SCROLL))
			return Keyboard.KEY_SCROLL;
		else if(input.isKeyDown(Keyboard.KEY_SEMICOLON))
			return Keyboard.KEY_SEMICOLON;
		else if(input.isKeyDown(Keyboard.KEY_SLASH))
			return Keyboard.KEY_SLASH;
		else if(input.isKeyDown(Keyboard.KEY_SLEEP))
			return Keyboard.KEY_SLEEP;
		else if(input.isKeyDown(Keyboard.KEY_SPACE))
			return Keyboard.KEY_SPACE;
		else if(input.isKeyDown(Keyboard.KEY_STOP))
			return Keyboard.KEY_STOP;
		else if(input.isKeyDown(Keyboard.KEY_SUBTRACT))
			return Keyboard.KEY_SUBTRACT;
		else if(input.isKeyDown(Keyboard.KEY_SYSRQ))
			return Keyboard.KEY_SYSRQ;
		else if(input.isKeyDown(Keyboard.KEY_TAB))
			return Keyboard.KEY_TAB;
		else if(input.isKeyDown(Keyboard.KEY_UNDERLINE))
			return Keyboard.KEY_UNDERLINE;
		else if(input.isKeyDown(Keyboard.KEY_UNLABELED))
			return Keyboard.KEY_UNLABELED;
		else if(input.isKeyDown(Keyboard.KEY_UP))
			return Keyboard.KEY_UP;
		else if(input.isKeyDown(Keyboard.KEY_YEN))
			return Keyboard.KEY_YEN;
		else
			return -1;
	}
	
	//Returns the current key been pressed restricted to letters, numbers, backspace and return
	public static int returnLetterPressed(Input input){
		if(input.isKeyPressed(Keyboard.KEY_BACK))
			return Keyboard.KEY_BACK;
		else if(input.isKeyPressed(Keyboard.KEY_RETURN))
			return Keyboard.KEY_RETURN;
		else if(input.isKeyPressed(Keyboard.KEY_0))
			return Keyboard.KEY_0;
		else if(input.isKeyPressed(Keyboard.KEY_1))
			return Keyboard.KEY_1;
		else if(input.isKeyPressed(Keyboard.KEY_2))
			return Keyboard.KEY_2;
		else if(input.isKeyPressed(Keyboard.KEY_3))
			return Keyboard.KEY_3;
		else if(input.isKeyPressed(Keyboard.KEY_4))
			return Keyboard.KEY_4;
		else if(input.isKeyPressed(Keyboard.KEY_5))
			return Keyboard.KEY_5;
		else if(input.isKeyPressed(Keyboard.KEY_6))
			return Keyboard.KEY_6;
		else if(input.isKeyPressed(Keyboard.KEY_7))
			return Keyboard.KEY_7;
		else if(input.isKeyPressed(Keyboard.KEY_8))
			return Keyboard.KEY_8;
		else if(input.isKeyPressed(Keyboard.KEY_9))
			return Keyboard.KEY_9;
		else if(input.isKeyPressed(Keyboard.KEY_Q))
			return Keyboard.KEY_Q;
		else if(input.isKeyPressed(Keyboard.KEY_W))
			return Keyboard.KEY_W;
		else if(input.isKeyPressed(Keyboard.KEY_E))
			return Keyboard.KEY_E;
		else if(input.isKeyPressed(Keyboard.KEY_R))
			return Keyboard.KEY_R;
		else if(input.isKeyPressed(Keyboard.KEY_T))
			return Keyboard.KEY_T;
		else if(input.isKeyPressed(Keyboard.KEY_Y))
			return Keyboard.KEY_Y;
		else if(input.isKeyPressed(Keyboard.KEY_U))
			return Keyboard.KEY_U;
		else if(input.isKeyPressed(Keyboard.KEY_I))
			return Keyboard.KEY_I;
		else if(input.isKeyPressed(Keyboard.KEY_O))
			return Keyboard.KEY_O;
		else if(input.isKeyPressed(Keyboard.KEY_P))
			return Keyboard.KEY_P;
		else if(input.isKeyPressed(Keyboard.KEY_A))
			return Keyboard.KEY_A;
		else if(input.isKeyPressed(Keyboard.KEY_S))
			return Keyboard.KEY_S;
		else if(input.isKeyPressed(Keyboard.KEY_D))
			return Keyboard.KEY_D;
		else if(input.isKeyPressed(Keyboard.KEY_F))
			return Keyboard.KEY_F;
		else if(input.isKeyPressed(Keyboard.KEY_G))
			return Keyboard.KEY_G;
		else if(input.isKeyPressed(Keyboard.KEY_H))
			return Keyboard.KEY_H;
		else if(input.isKeyPressed(Keyboard.KEY_J))
			return Keyboard.KEY_J;
		else if(input.isKeyPressed(Keyboard.KEY_K))
			return Keyboard.KEY_K;
		else if(input.isKeyPressed(Keyboard.KEY_L))
			return Keyboard.KEY_L;
		else if(input.isKeyPressed(Keyboard.KEY_Z))
			return Keyboard.KEY_Z;
		else if(input.isKeyPressed(Keyboard.KEY_X))
			return Keyboard.KEY_X;
		else if(input.isKeyPressed(Keyboard.KEY_C))
			return Keyboard.KEY_C;
		else if(input.isKeyPressed(Keyboard.KEY_V))
			return Keyboard.KEY_V;
		else if(input.isKeyPressed(Keyboard.KEY_B))
			return Keyboard.KEY_B;
		else if(input.isKeyPressed(Keyboard.KEY_N))
			return Keyboard.KEY_N;
		else if(input.isKeyPressed(Keyboard.KEY_M))
			return Keyboard.KEY_M;
		else
			return -1;
	}
	
}
