package frogger.controller;


/* Model holds persistent data
 * Get, set, manipulate data here
 * Main pane TODO getURL path
 */
/*
 * view model observer relationship
 * view registers to model 
 * model calls back to view when it has changed
 * view redisplay
 * 
 */

import frogger.controller.SelectionController.Controls;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import frogger.model.Frog;
import frogger.model.GameModel;
import frogger.model.World;
import frogger.model.npc.Swamp;
import frogger.util.AudioPlayer;
import frogger.util.HighScoreFile;
import frogger.view.GameScreen;
import frogger.view.ScoreStageLoader;
import frogger.Main;
import frogger.constant.GameOver;

import java.util.List;


/**
 * This class implements the singleton method using enum.
 * Following the MVC pattern, this class acts as Controller for
 * GameScreen (View) and GameModel (Model). This class acts as 
 * a medium between the View and the Model by providing methods
 * for the View to update the Model and vice versa. GameScreen 
 * and GameModel do not interact with each other directly. This
 * class creates a GameScreen and GameModel object. To reduce 
 * redundancy, only a new GameModel is created for each new
 * level as it contains the NPC for each level which have 
 * different properties
 */

public enum GameController  {

	INSTANCE;
	
	private static Stage mainStage = Main.getPrimaryStage();
	private static boolean pause = false;
	private int levelNum = 0; 
	private Frog frog; 
	private String scoreString = "";
	private World gamePane;
	private GameScreen gameView;
	private Scene scene;
	private GameModel gameModel;
	private Alert alert;
	private ImageView bonus;
	
	/**
	 * This method initializes the game properties such as 
	 * the controls chosen by user (WASD or arow keys), the 
	 * {@link World} (gamePane), {@link bonus}, and {@code Alert}
	 * @param control  the key control options ({@link Controls}) 
	 * chosen by user
	 */
	public void initGame(Controls control) {

		gameView = new GameScreen(control); 
		gamePane = gameView.getPane();
		bonus = gameView.getBonus();
		alert = gameView.getAlert();
		
		
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
		levelNum+=1;
		gameView.setLevelText(levelNum);
		gameModel = new GameModel(levelNum);
		Swamp.resetCtr();
		initFrog();
	    startGame();
		System.out.println("This is level "+ levelNum);
	}

	/**
	 * This method (called by {@link CollisionHandler}) invokes the 
	 * GameModel object to update its properties when 
	 * <u1> 
	 * <li> all five Frogs have reached the swamp, or</li>
	 * <li> the Frog object dies and loses all its lives</li>
	 * </u1>
	 * <p>
	 * @param state  {@code GameOver.NEXT} for next level,
	 * {@code GameOver.LOSE} when no lives are left
	 * @see GameOver
	 */
	public void handleGameDone(GameOver state) {
		gameModel.handleDoneLevel(state);
		stopGame();
		showScoreDisplay();
	}
	
	/**
	 * 
	 */
	public void showScoreDisplay() {
		ScoreStageLoader.INSTANCE.initScoreView(gameModel.getLevel(), gameModel.getScores());
		ScoreStageLoader.INSTANCE.showStage();
	 }
	


    /** 
     * This method defines the value of the {@code Alert} property
     * onHiding. This simulates an {@code EventHandler} to handle 
     * the {@code DialogEvent} which occurs when user closes the 
     * dialog box. This method updates the view by either
	 * <u1> 
	 * <li> starting the next level, or </li>
	 * <li> returning to main menu when maximum level 
	 * is reached </li>
	 * </u1>
	 * <p>
	 * 
     * @param event  the {@code DialogEvent} which occured after 
     * dialog box is closed
     */
	public void updateView(DialogEvent event) {
		
		gamePane.getChildren().removeAll(gameModel.getList());
		Swamp.resetCtr();
		
		if(gameModel.getState()==GameOver.NEXT) {
			frog.setScore(0);
			nextLevel();
					
		} else {
			ScreenController.INSTANCE.showMenu();
			new HighScoreFile(scoreString);
			levelNum=0;
		}
		
		
				
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
		Swamp.resetCtr();
		stopGame();
		levelNum=0;
		ScreenController.INSTANCE.showMenu();
		gamePane.getChildren().clear();
		gameModel.resetScoreList();
	}
	
	/**
	 * This method defines the next view to show after score stage
	 * pop up is exited
	 * @param event  the {@code WindowEvent} that occurs after
	 * score stage is hidden 
	 */
	public void showNext(WindowEvent event) {
		showAlert(gameModel.getProgressMessage());
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
	
	
	/*
	 * v2: enable flyBonus=false in Frog hasFlyBonus()
	 * method 1: bonus animation
	 * method 2: call model to play animation 
	 * (if 2 deleted, delete setBonusVisible())
	 */
	
	/**
	 * This method calls this {@link GameModel} object to play the bonus
	 * animation as the animation logic is contained inside the 
	 * model (MVC pattern). If time permits, all animation logic 
	 * will be refactored and moved into its own class.
	 * 
	 * @param bonusX  the x position of the {@link Swamp} class 
	 * where the Frog object caught the fly.
	 */
	public void showBonus(double bonusX) {
		ImageView bonus = gameView.getBonus();
		bonus.setX(bonusX);
		gameModel.playBonus(); //method 2

	}
	

	/**
	 * This method is called to update the visibility of {@link #bonus}
	 * in this GameView object (MVC pattern)
	 * @param bool  true to make {@code bonus} visible
	 */
	public void setBonusVisible(boolean bool) {
		bonus.setVisible(bool);
	}
	
	/**
	 * This method initializes this {@link Frog} object and its
	 * properties.
	 */
	private void initFrog() {
		this.frog = gameModel.getFrog();
		frog.setNoMove(false);
		frog.addScoreListener(this::updateScore);
	    frog.addLifeListener(this::updateLifeView);

	}
	
	/** This method calls all necessary methods to start the game. */
	private void startGame() {
		AudioPlayer.INSTANCE.playMusic();
		gamePane.startMotion();
		gameModel.continueAllTimer();
		frog.setNoMove(false);
	}
	
	/** This method calls all necessary methods to stop the game. */
	private void stopGame() {
		AudioPlayer.INSTANCE.stopMusic();
		gamePane.stopMotion();
		gameModel.pauseAllTimer();
		frog.setNoMove(true);
	}
	
	
	/**
	 * This method simulates the method 'changed' of the {@code 
	 * ChangeListener} interface and defines the actions to be 
	 * taken whenever the {@link Frog} object {@code lifeProp} changes. 
	 * 
	 * In this case, this method defines the actions to update the life 
	 * array displayed on the game screen.  
	 * 
	 * @param observable  the {@code ObservableValue} lifeProp which value changed
	 * @param oldValue  the old value
	 * @param newValue  the new value
	 * @see Frog#addLifeListener(ChangeListener)
	 */
	private void updateLifeView(ObservableValue <?extends Number>observable, Number oldValue, Number newValue) {

		int life = newValue.intValue();
		
		if(life>=0) {
			for (int i = 3; i > life; i--) 
				gameView.getLifeArray().get(i-1).setVisible(false);
		} else {
			GameController.INSTANCE.handleGameDone(GameOver.LOSE);
		}

	}
	
	/**
	 * This method simulates the method {@code changed} of the 
	 * {@code ChangeListener} interface and defines the actions 
	 * to be taken whenever the {@link Frog} object scoreProp 
	 * changes. 
	 * 
	 * In this case, this method defines the actions to update the 
	 * score digit array displayed on the game screen.  
	 * 
	 * @param observable  {@code scoreProp} which value changed
	 * @param oldValue  the old value
	 * @param newValue  the new value
	 * @see Frog#addScoreListener(ChangeListener)
	 */
	private void updateScore(ObservableValue <?extends Number>observable, Number oldValue, Number newValue) {
		gameModel.setScore(newValue.intValue());
	}
	

	/**
	 * This method initializes the header text of the {@code 
	 * @Alert} object and show the alert.
	 * @param string  {@code String} to be displayed as header
	 */
	private void showAlert(String string) {

		alert.setHeaderText(string);
		alert.show();
		
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
	 * {@link #handleKeyPress(KeyEvent)} to be able to use method 
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



///**
// * This method displays an alert message and user scores for each 
// * played level in descending order
// * @param string  {@code String[]} object where string[0] 
// * corresponds to title, string[1] to header and string[2] to content
// */
//@Deprecated
//public void displayAlert(String[] string) {
//
//	alert.setTitle(string[0]);
//	alert.setHeaderText(string[1]);
//	alert.setContentText(string[2]);
//	alert.show();
//}

////TEST1
//Level1 test = new Level1();
//
//for (List<Actor> lane : new Level1().getAllLaneElements()) {
//	//gets each lane list
//	for(Actor element : lane) {
//		model.addElement(element);
//	}
//	
//}
//
//if(level==2) model.increaseAllSpeed();
//if (level==3) {
//	//remove
//	//Level1().add(Lane8, 3, snake);
//}
////END OF TEST1

//for (Actor element : new LevelTest(level).getList()) {
//	model.addElement(element);
//}

//
//
//model.addElement(new Fly(600,100));
//model.addElement(new Fly(600,50));
//


//
//private void addGameElements2(int level) { //change this for each level
//	frog = new Frog(); //new frog for each level since points start at 0
//	switch(level) {
//	case 1:
//		
//	}
//	model.addElement(background);
//	obstacle.addElementsTest(1, 2, 1); //lvl1, increase to 3
//	obstacle.addElementsTest(2, 4, -1); 
//	obstacle.addElementsTest(3, 3, 1);
//	obstacle.addElementsTest(4, 2, -5);
//	obstacle.addElementsTest(5, 3, -1);
//	obstacle.addElementsTest(6, 3, 0.75);
//	obstacle.addElementsTest(7, 1, -2);
//	//obstacle.addElementsTest(8, 3, -1); //testing laneObject
//	obstacle.addElementsTest(9, 3, 0.75);
//	
//
//
//	for (Actor element : obstacle.getElementsList()) {
//		model.addElement(element);
//	}
////	
//
//	
//	model.addElement(frog);
//	setNumber(frog.getScore());//gamePane.add(new Digit(0, 30, 360, 25)); //TODO changed 
//
//}




//obstacle.addElementsTest(1, 4, 1);
//obstacle.addElementsTest(2, 4, -1);
//obstacle.addElementsTest(3, 3, 1);
//obstacle.addElementsTest(4, 1, -5);
//obstacle.addElementsTest(5, 3, -1);
//obstacle.addElementsTest(6, 3, 0.75);
//obstacle.addElementsTest(7, 3, -2);
//obstacle.addElementsTest(8, 3, -1);
//obstacle.addElementsTest(9, 3, 0.75);


//ArrayList<Actor> list = new Lane(8)
//.addElements(3,-1.00)
//.getElementsList();

//for (Actor element : list) {
//	model.addElement(element);
//}
	
//
//IntegerProperty x = new SimpleIntegerProperty(frog.getScore());
//x.addListener( new ChangeListener<Number>() {
//    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//        System.out.println(" new value " + newValue);
//    }
//} );
//
//frog.getScore2().addListener( new ChangeListener<Number>() {
//    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//        System.out.println(" new value " + newValue);
//    }
//} );
//System.out.println("listener");
//	

	//prepare and start animation timer object which
	//is called in each frame while it is active
	
//	private void createGameLoop() {
//		timer = new AnimationTimer() {
//
//			@Override
//			public void handle(long arg0) {
//				moveFrog();
//				
//			}
//
//
//			
//		};
//		timer.start();
//		
//	}
//	


//GameElements lvl1 = new GameElements(level);
//addElements(laneNum, obj, speed, etc) 
//lvl1.addLane1Elements(3,1);
//lvl1.addLane2Elements(4,-1);
//lvl1.addLane3Elements(3,1);
//lvl1.addLane4Elements(1,-5);
//lvl1.addLane5Elements(3,-1);
//lvl1.addLane6Elements(3, 0.75);
//lvl1.addLane7Elements(2, -2);
//lvl1.addLane8Elements(3,-1);
//lvl1.addLane9Elements(3, 0.75);
//for (Actor element : lvl1.getList()) {
//	
//	//gamePane.add(element);
//	model.addElement(element);
//}


//public GVMTest() { 
//	this.levelNum = 0;
//	initScene();
//	createScoreTimer();
//
//}
//
//


///**
//* renders score digit from right to left 
//* each loop renders one digit, points decrease by 10 with each while loop
//* @param score is the user's current score
//*/
//
//
//public void setNumber(int score) { //TODO changed param name
//	
//	//clear previous score from screen
//	for(int i=0;i<scoreDigit.size();i++) {
//		gamePane.remove(scoreDigit.get(i));
//	}
//	
//	scoreDigit.clear();
//	
//	
//	//add current score to array
//	if (score==0) {
//		scoreDigit.add(new Digit(0, scoreXPos, scoreYPos));
//		  
//	} else {
//		
//		int xShift = 0; //TODO changed name
//		int number = score; //TODO added because dont want to modify params bad programming practice
//		while (number > 0) {
//			int ones = number % 10;  //ones = points - quotient * 10; TODO changed
//			scoreDigit.add(new Digit(ones, scoreXPos - xShift, scoreYPos));
//			number/=10;
//			xShift+=30; //shift digit to the left with each while loop
//		}
//	}
//
//	//add score array to screen
//	gamePane.getChildren().addAll(scoreDigit);
//
//}



