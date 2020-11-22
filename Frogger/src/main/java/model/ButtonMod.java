package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Frogger.Main;
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
//<%=outterBoxWidth;%>
public class ButtonMod extends Button {
	private static final String BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-image: url('file:" + Main.RESOURCE_PATH;
	private static final String FONT_PATH =  Main.FONT_PATH + "joystix monospace.ttf"; //TODO new FileInputStream, no need 'file:'
	private static final String BUTTON_PRESSED = BUTTON_STYLE + "button_pressed.png" + "');";
	private static final String BUTTON_UP = BUTTON_STYLE + "button.png" + "');";

	public ButtonMod (String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_UP);
		initialiseButtonListeners(); 
		
	}
		

	/**
	 * This method sets the default font for button text
	 */
	
	private void setButtonFont() {
		try {
			
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 20));

		} catch (FileNotFoundException e){
			
			setFont(Font.font("Sans Serif", 20));
			
			
		}
	}
	/**
	 * This method sets the style for a pressed button
	 */
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED);
		setPrefHeight(45);
		setLayoutY(getLayoutY()+4);
		
	}
	
	/**
	 * This method sets the style for a released button
	 */
	
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_UP);
		setPrefHeight(49);
		setLayoutY(getLayoutY()-4);
		
	}
	
	
	/**
	 * This method registers event handlers for mouse events by defining 
	 * methods to handle mouse presence (entrance / exit) and mouse press 
	 * (pressed / released) on this object.
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
