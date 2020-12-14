package frogger.view;


import java.util.ArrayList;

import frogger.constant.FilePath;
import frogger.controller.MenuController;
import frogger.model.Background;
import frogger.model.Frog;
import frogger.util.animation.MenuAnimation;
import frogger.util.buttons.MenuButton;
import javafx.scene.layout.Pane;


/**
 * This class acts as view to the corresponding {@link MenuController}
 */
public class MenuScreen {
	private static MenuScreen instance = null;
	private static final int BUTTON_XPOS = 200;
	private static final int BUTTON_START_YPOS = 200;
	private Pane menuPane;
	private ArrayList<MenuButton> menuButtonList;

	private MenuAnimation anim;
	/**
	 * This method ensures only one instance of this {@link 
	 * MenuScreen} object is created
	 * @return  {@code MenuScreen} object
	 */
	public static MenuScreen getInstance() {
		if(instance == null) {
			instance = new MenuScreen();
		} 
		return instance;
	}
	
	/**
	 * This private constructor initializes {@link #menuPane}
	 * and {@link MenuAnimation} and starts the animation
	 */
	private MenuScreen() {
		menuButtonList = new ArrayList<MenuButton>();
		initializeMenuPane();
		initAnim();
		playAnim();

	}
	
	/**
	 * This method returns this {@code #menuPane} to be 
	 * displayed
	 * @return {@code #menuPane}
	 */
	
	public Pane getPane() {
		return menuPane;
	}
	
	/**
	 * This method initializes the {@code Pane}
	 * object and the elements to be displayed
	 * such as the background and buttons
	 */
	private void initializeMenuPane() {
		menuPane = new Pane();
		menuPane.setCache(true);
		createMenuBackground();
		createButtons();
	}
	
	
	/**
	 * This method creates the start, info and exit button
	 */
	private void createButtons() {
		createStartButton();
		createInfoButton();
		createExitButton();

	}
	

	/**
	 * This method creates a start button and initializes
	 * the action handler which calls {@link MenuController}
	 * to show the selection screen
	 */
	private void createStartButton() {
		MenuButton startButton = new MenuButton("play");
		startButton.setDefaultButton(true);
		addMenuButton(startButton);
		startButton.setOnAction((e)-> {
			MenuController.INSTANCE.viewSelection();
			anim.stop();
		});

	}
	
	/**
	 * This method creates an info button and initializes
	 * the action handler which calls {@link MenuController}
	 * to show the info screen
	 */
	private void createInfoButton() {
		
		MenuButton infoButton = new MenuButton("info");
		addMenuButton(infoButton);
		infoButton.setOnAction((e)-> MenuController.INSTANCE.showInfo());
		
	}
	
	/**
	 * This method creates an exit button and initializes
	 * the action handler which calls {@link MenuController}
	 * to exit the application
	 */
	private void createExitButton() {
		MenuButton exitButton = new MenuButton("exit");
		addMenuButton(exitButton);
		exitButton.setCancelButton(true);
		exitButton.setOnAction((e)-> MenuController.INSTANCE.exit());
		
	}
	
	/**
	 * This method creates and adds an {@code ImageView}
	 * that acts as background
	 */
	private void createMenuBackground() {
		Background background = new Background(FilePath.MENUBACKGROUND);
		menuPane.getChildren().add(background);

	}
	
	/**
	 * This method creates a {@link Frog} object, add it to {@code Pane}
	 * and pass the {@code Frog} to {@link MenuAnimation} for animation
	 */
	private void initAnim() {
		Frog frog = new Frog(555,706);
		frog.setRotate(-90);
		menuPane.getChildren().add(frog);
		anim = new MenuAnimation(frog);
		
	}
	
	/**
	 * This method starts the frog animation on the menu
	 * by playing the {@link MenuAnimation} object
	 */
	public void playAnim() {
		anim.play();
	}


	/**
	 * This method sets the position of a menu button and add it to <code>menuButtonList</code> 
	 * X position is standardized
	 * Y position increases with the number of menu buttons 
	 * @param button  the {@link MenuButton} object to be added
	 */
	private void addMenuButton(MenuButton button) {
		button.setLayoutX(BUTTON_XPOS);
		button.setLayoutY(BUTTON_START_YPOS + menuButtonList.size() * 100);
		menuButtonList.add(button);
		menuPane.getChildren().add(button);
		
	}
	

}

///**
// * This method creates the {@code AnimationTimer}
// * for frog animation
// */
//private void createAnim() {
//	Frog frog = new Frog(555,706);
//	menuPane.getChildren().add(frog);
//	anim = new MenuAnimation(frog);
//	Frog frog = new Frog(300, 706.467);
//	frog.setY(700);
//	timer = new AnimationTimer() {
//		@Override
//		public void handle(long now) {
//			if (now % 23 == 0) {
//				if (frog.getX()>=555) {
//					right=false;
//				} else if(frog.getX()<=10){
//		            	  right=true;
//				}
//		        
//				if(right) {
//					frog.jump(DIRECTION.RIGHT, true);
//		        } else  {
//		            frog.jump(DIRECTION.LEFT, true);
//		        }
//			}
//		}
//
//	};
//	menuPane.getChildren().add(frog);
//
//}

//Transition animation = new Transition() {
//    {
//    	setCycleCount(INDEFINITE);
//        setCycleDuration(Duration.millis(100000)); // total time for animation
//    }
//
//    @Override
//    protected void interpolate(double fraction) {
//    	if(frog.getX()<=556 && frog.getY()>=700) {
//    		  frog.jump(DIRECTION.RIGHT, true);
//    		  System.out.println("right");
//    	  }
//    		 
//    	 
//    	  if (frog.getX() >= 556 && frog.getY()>=450) {
//    		  frog.jump(DIRECTION.UP, true);
//    		  System.out.println("up");
//    		  //frog.setX(-20);
//    	  } 
//    	  
//    	  if(frog.getY()<=450 && frog.getX()<=556 && frog.getX()>=10) {
//    		  System.out.println("left");
//    		  frog.jump(DIRECTION.LEFT, true);
//    		  System.out.println("y is " + frog.getY());
//    		  System.out.println("xy is " + frog.getX());
//    	  }
//    		  
//    	  if(frog.getX()<=10 && frog.getY()<=700) {
//    		  System.out.println("down");
//    		  frog.jump(DIRECTION.DOWN, true);
//    	  }
//    		 
//    }
//};
//
//animation.play();


//AnimationTimer timer =
//new AnimationTimer() {
//  @Override
//  public void handle(long now) {
//    if (now % 83 == 0) {
//  	  if(frog.getX()<=556 && frog.getY()>=700) {
//  		  frog.jump(DIRECTION.RIGHT, true);
//  		  System.out.println("right");
//  	  }
//  		 
//  	 
//  	  if (frog.getX() >= 556 && frog.getY()>=450) {
//  		  frog.jump(DIRECTION.UP, true);
//  		  System.out.println("up");
//  		  //frog.setX(-20);
//  	  } 
//  	  
//  	  if(frog.getY()<=450 && frog.getX()<=556 && frog.getX()>=10) {
//  		  System.out.println("left");
//  		  frog.jump(DIRECTION.LEFT, true);
//  		  System.out.println("y is " + frog.getY());
//  		  System.out.println("xy is " + frog.getX());
//  	  }
//  		  
//  	  if(frog.getX()<=10 && frog.getY()<=700) {
//  		  System.out.println("down");
//  		  frog.jump(DIRECTION.DOWN, true);
//  	  }
//  		 
//  	//System.out.println("frog y" + frog.getY());//if(frog.getX() >= 556 && frog.getY()==640)
//    }
//    
//  }
//};




