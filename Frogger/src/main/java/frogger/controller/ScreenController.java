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

	/**
	 * This method initializes the {@code Stage} with the menu screen 
	 * ({@code Pane} object from {@link MenuScreen}) and starts the 
	 * menu screen animation before showing the stage
	 */
	public void showMenu() {
		Stage stage = Main.getPrimaryStage();
		MenuScreen menu = MenuScreen.getInstance();
		
		menu.startAnim();
		stage.getScene().setRoot(menu.getPane());
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



