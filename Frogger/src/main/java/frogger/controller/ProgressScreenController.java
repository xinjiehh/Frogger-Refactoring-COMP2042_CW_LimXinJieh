package frogger.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import frogger.constant.FilePath;
import frogger.constant.settings.Controls;
import frogger.constant.settings.Mode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Following MVC pattern, this class is the controller for the progress view
 * FXML file.
 */

public class ProgressScreenController {
	
	/** the button to select key control options */ 
	@FXML private Text header;
	
	/** the button to start game */ 
	@FXML private Button start;

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
	}

	/**
	 * This method sets the progress message to be displayed
	 * @param str  {@code String} message to be displayed
	 */
	public void setText(String str) {
		header.setText(str);
	}

	/**
	 * This method sets the property {@code onAction} of the button
	 * @param x  {@code EventHandler} to be executed when button is pressed or
	 * fired
	 */
	public void setButtonAction(EventHandler<ActionEvent> x) {
		start.setOnAction(x);
		
	}

	/**
	 * This method sets the placeholder for this {@link Button}
	 * object
	 * @param buttonText  {@code String} placeholder for button
	 */
	public void setButtonText(String buttonText) {
		start.setText(buttonText);
	}

	/**
	 * This method handles key press on this screen
	 * @param keyEvent  {@code KeyEvent} that fires
	 */
	@FXML
	public void handleOnKeyPressed(KeyEvent keyEvent) {
		start.fire();
	}


	/**
	 * This method is used to handle the mouse event on the button
	 * @param mouseEvent  {@code MouseEvent} fired on the button
	 */
	@FXML
	public void handleMouseEvent(MouseEvent mouseEvent) {

		if(mouseEvent.getButton().equals(MouseButton.PRIMARY) || mouseEvent.getButton().equals(MouseButton.NONE)) {
			switch(mouseEvent.getEventType().toString()) {
				case "MOUSE_PRESSED":
					setButtonPressedStyle();
					break;
				case "MOUSE_RELEASED":
					setButtonReleasedStyle();
					break;
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
		header.setFont(font);
		start.setFont(font);
	}


	/**
	 * This method sets the style, preferred height and {@code layoutY}
	 * property for a pressed button
	 */
	private void setButtonPressedStyle() {
		//inline css style of this {@code Button} when pressed
		String BUTTON_PRESSED = PREFIX + "button_pressed.png\");";
		start.setStyle(BUTTON_PRESSED);
		start.setPrefHeight(45);
		start.setLayoutY(start.getLayoutY()+4);

	}

	/**
	 * This method sets the style, preferred height and {@code layoutY}
	 * property for a released button
	 */

	private void setButtonReleasedStyle() {
		//inline css style of this {@code Button} when pressed
		String BUTTON_UP = PREFIX + "button.png" + "\");";
		start.setStyle(BUTTON_UP);
		start.setPrefHeight(49);
		start.setLayoutY(start.getLayoutY()-4);

	}

}
