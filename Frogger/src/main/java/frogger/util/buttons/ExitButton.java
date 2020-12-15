package frogger.util.buttons;


import frogger.constant.FilePath;
import frogger.view.GameScreen;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


/**
 * This class defines {@code Button} object used in {@link GameScreen}
 * for leaving the game (goes back to main menu)
 *
 */
public class ExitButton extends Button {
	/** inline css style of this {@code Button} */
	private static final String BUTTON_STYLE = "-fx-background-color: transparent;" + 
											   "-fx-background-size: cover;" + 
											   "-fx-background-image: url(\"" + FilePath.BUTTON_PATH ;
	/** inline css style of this {@code Button} */
	private static final String BUTTON = BUTTON_STYLE + "exitLeft.png" + "\");";

	
	/**
	 * This public constructor initializes the preferred width, height,
	 * style and listeners of this {@code ExitButton} object
	 */
	public ExitButton () {
		setPrefWidth(60);
		setPrefHeight(70);
		setStyle(BUTTON);
		initListeners(); 
		
	}
		

	/**
	 * This method sets the preferred height and layout 
	 * for a pressed button
	 */
	private void setButtonPressedStyle() {
		setPrefHeight(68);
		setLayoutY(getLayoutY()+4);

	}
	
	/**
	 * This method sets the preferred height and layout 
	 * for a released button
	 */
	
	private void setButtonReleasedStyle() {
		setPrefHeight(60);
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
	
	
	//-------- Controller ----------------
	/**
	 * This method is the button controller, which updates the button
	 * looks directly inside button view.
	 * @param e mouse event
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
