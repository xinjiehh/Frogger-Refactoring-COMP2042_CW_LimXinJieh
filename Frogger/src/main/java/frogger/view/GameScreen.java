package frogger.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import frogger.Main;
import frogger.constant.FilePath;
import frogger.model.World;
import frogger.utils.AudioPlayer;
import frogger.utils.buttons.ExitButton;
import frogger.utils.buttons.PauseButton;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;import frogger.controller.GameController;
import frogger.controller.SelectionController.Controls;


public class GameScreen {
	
	private int frogLife = 3;
	protected static final String FONT_PATH =  FilePath.DEFAULT_FONT;
	private Text text = new Text();
	private Alert alert = new Alert(AlertType.INFORMATION); 
	private ArrayList<Node> lifeArray = new ArrayList<Node>();
	private PauseButton pauseButton = new PauseButton();
	private ImageView bonus = new ImageView();
	private ImageView bg = new ImageView();
	private ExitButton exitButton = new ExitButton();
	private Slider slider;
	private World gamePane;
	
	
	

	public GameScreen(Controls control) {
		
		gamePane = new World(control);
		createLayout();
	}
	
	/**
	 * Public method that allows access to dialog box
	 * @return an Alert dialog box
	 */
	public Alert getAlert() {
		return alert;
	}
	
	/**
	 * Public method that allows access to bonus image
	 * @return the bonus ImageView
	 */
	
	public ImageView getBonus() {
		return bonus;
	}
	

	/**
	 * Public method that allows access to frog life array
	 * @return ArrayList<Node> of frog
	 */
	public ArrayList<Node> getLifeArray() {
		return lifeArray;
	}
	
	/**
	 * Public method that sets level text shown on screen
	 * @param i current level number
	 */
	public void setLevelText(int i) {
		text.setText("Level " + i);
	}
	
	/**
	 * Public method that allows access to World so elements
	 * @return World
	 */
	public World getPane() {
		return gamePane;
	}
	
	/**
	 * This method creates basic game UI such as background, volume slider
	 * and level text
	 */
	private void createLayout() {
		initBackground();
		initVolumeSlider();
		initTextStyle();
		initLife();
		initPauseButton();
		initExitButton(); 
		initAlert();
		initBonus();
	}
	
	/**
	 * This method initializes the bonus ImageView that appears whenever a frog
	 * 'catches' a fly, i.e. the frog makes contact with a swamp that contains 
	 * a fly
	 */
	private void initBonus() {
		bonus.setImage(new Image("100.png", 50,50, true,true));
		bonus.setY(50);
		bonus.setVisible(false);
		gamePane.add(bonus);
	}

	/**
	 * This method initializes the event handler for Alert box when it is closed 
	 */
	private void initAlert() {
		alert.setOnHiding(GameController.INSTANCE::updateView);
		
	}

	/**
	 * This method initializes the array of ImageView to be displayed on the
	 * screen to indicate the frog life count
	 */
	private void initLife() {
		int size = 35;
		for(int i=0; i<frogLife; i++) {
			
			ImageView img = new ImageView(new Image("/frog/FroggerUp.png", size, size, true, true));
			img.setX(550-i*size);
			img.setY(50);
			lifeArray.add(img);
			gamePane.add(img);
		}
		
	}
	
	/**
	 * This method initializes the title for each level
	 * that is displayed on the game screen
	 */
	
	private void initTextStyle() {
		
		text = new Text();
		try {
			
			text.setFont(Font.loadFont(new FileInputStream(FONT_PATH),45));
			
		} catch (FileNotFoundException e) {
			
			System.out.println("Error loading font for level text");
			e.printStackTrace();
		}
		
		text.setX(100);
		text.setY(60);
		//Setting the color 
		text.setFill(Color.LIMEGREEN); 
		        
		//Setting the Stroke  
		text.setStrokeWidth(1.5); 
		       
		//Setting the stroke color 
		text.setStroke(Color.PURPLE); 
		gamePane.add(text);
		
	}


	
	/**
	 * This method initializes the background. Color adjust has been removed
	 * as color corrected image has been included. However, color adjust is
	 * kept as comment in case lecturer requires demonstration of use.
	 */
	
	private void initBackground() {

		bg.setImage(new Image(FilePath.GAMEBACKGROUND, Main.WIDTH, Main.HEIGHT, false, true));
		//ColorAdjust colorAdjust = new ColorAdjust();
		//colorAdjust.setContrast(1);     
	    //bg.setEffect(colorAdjust); 
	    gamePane.add(bg);
	}

	/**
	 * This method initializes the x position, y position and event handler
	 * for the pause button, then adds it to pane
	 */
	private void initPauseButton() {
		pauseButton.setLayoutX(70);
		pauseButton.setLayoutY(14);
		pauseButton.setOnAction(GameController.INSTANCE::handlePause);
		gamePane.add(pauseButton);
	}
	/**
	 * This method initializes the x position, y position and event handler
	 * for the exit button, then adds it to pane.
	 */
	private void initExitButton() {
		exitButton.setLayoutX(5);
		exitButton.setLayoutY(5);
		exitButton.setOnAction(GameController.INSTANCE::handleExit);
		gamePane.add(exitButton);
	}

	/**
	 * This method initializes the x position, y position and initial slider
	 * position for the volume slider, and add change listener to volume
	 * slider to update game volume, then adds it to pane
	 */
	private void initVolumeSlider() {
		slider = new Slider(0, 1, AudioPlayer.INSTANCE.getVolume());
		slider.setLayoutX(300);
		slider.setLayoutY(75);
		
		//add change listener
	    slider.valueProperty().addListener((obs,oldVal,newVal) -> {
	        AudioPlayer.INSTANCE.setVolume((double)newVal);
	    });
	  
	    gamePane.add(slider);
	}
	
	private void createVolumeButton() {
		
	}
	
	


}
