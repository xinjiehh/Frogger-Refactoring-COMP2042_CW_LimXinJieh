package frogger.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import frogger.constant.FilePath;
import frogger.constant.settings.Controls;
import frogger.controller.GameController;
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
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class is part of the MVC pattern, acting as a view
 * for {@link GameModel}. This class is constructed like a view 
 * fxml file, which only display controls ({@link PauseButton}, {@link 
 * ExitButton}, {@code Slider} for volume) and game elements, then 
 * the {@code Pane} object is loaded in the controller. The controller 
 * {@code GameController} is called to handle user input. <p>
 * 
 * 
 * The only functional difference with a fxml view is that this 
 * class implements {@link Observer} as MVC is closely related 
 * to Observer pattern. <p>
 * 
 * 
 * For example, this class subscribes to the "life" 
 * event which is updated by the {@link GameModel} through 
 * the {@link Subject} interface. This class receives and 
 * handles the update via {@link Observer#update} method. <p>
 * 
 * 
 * This reduces the need to constantly check for updates in 
 * every frame using the {@code AnimationTimer} like in the 
 * original code.
 *
 */
public class GameScreen implements Observer {
	
	/** the amount of lives player has */
	private static final int FROG_LIFE = 3;
	
	/** the text display for level */
	private Text text = new Text();
	
	/** the life image array (life bar) shown */
	private ArrayList<Node> lifeArray = new ArrayList<Node>();
	
	/** button for user to pause/play game */
	private PauseButton pauseButton = new PauseButton();
	
	/** button for user to go back to main menu */
	private ExitButton exitButton = new ExitButton();
	
	/** bonus image that appears when frog catches a fly */
	private ImageView bonus = new ImageView();

	/** Pane object containing all the elements */
	private World gamePane;
	

	
	

	public GameScreen(Controls control) {
		gamePane = new World(control);
		createLayout();
		Subject.subscribe(this, "life","add sprite","level");
	}
	
	
	/**
	 * In this case, this method defines the actions to be taken 
	 * whenever there are updates to subscribed events, for example
	 * when the {@link PlayerAvatar} object {@code lifeProp} or 
	 * {@code hasBonus} changes (MVC pattern).
	 * 
	 * For example, if frog life is reduced, the corresponding 
	 * frog image is hidden from the life bar {@link #lifeArray}. 
	 * If frog catches a fly, {@link #bonus} is shown.
	 */
	@Override
	public void update(String eventType, Subject s) {

		switch (eventType) {
		
			case "level" -> text.setText("Level " + ((GameModel)s).getLevel());
			
			case "life" -> {
				int life = ((PlayerAvatar) s).getLife();
				if (life >= 0) {
					for (int i = 3; i > life; i--)
						lifeArray.get(i - 1).setVisible(false);
				}
			}
			
			case "add sprite" -> {
				List<Node> list = ((GameModel)s).getList();
				gamePane.addAll(list);
			}
			
			case "remove sprite" -> {
				List<Node> list = ((GameModel)s).getList();
				gamePane.getChildren().removeAll(list);
			}
			
			
		}
	}
	
	/**
	 * Public method that allows access to this {@link World} 
	 * object 
	 * 
	 * @return {@code World} object of this {@code 
	 * GameScreen} object
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
		Slider slider = new Slider(0, 1, AudioPlayer.INSTANCE.getVolume());
		slider.setLayoutX(300);
		slider.setLayoutY(75);
		
		//add change listener
	    slider.valueProperty().addListener((obs,oldVal,newVal) -> AudioPlayer.INSTANCE.setVolume((double)newVal));
	    
	    gamePane.add(slider);
	}
	
	/**
	 * This method shows the bonus image above the {@link Swamp} 
	 * object where the {@link PlayerAvatar} object touches a fly.
	 * It is shown for 1000 milliseconds before being hidden until
	 * a fly is caught again. 
	 * 
	 * @param bonusX  the x-position of the {@code Swamp} where
	 * the {@code PlayerAvatar} caught the fly
	 *
	 * @see BonusAnimation
	 */
	public void playBonusAnim(double bonusX) {
		/** bonus animation to show and hide bonus image */
		bonus.setX(bonusX);
		BonusAnimation bonusAnim = new BonusAnimation(bonus);
		bonusAnim.play();
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

