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
	private int nextX;
	private int nextY;
	private int prevX;
	private int prevY;
	private int pageNumberX;
	private int pageNumberY;	
	private ArrayList<Heading> headingList;
	private ArrayList<Button> buttonList;
	private Button goBack;
	private PrevButton prev;
	private NextButton next;
	private ArrayList<HowToPage> pageList;

	//Helper class that holds the page number and left and right tutorial page images
	public class HowToPage{
		private String name;
		private Image right;
		private Image left;
		public HowToPage(String l,String r) throws SlickException{
			pageNum++;
			name = "Page " + pageNum;
			right = new Image(r);
			left = new Image(l);
		}
	}
	
	public class NextButton extends Button{

		public NextButton(String src, int x, int y, float s, float e, int es)
				throws SlickException {
			super(src, x, y, s, e, es);
		}
		
		public boolean buttonPressed(int delta, int x, int y, GameContainer gc, StateBasedGame sbg){
			//Checks if the next page button is pressed and changes the current page respectively if logical
			int curPageNum = pageList.indexOf(currentPage);
			boolean mouseOver = false;
			if(isInside(x, y)){
				mouseOver = true;
	    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && curPageNum+1 < pageList.size()){
	    			currentPage = pageList.get(curPageNum+1);
	    		}
	    	}
			if(gc.getInput().isKeyPressed(Input.KEY_RIGHT) && curPageNum+1 < pageList.size()){
				currentPage = pageList.get(curPageNum+1);
			}
			return mouseOver;
		}
	}
	
	public class PrevButton extends Button{

		public PrevButton(String src, int x, int y, float s, float e, int es)
				throws SlickException {
			super(src, x, y, s, e, es);
		}
		
		public boolean buttonPressed(int delta, int x, int y, GameContainer gc, StateBasedGame sbg){
			int curPageNum = pageList.indexOf(currentPage);
			boolean mouseOver = false;
			//Checks if the prev page button is pressed and changes the current page respectively if logical
	    	if(isInside(x, y)){
	    		mouseOver = true;
	    		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON) && curPageNum-1 >= 0){
	    			currentPage = pageList.get(curPageNum-1);
	    		}
	    	}
			if(gc.getInput().isKeyPressed(Input.KEY_LEFT) && curPageNum-1 >= 0){
				currentPage = pageList.get(curPageNum-1);
			}
			return mouseOver;
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
		
		//The x y position the next and prev buttons are drawn at
		nextX = gc.getWidth()-nexti.getWidth();
		nextY = Utils.SECONDHEADING;
		prevX = 0;
		prevY = Utils.SECONDHEADING;
		
		//Initialises the headings and buttons of this page
		headingList = new ArrayList<Heading>();
		buttonList = new ArrayList<Button>();
		headingList.add(new Heading(Utils.HOWTOTITLE,Utils.LEFTALIGNX,Utils.TITLEPOSY));
		headingList.add(goBack = new Button(Utils.GOBACK,Utils.BOTRIGHTX,Utils.BOTRIGHTY,Utils.STARTSCALE,Utils.ENLARGE,gPanel.MAINMENUID));
		buttonList.add(goBack);
		goBack.setSelected(true);
		
		//next and prev are not added to headingList since they are not always rendered
		buttonList.add(next = new NextButton(Utils.HOWTONEXT,nextX,nextY,Utils.STARTSCALE,Utils.ENLARGE,gPanel.HOWTOMENUID));
		buttonList.add(prev = new PrevButton(Utils.HOWTOPREV,prevX,prevY,Utils.STARTSCALE,Utils.ENLARGE,gPanel.HOWTOMENUID));
		
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
		pageList.add(currentPage = new HowToPage(Utils.HOWTOPAGE1P,Utils.HOWTOPAGE1T));
		pageList.add(new HowToPage(Utils.HOWTOPAGE2P,Utils.HOWTOPAGE2T));
		pageList.add(new HowToPage(Utils.HOWTOPAGE3P,Utils.HOWTOPAGE3T));
		pageList.add(new HowToPage(Utils.HOWTOPAGE4P,Utils.HOWTOPAGE4T));
		pageList.add(new HowToPage(Utils.HOWTOPAGE5P,Utils.HOWTOPAGE5T));
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
		g.setColor(Color.white);
		g.drawString(currentPage.name + " of " + pageNum,pageNumberX,pageNumberY);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		//Gets mouse co-oridnates and the current page number
		int mouseX;
		int mouseY;
    	mouseX = gc.getInput().getMouseX();
    	mouseY = gc.getInput().getMouseY();
    	
    	for(Button button : buttonList){
    		button.buttonPressed(delta, mouseX, mouseY, gc, sbg);
    	}
    	
    	//Sets the page arrows to visible depending on the current page
    	if(pageList.indexOf(currentPage) == 0)
    		showPrev = false;
    	else
    		showPrev = true;
    	if(pageList.indexOf(currentPage) == pageList.size()-1)
    		showNext = false;
    	else
    		showNext = true;
    	
	}
	

	@Override
	public int getID() {
		return id;
	}
	

}
