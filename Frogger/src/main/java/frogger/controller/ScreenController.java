package frogger.controller;

import frogger.Main;
import frogger.controller.SelectionController.Controls;
import frogger.view.MenuScreen;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * This class is the entry class from {@link Main}, responsible
 * for switching between different views on the main stage  
 * This class follows singleton method using enum. 
 */
public enum ScreenController {
	INSTANCE;
	
	private HashMap<String,Pane> screenMap = new HashMap<>();
	private final Stage stage = Main.getPrimaryStage();
	private final Scene scene = stage.getScene();
	
	
	/**
	 * This method allows to easily add new views to be displayed
	 * on the main stage
	 * @param id  {@code String} to act as screen identifier
	 * @param pane  {@code Pane} object of the screen
	 */
	public void addScreen(String id, Pane pane) {
		screenMap.put(id, pane);

	}
	

	/**
	 * This method allows to easily remove views from the stage
	 * @param name  {@code String} to act as screen id
	 */
	public void removeScreen(String name) {
		screenMap.remove(name);
		
	}
	
	/**
	 * This method allows easy switching of the view 
	 * using the identifier {@code String} to get the
	 * corresponding {@code Pane}. The {@code Pane} is 
	 * set as the root of this {@link #scene} object 
	 * 
	 * @param name  {@code String} identifier of the {@code Pane}
	 * to be displayed
	 */
	public void activate(String name) {
		scene.setRoot(screenMap.get(name));
	}
	
	/**
	 * This method initializes the {@code Stage} with the menu screen 
	 * ({@code Pane} object from {@link MenuScreen}) and starts the 
	 * menu screen animation before showing the stage
	 */
	public void showMenu() {
		MenuScreen menu = MenuScreen.getInstance();
		scene.setRoot(menu.getPane());
		stage.show();	
	}



	/**
	 * This method starts the game and passes on the controls
	 * to the {@link GameController}.
	 * @param control  the key control options chosen by the user
	 */
	public void startGame(Controls control) {
		
		GameController.INSTANCE.initGame(control);
		GameController.INSTANCE.nextLevel();

	}


}



