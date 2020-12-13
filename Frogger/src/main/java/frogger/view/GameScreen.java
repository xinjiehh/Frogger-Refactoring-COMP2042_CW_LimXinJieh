package frogger.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import frogger.constant.FilePath;
import frogger.controller.GameController;
import frogger.controller.SelectionController.Controls;
import frogger.model.Background;
import frogger.model.GameModel;
import frogger.model.Observer;
import frogger.model.PlayerAvatar;
import frogger.model.Subject;
import frogger.model.World;
import frogger.model.npc.Swamp;
import frogger.util.AudioPlayer;
import frogger.util.animation.BonusAnimation;
import frogger.util.buttons.ExitButton;
import frogger.util.buttons.PauseButton;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class acts as view to the corresponding
 * {@link GameController}, displaying objects such as 
 * controls ({@link PauseButton}, {@link ExitButton}, 
 * {@code Slider} for volume) and game elements ({@code 
 * Text} for level.
 * <p>
 * This class is part of the MVC pattern, acting as a view
 * for {@link GameModel}. As MVC pattern is closely related 
 * to Observer pattern, this class also implements Observer 
 * pattern. This class subscribes to the "life" event which
 * is updated by the {@link GameModel} through the {@link 
 * Subject} interface. This class receives and handles the 
 * update via {@link Observer#update()} method. This reduces
 * the need to constantly check for updates in every frame
 * using the {@code AnimationTimer} like in the original
 * code.
 *
 */
public class GameScreen implements Observer {
	
	private static final int FROG_LIFE = 3;
	private Text text = new Text();
	private Alert alert = new Alert(AlertType.INFORMATION); 
	private ArrayList<Node> lifeArray = new ArrayList<Node>();
	private PauseButton pauseButton = new PauseButton();
	private ImageView bonus = new ImageView();
	private ExitButton exitButton = new ExitButton();
	private Slider slider;
	private World gamePane;
	private BonusAnimation bonusAnim;
	
	

	public GameScreen(Controls control) {
		gamePane = new World(control);
		createLayout();
		Subject.subscribe("life", this);
		Subject.subscribe("sprite", this);
		Subject.subscribe("score", this);
	
	}
	
	/**
	 * Public method that allows access to dialog box
	 * @return an Alert dialog box
	 */
	public Alert getAlert() {
		return alert;
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
		bonus.setImage(new Image(FilePath.BONUS, 50,50, true,true));
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
		for(int i=0; i<FROG_LIFE; i++) {
			
			ImageView img = new ImageView(new Image(FilePath.FROG_UP, size, size, true, true));
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
			
			text.setFont(Font.loadFont(new FileInputStream(FilePath.DEFAULT_FONT),45));
			
		} catch (FileNotFoundException e) {
			
			System.out.println("Error loading font for level text");
			e.printStackTrace();
		}
		
		text.setX(205);
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
		
		Background background = new Background(FilePath.GAMEBACKGROUND);
		//ColorAdjust colorAdjust = new ColorAdjust();
		//colorAdjust.setContrast(1);     
	    //bg.setEffect(colorAdjust); 
	    gamePane.add(background);
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
	    slider.valueProperty().addListener((obs,oldVal,newVal) -> AudioPlayer.INSTANCE.setVolume((double)newVal));
	    
	    gamePane.add(slider);
	}
	
	/**
	 * This method shows the bonus image above the {@link Swamp} 
	 * object where the {@link PlayerAvatar} object touches a fly.
	 * It is shown for 1000 miliseconds before being hidden until
	 * a fly is caught again. 
	 * @param bonusX  the x-position of the {@code Swamp} where
	 * the {@code PlayerAvatar} caught the fly
	 */
	public void playBonusAnim(double bonusX) {
		bonus.setX(bonusX);
		bonusAnim = new BonusAnimation(bonus);
		bonusAnim.play();
	}
	
	/**
	 * In this case, this method defines the actions to be taken 
	 * whenever the {@link PlayerAvatar} object {@code lifeProp} 
	 * or {@code hasBonus} changes. (MVC pattern)
	 * 
	 * For example, if frog life is reduced, the corresponding 
	 * frog image is hidden from the life bar {@link #lifeArray}. 
	 * If frog catches a fly, {@link #bonus} is shown.
	 */
	@Override
	public void update(String eventType, Subject s) {
		
		if(eventType=="life") {
			int life = ((PlayerAvatar)s).getLife();
			if(life>=0) {
				for (int i = 3; i > life; i--) 
					lifeArray.get(i-1).setVisible(false);
			}
			
		} else if (eventType=="sprite") {
			List<Node> list = ((GameModel)s).getList();
			gamePane.addAll(list);
			
		} 
	}


}

//else if (eventType=="bonus") {
////boolean bool = ((GameModel)s).getHasBonus();
////bonus.setVisible(bool);
//bonusAnim.play();
//}

///**
//* Public method that allows access to bonus image
//* @return the bonus ImageView
//*/
//
//public ImageView getBonus() {
//	return bonus;
//}
//

