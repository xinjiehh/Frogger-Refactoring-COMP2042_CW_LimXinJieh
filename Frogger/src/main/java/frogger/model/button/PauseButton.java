package frogger.model.button;


import frogger.constant.FilePath;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * constructor initializes fixed button width, height, font, with customizable text
 * removed xPos yPos from constructor
 */
//<%=outterBoxWidth;%>

public class PauseButton extends Button implements CustomButton {

	private static final String BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-size: cover; -fx-background-image: url('file:" + FilePath.RESOURCE_PATH + "button/";
	private static final String PLAY_BUTTON = BUTTON_STYLE + "right.png" + "');";
	private static final String PAUSE_BUTTON = BUTTON_STYLE + "pause.png" + "');";
	
	public enum BUTTON {
		EXIT,
		PAUSE;
	}
	
	
	public PauseButton () {
		setPrefWidth(60);
		setPrefHeight(60);
		setStyle(PAUSE_BUTTON);
		setId("pause");
		initialiseButtonListeners(); 
		
	}
		

	
	@Override
	public void setButtonPressedStyle() {
		setPrefHeight(55);
		setLayoutY(getLayoutY()+4);
		
	}


	@Override
	public void setButtonReleasedStyle() {
		setStyle(getId() == "pause"? PLAY_BUTTON : PAUSE_BUTTON);
		setId(getId() == "pause"? "play" : "pause");
		setPrefHeight(60);
		setLayoutY(getLayoutY()-4);
		
	}


	@Override
	public void initialiseButtonListeners() {
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
	@Override
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

