package frogger.util.buttons;

import frogger.constant.FilePath;
import frogger.view.MenuScreen;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class defines {@code Button} object used in {@link MenuScreen}
 *
 */
public class MenuButton extends Button {


	/** inline css style of this {@code Button} */
	private static final String BUTTON_STYLE = 
			"-fx-background-color: transparent; " + 
			"-fx-background-image: url(\"" + FilePath.BUTTON_PATH;
	
	/** inline css style of this {@code Button} when pressed */
	private final String BUTTON_PRESSED = BUTTON_STYLE + "button_pressed.png" + "\");";
	
	/** inline css style of this {@code Button} when pressed */
	private final String BUTTON_UP = BUTTON_STYLE + "button.png" + "\");";

	/**
	 * This public constructor initializes the text, font,
	 * preferred width, height, style and listeners of this 
	 * {@code ExitButton} object
	 * 
	 * @param text  {@code String} to be displayed in this
	 * {@code Button}
	 */
	public MenuButton (String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_UP);
		initListeners(); 
		
	}
	

	/*
	 * This method loads and sets the default font for button text
	 */
	
	private void setButtonFont() {
		try {
			
			setFont(Font.loadFont(new FileInputStream(FilePath.DEFAULT_FONT), 20));

		} catch (FileNotFoundException e){
			
			setFont(Font.font("Sans Serif", 20));
			
			
		}
	}
	
	/**
	 * This method sets the style, preferred height and {@code layoutY}
	 * property for a pressed button
	 */
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED);
		setPrefHeight(45);
		setLayoutY(getLayoutY()+4);
		
	}
	
	/**
	 * This method sets the style, preferred height and {@code layoutY}
	 * property for a released button
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
	
	private void initListeners() {
		
		setOnMousePressed(this::handleMouseEvent);
		setOnMouseReleased(this::handleMouseEvent);
		setOnMouseExited(this::handleMouseEvent);
		setOnMouseEntered(this::handleMouseEvent);
	
	}

	//-------- End of view --------------
	
	
	
	//-------- Controller ----------------
	/**
	 * This method is controls the button view, which updates
	 * the button style directly inside button class.
	 * @param e  mouse event
	 */
	
	public void handleMouseEvent(MouseEvent e) {
		
		if(e.getButton().equals(MouseButton.PRIMARY) || e.getButton().equals(MouseButton.NONE)) {
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