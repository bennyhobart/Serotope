package Menu;

import java.util.ArrayList;

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
	private static int pageNum;
	private HowToPage currentPage;
	private boolean showPrev;
	private boolean showNext;
	private int leftPosX;
	private int leftPosY;
	private int rightPosX;
	private int rightPosY;
	private int pageNumberX;
	private int pageNumberY;	
	private ArrayList<Heading> headingList;
	private Button goBack;
	private Button prev;
	private Button next;
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
		Image nexti = new Image(Utils.HOWTONEXT);
		Image previ = new Image(Utils.HOWTOPREV);
		Image dummy = new Image(Utils.HOWTODUMMY);
		headingList = new ArrayList<Heading>();
		headingList.add(new Heading(Utils.HOWTOTITLE,gc.getWidth()/8,gc.getHeight()/6));
		headingList.add(goBack = new Button(Utils.GOBACK,gc.getWidth()/8*7,gc.getHeight()/12*11,Utils.STARTSCALE,Utils.ENLARGE,gPanel.MAINMENUID));
		next = new Button(Utils.HOWTONEXT,gc.getWidth()-nexti.getWidth(),gc.getHeight()/2,Utils.STARTSCALE,Utils.ENLARGE,gPanel.HOWTOMENUID);
		prev = new Button(Utils.HOWTOPREV,0,gc.getHeight()/2,Utils.STARTSCALE,Utils.ENLARGE,gPanel.HOWTOMENUID);
		
		leftPosX = previ.getWidth()+Utils.ARROWBUFFER*2;
		leftPosY = gc.getHeight()/3;
		rightPosX = gc.getWidth()-dummy.getWidth()-nexti.getWidth()-Utils.ARROWBUFFER*2;
		rightPosY = gc.getHeight()/3;
		pageNumberX = gc.getWidth()/2-Utils.ARROWBUFFER*2;
		pageNumberY = gc.getHeight()/12*11;
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
		for(Heading heading : headingList)
			heading.draw();
		
		if(showNext){
			next.draw();
		}
		if(showPrev){
			prev.draw();
		}
		
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
    	
    	if(prev.isInside(mouseX, mouseY)){
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && curPageNum-1 >= 0){
    			currentPage = pageList.get(curPageNum-1);
    		}
    	}
    	
    	if(next.isInside(mouseX, mouseY)){
    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && curPageNum+1 < pageList.size()){
    			currentPage = pageList.get(curPageNum+1);
    		}
    	}
    	
    	if(curPageNum == 0)
    		showPrev = false;
    	else
    		showPrev = true;
    	if(curPageNum == pageList.size()-1)
    		showNext = false;
    	else
    		showNext = true;
    	
    	
    	Utils.buttonPressed(delta, mouseX, mouseY, goBack, gc, sbg);
    	
	}
	

	@Override
	public int getID() {
		return id;
	}
	

}
