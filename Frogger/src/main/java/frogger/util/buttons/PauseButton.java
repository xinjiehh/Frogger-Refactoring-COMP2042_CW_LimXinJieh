package frogger.util.buttons;


import frogger.constant.FilePath;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * This class defines {@code Button} object used in {@link GameScreen}
 * for pausing/playing the game
 *
 */
public class PauseButton extends Button {

	private static final String BUTTON_STYLE = "-fx-background-color: transparent;" + 
			   "-fx-background-size: cover;" + 
			   "-fx-background-image: url(\"" + FilePath.BUTTON_PATH ;
	
	private static final String PLAY_BUTTON = BUTTON_STYLE + "right.png" + "\");";
	private static final String PAUSE_BUTTON = BUTTON_STYLE + "pause.png" + "\");";
	
	/**
	 * This public constructor initalizes the id, preferred width, height,
	 * style and listeners of this {@code ExitButton} object
	 */
	public PauseButton () {
		setPrefWidth(60);
		setPrefHeight(60);
		setStyle(PAUSE_BUTTON);
		setId("pause");
		initListeners(); 
		
	}
		

	
	/**
	 * This method sets the preferred height and layout 
	 * for a pressed button
	 */
	public void setButtonPressedStyle() {
		setPrefHeight(55);
		setLayoutY(getLayoutY()+4);
		
	}

	/**
	 * This method switches the style and id of this {@code Button}
	 * object according to its previous id (pause/play), and sets the 
	 * preferred height and layout for a released button
	 */
	public void setButtonReleasedStyle() {
		setStyle(getId() == "pause"? PLAY_BUTTON : PAUSE_BUTTON);
		setId(getId() == "pause"? "play" : "pause");
		setPrefHeight(60);
		setLayoutY(getLayoutY()-4);
		
	}

	/**
	 * This method registers event handlers for mouse events by defining 
	 * methods to handle mouse presence (entrance / exit) and mouse press 
	 * (pressed / released) on this object.
	 */
	public void initListeners() {
		setOnMousePressed(this::handleMouseEvent);
		setOnMouseReleased(this::handleMouseEvent);
		setOnMouseExited(this::handleMouseEvent);
		setOnMouseEntered(this::handleMouseEvent);
		
	}

	/**
	 * This method registers event handlers for mouse events by defining 
	 * methods to handle mouse presence (entrance / exit) and mouse press 
	 * (pressed / released) on this object.
	 * @param e mouse event to be handled
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

//
//private void initialiseButtonListeners() {
//
//	setOnMousePressed(new EventHandler<MouseEvent>() {
//		
//		@Override
//		public void handle(MouseEvent event) {
//			if(event.getButton().equals(MouseButton.PRIMARY)) {
//				setButtonPressedStyle();	
//			}
//		}
//	});
//	
//	setOnMouseReleased(new EventHandler<MouseEvent>() {
//		
//		@Override
//		public void handle(MouseEvent event) {
//			if(event.getButton().equals(MouseButton.PRIMARY)) {
//				
//				setButtonReleasedStyle();
//				
//			}
//			
//		}
//		
//	});
//
//	setOnMouseEntered(new EventHandler<MouseEvent>() {
//
//		@Override
//		public void handle(MouseEvent event) {
//			setEffect(new DropShadow());
//			
//		}
//		
//	});
//	
//	setOnMouseExited(new EventHandler<MouseEvent>() {
//
//		@Override
//		public void handle(MouseEvent event) {
//			setEffect(null);
//			
//		}
//		
//	});
//
//}

