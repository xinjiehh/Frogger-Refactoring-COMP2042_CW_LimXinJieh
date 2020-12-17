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
 * Following MVC pattern, this class acts as view to the corresponding 
 * {@link MenuController}. This class is constructed like a view fxml 
 * file, whereby it only contains code for display and calls {@code 
 * Controller} to handle user input. This {@code Pane} object is then
 * loaded in the controller. <p>
 * 
 * This class also implements Singleton using {@link #getInstance} to 
 * create this screen only when needed and ensure that only one instance 
 * of {@code MenuScreen} is created as only one menu screen is needed per JVM.
 */

public class MenuScreen {
	
	/** the instance of this {@code MenuScreen} */
	private static MenuScreen instance = null;
	
	/** the initial x position of the button */
	private static final int BUTTON_XPOS = 200;
	
	/** the initial y position of the button */
	private static final int BUTTON_START_YPOS = 200;
	
	/** {@code Pane} object containing all the nodes */ 
	private Pane menuPane;
	
	/** the list containing all the buttons */
	private ArrayList<MenuButton> menuButtonList;
	
	/** the animation shown on the menu screen */
	private MenuAnimation anim;
	
	
	/**
	 * This method ensures only one instance of this {@link 
	 * MenuScreen} object is created
	 * @return  this {@code MenuScreen} object
	 */
	public static MenuScreen getInstance() {
		if(instance == null) {
			instance = new MenuScreen();
		} 
		return instance;
	}
	
	
	
	/**
	 * This method returns the {@code Pane} object to be 
	 * displayed
	 * 
	 * @return the {@link #menuPane} of this {@code 
	 * MenuScreen}
	 */
	
	public Pane getPane() {
		return menuPane;
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