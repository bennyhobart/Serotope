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

	//Helper class that holds the page number and left and right tutorial page images
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
		//Sets background colour
        Color background = new Color(Color.black);
        gc.getGraphics().setBackground(background);
        
        //Creates dummy images of the page arrows and tutorial screen images
		Image nexti = new Image(Utils.HOWTONEXT);
		Image previ = new Image(Utils.HOWTOPREV);
		Image dummy = new Image(Utils.HOWTODUMMY);
		
		//Initialises the headings and buttons of this page
		headingList = new ArrayList<Heading>();
		headingList.add(new Heading(Utils.HOWTOTITLE,gc.getWidth()/8,gc.getHeight()/6));
		headingList.add(goBack = new Button(Utils.GOBACK,gc.getWidth()/8*7,gc.getHeight()/12*11,Utils.STARTSCALE,Utils.ENLARGE,gPanel.MAINMENUID));
		//next and prev are not added to headingList since they are not always rendered
		next = new Button(Utils.HOWTONEXT,gc.getWidth()-nexti.getWidth(),gc.getHeight()/2,Utils.STARTSCALE,Utils.ENLARGE,gPanel.HOWTOMENUID);
		prev = new Button(Utils.HOWTOPREV,0,gc.getHeight()/2,Utils.STARTSCALE,Utils.ENLARGE,gPanel.HOWTOMENUID);
		
		//Helps with adjusting rendering positions
		int adjust = gc.getWidth()/25;
		
		//Sets the rendering positions for the left and right tutorial images and page number
		leftPosX = previ.getWidth()+adjust;
		leftPosY = gc.getHeight()/3;
		rightPosX = gc.getWidth()-dummy.getWidth()-nexti.getWidth()-adjust;
		rightPosY = gc.getHeight()/3;
		pageNumberX = gc.getWidth()/2-adjust;
		pageNumberY = gc.getHeight()/12*11;
		pageNum = 0;
		
		//Creates a list of all the tutorial pages and sets the current page to the first
		pageList = new ArrayList<HowToPage>();
		pageList.add(currentPage = new HowToPage(dummy,dummy));
		pageList.add(new HowToPage(dummy,dummy));
		showPrev = false;
		showNext = true;

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		//Draws each of the page's headings and buttons
		for(Heading heading : headingList)
			heading.draw();
		
		//Checks if the page arrows need to be drawn and then does so
		if(showNext){
			next.draw();
		}
		if(showPrev){
			prev.draw();
		}
		
		//Draws the current page of the tutorial to the screen and page number
		currentPage.left.draw(leftPosX,leftPosY);
		currentPage.right.draw(rightPosX,rightPosY);
		g.drawString(currentPage.name + " of " + pageNum,pageNumberX,pageNumberY);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		//Gets mouse co-oridnates and the current page number
		int mouseX;
		int mouseY;
		int curPageNum; 	
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();
    	curPageNum = pageList.indexOf(currentPage);
    	
    	//Checks if the next or prev page buttons are pressed and changes the current page respectively if logical
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
    	
    	//Sets the page arrows to visible depending on the current page
    	if(curPageNum == 0)
    		showPrev = false;
    	else
    		showPrev = true;
    	if(curPageNum == pageList.size()-1)
    		showNext = false;
    	else
    		showNext = true;
    	
    	//Checks if the Go Back button is pressed and executes the action if so
    	Utils.buttonPressed(delta, mouseX, mouseY, goBack, gc, sbg);
    	
	}
	

	@Override
	public int getID() {
		return id;
	}
	

}
