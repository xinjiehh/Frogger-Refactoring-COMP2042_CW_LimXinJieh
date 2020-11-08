package Frogger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/**
 * constructor initializes fixed button width, height, font, with customizable text
 * removed xPos yPos from constructor
 */

public class ButtonMod extends Button {

	private final String FONT_PATH = "src/main/resources/kenvector_future.ttf"; //TODO new FileInputStream, no need 'file:'
	private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('file:src/main/resources/button_pressed.png');";
	private final String BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-image: url('file:src/main/resources/button.png');";

	
	
	public ButtonMod (String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_STYLE);
		initialiseButtonListeners(); 
		
	}
	
	
	private void setButtonFont() {
		try {
			
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));

		} catch (FileNotFoundException e){
			
			setFont(Font.font("Sans Serif", 23));
			
			
		}
	}
	
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY()+4);
		
	}
	
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY()-4);
		
	}
	
	
	/**
	 * onMouseReleased, Pressed, Entered, Exited
	 */
	
	private void initialiseButtonListeners() {
		
		setOnMousePressed(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();	
				}
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					
					setButtonReleasedStyle();
					
				}
				
			}
			
		});
	
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
				
			}
			
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
				
			}
			
		});
	
	}

}
