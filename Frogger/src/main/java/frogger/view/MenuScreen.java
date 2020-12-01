package frogger.view;


import frogger.Main;
import frogger.constant.FilePath;
import frogger.controller.MenuController;
import frogger.utils.buttons.MenuButton;

//import backup.FrogSubscene;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


//singleton
//static method, private class
//TO replace VM

public class MenuScreen {
	private static MenuScreen instance = null;
	private static final int BUTTON_XPOS = 200;
	private static final int BUTTON_START_YPOS = 200;
	private Pane menuPane;
	private ArrayList<MenuButton> menuButtonList;
	private MenuButton startButton;
	private MenuButton infoButton;
	private MenuButton exitButton;
	
	public static MenuScreen getInstance() {
		if(instance == null) {
			instance = new MenuScreen();
		} 
		return instance;
	}
	
	public Pane getPane() {
		return menuPane;
	}
	
	private MenuScreen() {
		menuButtonList = new ArrayList<MenuButton>();
		initializeMenuPane();

	}
	
	
	private void initializeMenuPane() {
		menuPane = new Pane();
		menuPane.setCache(true);
		createMenuBackground();
		createButtons();
	}
	
	

	private void createButtons() {
		createStartButton();
		createInfoButton();
		createExitButton();

	}
	

	
	private void createStartButton() {
		startButton = new MenuButton("play");
		startButton.setDefaultButton(true);
		addMenuButton(startButton);
		startButton.setOnAction((e)-> {
			MenuController.INSTANCE.viewSelection();
		});

	}
	
	//methods for menu UI

	private void createInfoButton() {
		
		infoButton = new MenuButton("info");
		addMenuButton(infoButton);
		infoButton.setOnAction((e)-> {
			MenuController.INSTANCE.showInfo();

		});
		
	}

	private void createExitButton() {
		exitButton = new MenuButton("exit");
		addMenuButton(exitButton);
		exitButton.setCancelButton(true);
		exitButton.setOnAction((e)->{
			MenuController.INSTANCE.exit();
		});
		
	}

	private void createMenuBackground() {
		
		ImageView menuBackground = new ImageView(new Image(FilePath.MENUBACKGROUND,Main.WIDTH, Main.HEIGHT, false, true));
		menuPane.getChildren().add(menuBackground);

	}
	
	private void createFrog() {
		
	}


	/**
	 * This method sets the position of a menu button and add it to <code>menuButtonList</code> 
	 * X position is standardized
	 * Y position increases with the number of menu buttons 
	 */
	
	private void addMenuButton(MenuButton button) {
		button.setLayoutX(BUTTON_XPOS);
		button.setLayoutY(BUTTON_START_YPOS + menuButtonList.size() * 100);
		menuButtonList.add(button);
		menuPane.getChildren().add(button);
		
	}
	

}




