package frogger.utils.buttons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import frogger.constant.FilePath;
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
public class MenuButton extends Button {
	private static final String FONT_PATH =  FilePath.DEFAULT_FONT; 
	
	private static final String BUTTON_STYLE = 
			"-fx-background-color: transparent; " + 
			"-fx-background-image: url(\"" + FilePath.BUTTON_PATH;
	
	
	private String BUTTON_PRESSED = BUTTON_STYLE + "button_pressed.png" + "\");";
	private String BUTTON_UP = BUTTON_STYLE + "button.png" + "\");";

	
	public MenuButton (String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_UP);
		initialiseButtonListeners(); 
		
	}
	
	//test
	public MenuButton () {
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
		
		setOnMousePressed(this::handleMouseEvent);
		setOnMouseReleased(this::handleMouseEvent);
		setOnMouseExited(this::handleMouseEvent);
		setOnMouseEntered(this::handleMouseEvent);
	
	}

	//-------- End of view --------------
	
	
	
	//-------- Controller ----------------
	/**
	 * This method is the button controller, which updates the button
	 * looks directly inside button view.
	 * @param e mouse event
	 */
	
	public void handleMouseEvent(MouseEvent e) {
		
		if(e.getButton().equals(MouseButton.PRIMARY) || e.getButton().equals(MouseButton.NONE)) {
			System.out.println(e.getEventType());
			switch(e.getEventType().toString()) {
			case "MOUSE_PRESSED":
				setButtonPressedStyle();
				break;
			case "MOUSE_RELEASED":
				setButtonReleasedStyle();
				break;
			case "MOUSE_ENTERED":
				setEffect(new DropShadow());
				break;
			case "MOUSE_EXITED":
				setEffect(null);
				break;
			default:
				break;

			}
			
		}
	}


}