package frogger.controller;

import frogger.Main;
import frogger.view.MenuScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import frogger.controller.SelectionController.Controls;

/**
 * This class follows singleton method using enum 
 *
 */
public enum ScreenController {
	INSTANCE;
	private Stage stage = Main.getPrimaryStage();
	private Pane pane = new Pane();

	/**
	 * This method shows the menu screen
	 */
	public void showMenu() {
		MenuScreen menu = MenuScreen.getInstance();
		pane = menu.getPane();	
		menu.startAnim();
		stage.getScene().setRoot(pane);
		stage.show();
		
	}

	/**
	 * This method starts the game and passes on the controls
	 * to the GameController class.
	 * @param control  the key control options chosen by the user
	 */
	public void startGame(Controls control) {
		
		GameController.INSTANCE.initGame(control);
		GameController.INSTANCE.nextLevel();

	}


}



