import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class gPanel extends StateBasedGame {

	public gPanel(String name) {
		super(name);
	}

	final static String gameName = "Serotope";
	final static String VERSION = "0.15.4.13";
	
	
	public static final int MAINMENUID = 0;
	public static final int PLAYID = 1;
	
	public static final int PWIDTH = 1280;
	public static final int PHEIGHT=800;
	public static final int FPS = 60;
	
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new gPanel("Serotope" + VERSION));
			app.setDisplayMode(PWIDTH, PHEIGHT, false);
			app.setShowFPS(true);
			app.setTargetFrameRate(FPS);
			app.start();
		}
		catch(SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MAINMENUID).init(gc,this);
		this.getState(PLAYID).init(gc, this);
		this.enterState(MAINMENUID);
	}

}
