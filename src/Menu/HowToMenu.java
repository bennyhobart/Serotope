package Menu;

import java.util.ArrayList;

import javax.swing.JSlider;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HowToMenu extends BasicGameState {
	
	
	private int id;
	private float goBackScale;
	private final float enlarge = 0.0001f;
	private static final String TITLE = "assets/image/menus/HowToTitle.png";
	private static final String PREV = "assets/image/menus/HowToPrev.png";
	private static final String NEXT = "assets/image/menus/HowToNext.png";
	private static final String DUMMY = "assets/image/menus/HowToPages/DummyPage.png";
	private static final int BUFFER = 20;
	private static int pageNum;
	private HowToPage currentPage;
	private boolean showPrev;
	private boolean showNext;
	private int titlePosX;
	private int titlePosY;
	private int prevPosX;
	private int prevPosY;
	private int nextPosX;
	private int nextPosY;
	private int goBackPosX;
	private int goBackPosY;
	private int leftPosX;
	private int leftPosY;
	private int rightPosX;
	private int rightPosY;
	private int pageNumberX;
	private int pageNumberY;	
	private Image title;
	private Image prev;
	private Image next;
	private Image dummy;
	private Image goBack;
	private ArrayList<HowToPage> pageList;

	public class HowToPage{
		private String name;
		private Image right;
		private Image left;
		public HowToPage(Image l,Image r){
			pageNum++;
			name = "Page " + pageNum;
			right = r;
			left = l;
		}
	}

	public HowToMenu(int id) {
		this.id=id;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException{
        Color background = new Color(Color.black);
        gc.getGraphics().setBackground(background);
		title = new Image(TITLE);
		next = new Image(NEXT);
		prev = new Image(PREV);
		dummy = new Image(DUMMY);
		goBack = new Image(MainMenu.GOBACK);
		titlePosX = gc.getWidth()/8;
		titlePosY = gc.getHeight()/6;
		nextPosX = gc.getWidth()-next.getWidth()-BUFFER;
		nextPosY = gc.getHeight()/2;
		prevPosX = 20;
		prevPosY = gc.getHeight()/2;
		leftPosX = prev.getWidth()+BUFFER*2;
		leftPosY = gc.getHeight()/3;
		rightPosX = gc.getWidth()-dummy.getWidth()-next.getWidth()-BUFFER*2;
		rightPosY = gc.getHeight()/3;
		pageNumberX = gc.getWidth()/2-BUFFER*2;
		pageNumberY = gc.getHeight()/12*11;
		goBackPosX = gc.getWidth()/8*7;
		goBackPosY = gc.getHeight()/12*11;
		goBackScale = 1;
		pageNum = 0;
		
		pageList = new ArrayList<HowToPage>();
		pageList.add(currentPage = new HowToPage(dummy,dummy));
		pageList.add(new HowToPage(dummy,dummy));
		showPrev = false;
		showNext = true;

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		title.draw(titlePosX,titlePosY);
		if(showNext){
			next.draw(nextPosX,nextPosY);
		}
		if(showPrev){
			prev.draw(prevPosX,prevPosY);
		}
		goBack.draw(goBackPosX,goBackPosY,goBackScale);
		currentPage.left.draw(leftPosX,leftPosY);
		currentPage.right.draw(rightPosX,rightPosY);
		g.drawString(currentPage.name + " of " + pageNum,pageNumberX,pageNumberY);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		int mouseX;
		int mouseY;
		int curPageNum;
    	
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();
    	curPageNum = pageList.indexOf(currentPage);
    	if(curPageNum == 0)
    		showPrev = false;
    	else
    		showPrev = true;
    	if(curPageNum == pageList.size()-1)
    		showNext = false;
    	else
    		showNext = true;
    	
    	if(onButton(mouseX,mouseY,goBackPosX,goBackPosY,goBack)){
    		if(goBackScale < 1.05f)
    			goBackScale += enlarge * delta;
    		
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(gPanel.MAINMENUID);
    		}
    	}else{
    		if(goBackScale > 1.0f)
    			goBackScale -= enlarge *delta;
    	}
    	
    	if(onButton(mouseX,mouseY,prevPosX,prevPosY,prev)){
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && curPageNum-1 >= 0){
    			currentPage = pageList.get(curPageNum-1);
    		}
    	}
    	
    	if(onButton(mouseX,mouseY,nextPosX,nextPosY,next)){
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && curPageNum+1 < pageList.size()){
    			currentPage = pageList.get(curPageNum+1);
    		}
    	}
    	
	}
	
	private boolean onButton(int mouseX, int mouseY, int buttonX, int buttonY, Image button){
    	if( ( mouseX >= buttonX && mouseX <= buttonX + button.getWidth()) &&
    		    ( mouseY >= buttonY && mouseY <= buttonY + button.getHeight()) ){
    		return true;
    	}else
    		return false;
    	
	}

	@Override
	public int getID() {
		return id;
	}
	

}
