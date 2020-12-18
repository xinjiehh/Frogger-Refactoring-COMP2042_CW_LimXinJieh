package frogger.controller;

import frogger.constant.FilePath;
import frogger.constant.GameData;
import frogger.constant.settings.Controls;
import frogger.constant.settings.Mode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Following MVC pattern, this class is the controller for the selection view 
 * FXML file. This allows user to select key controls (arrow keys or WASD) and 
 * game mode (to be implemented). When user starts game the controls is passed 
 * to {@link ScreenController}.
 */

public class SelectionController {
	/** the button to select game mode options */
	@FXML private Button mode;
	
	/** the button to select key control options */ 
	@FXML private Button controls;
	
	/** the button to start game */ 
	@FXML private Button start;

	/** text field to enter player's name */
	@FXML private TextField username;

	/**
	 * This method initializes the default font, game mode ({@link Mode#SINGLE})
	 * and key controls ({@link Controls#WASD})
	 */
	@FXML
	public void initialize() {
		initFont();
		initNameListener();
		mode.setUserData(Mode.SINGLE);
		controls.setUserData(Controls.WASD);
	}

	private void initNameListener() {
		username.textProperty().addListener((obs, oldV, newV) -> {
			String temp = newV;
			//only allow letters
			temp = temp.replaceAll("[^a-zA-Z]", "");
			username.setText(temp);
		});
	}
	/**
	 * This method initializes the font to be used in the related fxml file
     */
	private void initFont() {
		try {
			Font.loadFont(new FileInputStream(FilePath.DEFAULT_FONT), 20);}
		catch (FileNotFoundException e){
			System.out.println("Error loading font for selection");
		}

	}


	/**
	 * This method allows users to switch between different playing 
	 * modes (to be implemented)
	 */
	@FXML
	public void switchMode() {
	}

  
	/**
	 * This method is responsible for updating the view and data 
	 * when users select their key controls
	 */
	@FXML
	public void switchControls() {
		switch ((Controls) controls.getUserData()) {
			case WASD -> {
				controls.setUserData(Controls.ARROW);
				controls.setText("< ARROW KEYS >");
			}	
			case ARROW -> {
				controls.setUserData(Controls.WASD);
				controls.setText("<    WASD    >");
			}
		}
	  
  }

	/**
	 * This method starts the game by calling {@link 
	 * ScreenController} and passing the selected 
	 * {@link Controls} to {@code ScreenController}
	 */
	@FXML
	public void startGame() {

		ScreenController.INSTANCE.startGame((Controls) controls.getUserData());
		GameData.INSTANCE.setUsername(username.getText());
	}

	/**
	 * This method handles the {@code MouseEvent} on the button
	 * @param mouseEvent  {@code MouseEvent} that fired on the button
	 */
    public void handleMouseEvent(MouseEvent mouseEvent) {
		switch(mouseEvent.getEventType().toString()) {
			case "MOUSE_ENTERED":
				start.setEffect(new DropShadow());
				break;
			case "MOUSE_EXITED":
				start.setEffect(null);
				break;
			default:
				break;

		}
    }
}
