package frogger.controller;


import frogger.Main;
import frogger.constant.EndGame;
import frogger.constant.settings.Controls;
import frogger.model.GameModel;
import frogger.model.PlayerAvatar;
import frogger.model.World;
import frogger.model.npc.Swamp;
import frogger.util.AudioPlayer;
import frogger.util.CollisionHandler;
import frogger.util.HighScoreFile;
import frogger.util.ViewLoader;
import frogger.view.GameScreen;
import frogger.view.ProgressScreen;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.List;


/**
 * This class implements the singleton method using enum.
 * Following the MVC pattern, this class acts as Controller for
 * {@link GameScreen} (View) and {@link GameModel} (Model). 
 * 
 * This class acts as a medium for the interaction of other classes
 * with the {@code GameScreen} and/or {@code GameModel}. 
 * 
 * <p>
 * 
 * This class creates a GameScreen and GameModel object. Then, this
 * class receives input/changes from external sources (classes), and 
 * informs the view ({@code GameScreen}) and/or model ({@code GameModel}) to 
 * change accordingly. 
 *  
 * <p>
 * To reduce redundancy, only a new GameModel is created for each new
 * level as it contains the NPC which varies in each level.   
 * 
 */

public enum GameController  {
	/** singleton instance of this class */
	INSTANCE;
	
	/** the {@code Stage} object created in {@link Main} */
	private static final Stage mainStage = Main.getPrimaryStage();
	
	/** the state of the game (pause/play) */
	private static boolean pause = false;
	
	/** the {@link World} loaded from {@link GameScreen} */
	private World gamePane;
	
	/** the {@link GameScreen} controlled by this {@code GameController} object */
	private GameScreen gameView;
	
	/** the {@code Scene} object created in {@link Main} */
	private Scene scene;
	
	/** the {@link GameModel} controlled by this {@code GameController} object */
	private GameModel gameModel;
	
	/**
	 * This method initializes the game properties such as 
	 * the controls chosen by user (WASD or arrow keys) and
	 * the {@link World} {@code gamePane}
	 * @param control  the key control options ({@link Controls}) 
	 * chosen by user
	 */
	public void initGame(Controls control) {

		gameView = new GameScreen(control); 
		gameModel = new GameModel();
		gamePane = gameView.getPane();
		
		
		if (scene==null) {
			initGameScene();
		}
		
		mainStage.getScene().setRoot(gamePane);
		mainStage.show();
		
	}
	
	/**
	 * This method creates and/or changes elements necessary 
	 * for each new level, then starts the game
	 */
	public void nextLevel() {
		
		gameModel.newLevel();
	    startGame();
		
	}

	/**
	 * This method (called by {@link CollisionHandler}) invokes the 
	 * GameModel object to update its properties when all five {@link
	 * PlayerAvatar} objects have reached the swamp, or the {@code PlayerAvatar}
	 * object dies and loses all its lives
	 * 
	 *
	 * @param state  {@code GameOver.NEXT} for next level,
	 * {@code GameOver.LOSE} when no lives are left
	 * 
	 * @see EndGame
	 */
	public void handleGameDone(EndGame state) {
		gameModel.handleDoneLevel(state);
		stopGame();
		showScoreDisplay();
	}
	
	/**
	 * This method calls the {@link ViewLoader} to initialize and
	 * show the score {@code Stage} pop up
	 */
	public void showScoreDisplay() {
		ViewLoader.INSTANCE.loadScore(gameModel.getLevelList(), gameModel.getScores());
	 }

	/**
	 * This method initializes the {@link ProgressScreen} and shows the
	 * user's next step in the game. To use FXML, enable the commented
	 * blocks of code and disable line 176
	 */
	private void showProgressScreen() {
		if(gameModel.getState()==EndGame.NEXT) {
			
			ProgressScreen.getInstance().setHeader("NEXT LEVEL\n\n\n\n");
			ProgressScreen.getInstance().setButtonText("START");
			ProgressScreen.getInstance().setButtonAction(e->{
				nextLevel();
				mainStage.getScene().setRoot(gamePane);
			});

//			If using FXML, enable to use progress.fxml and disable line 176
//			ViewLoader.INSTANCE.loadProgressScreen("NEXT LEVEL", "START",
//					e -> {
//						nextLevel();
//						mainStage.getScene().setRoot(gamePane);
//					});

		} else {
			String header = (gameModel.getState()==EndGame.LOSE)? "Game over :( " :
				"Congratulations, you won!";
			ProgressScreen.getInstance().setHeader(header+"\n\n\n\n");
			ProgressScreen.getInstance().setButtonText("MAIN MENU");
			ProgressScreen.getInstance().setButtonAction(e->{
				ScreenController.INSTANCE.showMenu();
				gameModel.resetGame();
				new HighScoreFile(gameModel.getScoreString());
			});

//			If using FXML, enable to use progress.fxml and disable line 176
//			ViewLoader.INSTANCE.loadProgressScreen(header, "MAIN MENU",
//					e->{
//						ScreenController.INSTANCE.showMenu();
//						gameModel.resetGame();
//						new HighScoreFile(gameModel.getScoreString());
//					});
			
		}
		//disable if using FXML
		mainStage.getScene().setRoot(ProgressScreen.getInstance().getPane());
	}
	

	
	/**
	 * This method defines the value of the {@code ButtonBase} 
	 * property onAction. This simulates an {@code EventHandler}
	 * to handle the {@code ActionEvent} which occurs whenever
	 * button is fired. This method calls the necessary methods 
	 * for "cleanup" when user exits the game screen and redirects 
	 * user back to the main menu.
	 * 
	 * @param event  ActionEvent which occurs when button is fired
	 */
	public void handleExit(ActionEvent event) {
		gameModel.resetGame();
		gamePane.getChildren().clear();
		stopGame();
		ScreenController.INSTANCE.showMenu();
		
	}
	
	 /** 
     * This method defines the value of the score {@code Stage} property 
     * {@code onHiding}. This simulates an {@code EventHandler} to handle 
     * the {@code WindowEvent} that occurs when user closes score {@code 
     * Stage}. This method updates the view by starting the next level, or
	  * returning to main menu when maximum level
	 * is reached
	 * 
     * @param event  the {@code WindowEvent} that occurs after
	 * score stage is hidden 
     */
	public void showNextScreen(WindowEvent event) {
		gamePane.getChildren().removeAll(gameModel.getList());
		Swamp.resetCtr();
		showProgressScreen();
	}

	/**
	 * This method handles the pausing and resuming of a game
	 * whenever the pause button is clicked in {@link GameScreen}
	 * 
	 * @param event  the {@code ActionEvent} that occurs when the
	 * pause button is fired
	 */
	public void handlePause(ActionEvent event) {
		if(!pause) {
			pause=true;
			stopGame();
		} else {	
			pause=false;
			startGame();
		}
	}
	
	/**
	 * This method allows other classes to manipulate the game view
	 * by adding nodes to this {@link World} object to be displayed 
	 * on the stage and seen by user
	 * 
	 * @param list  list of {@code Node} objects to be added
	 */
	public void addToView(List<Node> list) {
		
		for(Node node: list) {
			gamePane.add(node);
		}
		
	}
	
	/**
	 * This method allows other classes to manipulate the game view
	 * by removing nodes from this {@link World} object, which 
	 * subsequently removes the nodes from the user's view
	 * 
	 * @param list  list of {@code Node} objects to be removed from
	 * view
	 */
	public void removeFromView(List<Node> list) {
		for(Node node: list) {
			gamePane.remove(node);
		}
	}
	
	/**
	 * This method calls this {@link GameModel} object to play the bonus
	 * animation as the animation logic is contained inside the 
	 * model (MVC pattern). If time permits, all animation logic 
	 * will be refactored and moved into its own class.
	 * 
	 * @param bonusX  the x position of the {@link Swamp} class 
	 * where the {@link PlayerAvatar} object caught the fly.
	 */
	public void showBonus(double bonusX) {
		gameView.playBonusAnim(bonusX);

	}
	
	/** This method calls all necessary methods to start the game. */
	private void startGame() {
		AudioPlayer.INSTANCE.playMusic();
		gamePane.startMotion();
		gameModel.continueAllTimer();
	}
	
	/** This method calls all necessary methods to stop the game. */
	private void stopGame() {
		AudioPlayer.INSTANCE.stopMusic();
		gamePane.stopMotion();
		gameModel.pauseAllTimer();
	}
	

	/**
	 * This method initializes this {@code Scene} object
	 * and adds event filters which handle key press and 
	 * key release. Lambda expressions (method reference) 
	 * are used in this method to simplify it.
	 */
	
	private void initGameScene() {
		this.scene = mainStage.getScene();
		this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPress);
		this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::handleKeyRelease);
		
	}
	
	/**
	 * This method is called by the method {@link #initGameScene()} 
	 * to handle key press. This method is separate from 
	 * {@link #handleKeyRelease(KeyEvent)} to be able to use method
	 * reference in the caller method
	 * @param event  {@code KeyEvent} generated on key press
	 */
	private void handleKeyPress(KeyEvent event) {
		gamePane.handleKey(event, true);
		event.consume();
	}
	
	/**
	 * This method is called by the method {@link #initGameScene()} 
	 * to handle key release
	 * @param event  {@code KeyEvent} generated on key release
	 */
	private void handleKeyRelease(KeyEvent event) {
		gamePane.handleKey(event, false);
		event.consume();
	}
	
}