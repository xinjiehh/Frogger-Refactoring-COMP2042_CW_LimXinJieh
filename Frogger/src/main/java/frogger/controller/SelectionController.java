package frogger.controller;

import frogger.constant.FilePath;
import frogger.constant.settings.Controls;
import frogger.constant.settings.Mode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	
	/**
	 * This method initializes the default font, game mode ({@link Mode#SINGLE})
	 * and key controls ({@link Controls#WASD})
	 */
	@FXML
	public void initialize() {
		initFont();
		mode.setUserData(Mode.SINGLE);
		controls.setUserData(Controls.WASD);
	}
	/**
	 * This method initializes the font to be used in the related fxml file
     */
	private void initFont() {
		Font font;
		try {font = Font.loadFont(new FileInputStream(FilePath.DEFAULT_FONT), 20);} 
		catch (FileNotFoundException e){
			System.out.println("Error loading font for selection");
			font = Font.font("Sans Serif", 20);
		}
	
		mode.setFont(font);
		controls.setFont(font);
		start.setFont(font);
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
	}



}
