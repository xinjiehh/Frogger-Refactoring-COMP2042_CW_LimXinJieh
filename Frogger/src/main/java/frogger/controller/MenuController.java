package frogger.controller;

import frogger.util.ViewLoader;
import frogger.view.MenuScreen;
import javafx.application.Platform;

/**
 * This enum class implements singleton method. It acts as controller
 * for {@link MenuScreen} (View) which handles the different actions of the
 * menu buttons such as showing selection screen, showing info screen
 * and exiting the application
 * 
 */
public enum MenuController {
	
	INSTANCE;
	

	/**
	 * This method loads and shows the selection page from FXML for 
	 * users to choose their controls
	 */
	public void viewSelection() {
		ViewLoader.INSTANCE.loadSelection();

	}
	
	/**
	 * This method loads and shows the info page from FXML which has 
	 * a brief overview of the game
	 */
	public void showInfo() {
		ViewLoader.INSTANCE.loadInfo();
	}
	
	/**
	 * This method handles the exiting of the application
	 */
	public void exit() {
		Platform.exit();
		//https://stackoverflow.com/questions/26619566/javafx-stage-close-handler
	}

	

}
