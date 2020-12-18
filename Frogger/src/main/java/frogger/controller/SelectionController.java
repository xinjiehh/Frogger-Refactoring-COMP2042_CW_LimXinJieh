package frogger.controller;

import frogger.constant.FilePath;
import frogger.constant.settings.Controls;
import frogger.constant.settings.GameData;
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

	/** the button to select key control options */ 
	@FXML private Button controls;
	
	/** the button to start game */ 
	@FXML private Button startButton;

	/** text field to enter player's name */
	@FXML private TextField username;

	/** inline css style of this {@code Button} */
	private static final String PREFIX =
			"-fx-background-image: url(\"" + FilePath.BUTTON_PATH;


	/**
	 * This method initializes the default font, game mode ({@link Mode#SINGLE})
	 * and key controls ({@link Controls#WASD})
	 */
	@FXML
	public void initialize() {
		initFont();
		initNameListener();
		controls.setUserData(Controls.WASD);
	}

	private void initNameListener() {
		username.textProperty().addListener((obs, oldV, newV) -> {
			String temp = newV;
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
	 * This method handles the {@code MouseEvent} on the button
	 * @param mouseEvent  {@code MouseEvent} that fired on the button
	 */
	@FXML
    public void handleMouseEvent(MouseEvent mouseEvent) {
		switch(mouseEvent.getEventType().toString()) {
			case "MOUSE_PRESSED":
				setButtonPressedStyle();
				break;
			case "MOUSE_RELEASED":
				setButtonReleasedStyle();
				break;
			case "MOUSE_ENTERED":
				startButton.setEffect(new DropShadow());
				break;
			case "MOUSE_EXITED":
				startButton.setEffect(null);
				break;
			default:
				break;

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
	 * This method sets the style, preferred height and {@code layoutY}
	 * property for a pressed button
	 */
	private void setButtonPressedStyle() {
		//inline css style of this {@code Button} when pressed
		String BUTTON_PRESSED = PREFIX + "button_pressed.png\");";
		startButton.setStyle(BUTTON_PRESSED);
		startButton.setPrefHeight(45);
		startButton.setLayoutY(startButton.getLayoutY()+4);

	}


	/**
	 * This method sets the style, preferred height and {@code layoutY}
	 * property for a released button
	 */

	private void setButtonReleasedStyle() {
		//inline css style of this {@code Button} when pressed
		String BUTTON_UP = PREFIX + "button.png" + "\");";
		startButton.setStyle(BUTTON_UP);
		startButton.setPrefHeight(49);
		startButton.setLayoutY(startButton.getLayoutY()-4);

	}
}
