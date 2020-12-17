package frogger.view;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import frogger.Main;
import frogger.constant.FilePath;
import frogger.model.Background;
import frogger.util.buttons.MenuButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Following MVC pattern, this class is constructed like a view fxml 
 * file, whereby it only contains code for display and calls {@code 
 * Controller} to handle user input <p>
 * 
 * This class also implements Singleton using {@link #getInstance} to 
 * create this screen only when needed and ensure that only one instance 
 * of {@code ProgressScreen} is created as only one progress screen is 
 * needed per JVM.
 */

public class ProgressScreen {
	
	/** the instance of this {@code ProgressScreen} */
	private static ProgressScreen instance = null;
	
	/** {@code StackPane} object containing all the nodes */ 
	private StackPane progressPane;
	
	/** button to show the next screen */
	private MenuButton button;
	
	/** header text to be displayed */
	private Text header;

	private Text body;
	
	/**
	 * This method ensures only one instance of this {@link 
	 * ProgressScreen} object is created
	 * @return  this {@code MenuScreen} object
	 */
	public static ProgressScreen getInstance() {
		if(instance == null) {
			instance = new ProgressScreen();
		} 
		return instance;
	}


	/**
	 * This method returns the {@code StackPane} object to be 
	 * displayed
	 * 
	 * @return the {@link #progressPane} of {@code ProgressScreen}
	 */
	
	public Pane getPane() {
		return progressPane;
	}
	
	
	/**
	 * This private constructor initializes {@link #progressPane}
	 */
	private ProgressScreen() {
		
		initializePane();
		initText();
		createButton();
		Main.getPrimaryStage().getScene().addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPress);
		
	}

	/**
	 * This method sets the placeholder for this {@link Button}
	 * object
	 * @param str  {@code String} placeholder for button
	 */
	public void setButtonText(String str) {
		button.setText(str);
	}

	/**
	 * This method sets the progress message to be displayed
	 * @param str  {@code String} message to be displayed
	 */
	public void setHeader(String str) {
		header.setText(str);
		
	}

	/**
	 * This method initializes the style of the {@code Text} objects
	 * to be displayed on screen
	 */
	private void initText() {
		header = new Text();
		body = new Text();
		try {

			header.setFont(Font.loadFont(new FileInputStream(FilePath.DEFAULT_FONT),25));
			body.setFont(Font.loadFont(new FileInputStream(FilePath.DEFAULT_FONT),15));

		} catch (FileNotFoundException e) {

			System.out.println("Error loading font");
			e.printStackTrace();
		}

		header.setFill(Color.LIMEGREEN);
		body.setFill(Color.WHITE);
		body.setText("\n\n\n\n\n\nor press any key to continue");
		progressPane.getChildren().add(body);
		progressPane.getChildren().add(header);


	}

	/**
	 * This method initializes the {@code Pane}
	 * object and the elements to be displayed
	 * such as the background and buttons
	 */
	private void initializePane() {
		progressPane = new StackPane();
		progressPane.setCache(true);
		createMenuBackground();
	}
	

	/**
	 * This method creates the button shown on screen
	 */
	private void createButton() {
		button = new MenuButton("");
		progressPane.getChildren().add(button);
	}


	/**
	 * This method sets the property {@code onAction} of the button
	 * @param x  {@code EventHandler} to be executed when button is pressed or
	 * fired
	 */
	public void setButtonAction(EventHandler<ActionEvent> x) {
		button.setOnAction(x);
		
	}

	/**
	 * This method handles key press on this screen
	 * @param event  {@code KeyEvent} that fires
	 */
	private void handleKeyPress(KeyEvent event) {
		if(Main.getPrimaryStage().getScene().getRoot()== progressPane)
			button.fire();
	}
	
	
	/**
	 * This method creates and adds an {@code ImageView}
	 * that acts as background
	 */
	private void createMenuBackground() {
		Background background = new Background(FilePath.MENUBACKGROUND);
		progressPane.getChildren().add(background);

	}
	
	
	

}