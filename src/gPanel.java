import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class gPanel extends StateBasedGame
{
	final static String gameName = "Serotope";
	final static String VERSION = "0.18.4.13";
	
	
	public static final int MAINMENUID = 0;
	public static final int PLAYID = 1;
	
	public static final int PWIDTH = 1100;
	public static final int PHEIGHT=700;
	public static final int FPS = 60;
	
	
	
	public static InputManager Input;
	
	
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new gPanel());
			app.setDisplayMode(PWIDTH, PHEIGHT, false);
			Input = new InputManager(app); 
			app.setShowFPS(true);
			app.setTargetFrameRate(FPS);
			app.start();
		}
		catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
	public gPanel(){
		super(gameName+ " " +VERSION );
		this.addState(new MainMenu(MAINMENUID));
		this.addState(new Play(PLAYID));
	}
	
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MAINMENUID).init(gc,this);
		this.getState(PLAYID).init(gc, this);
		this.enterState(MAINMENUID);
	}
}
