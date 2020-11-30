package frogger.model.button;


import frogger.constant.FilePath;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;

/**
 * constructor initializes fixed button width, height, font, with customizable text
 * removed xPos yPos from constructor
 */
//<%=outterBoxWidth;%>

public class ExitButton extends Button {

	private static final String BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-size: cover; -fx-background-image: url('file:" + FilePath.RESOURCE_PATH + "button/";
	private static final String BUTTON = BUTTON_STYLE + "exitLeft.png" + "');";

	
	public ExitButton () {
		setPrefWidth(60);
		setPrefHeight(70);
		setStyle(BUTTON);
		initialiseButtonListeners(); 
		
	}
		

	/**
	 * This method sets the style for a pressed button
	 */
	private void setButtonPressedStyle() {
		setPrefHeight(68);
		setLayoutY(getLayoutY()+4);

	}
	
	/**
	 * This method sets the style for a released button
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

	private void initialiseButtonListeners() {

		setOnMousePressed((e)->{

			if(e.getButton().equals(MouseButton.PRIMARY)) 
					setButtonPressedStyle();	

		});
		
//		Exception in thread "JavaFX Application Thread" java.lang.NullPointerException: Cannot invoke "javafx.scene.Scene.setOnKeyReleased(javafx.event.EventHandler)" because "newValue" is null
//		at frogger.World.createNewListeners(World.java:44)
//		at frogger.World.lambda$new$0(World.java:37)
//	
		setOnMouseEntered((e) -> {setEffect(new DropShadow());});
		
		setOnMouseExited((e) -> {setEffect(null);});
	
	}

}
