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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import frogger.model.Frog;
import frogger.model.GameModel;
import frogger.model.World;
import frogger.model.NPC.Swamp;
import frogger.util.AudioPlayer;
import frogger.util.HighScoreFile;
import frogger.view.GameScreen;
import frogger.Main;

import java.io.IOException;
import java.util.List;


/**
 * This class allows 
 *
 */

public enum GameController  {

	INSTANCE;
	
	public enum GameOver {
		NEXT,
		WIN,
		LOSE;
	}
	
	
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
	
	public void nextLevel() {
		levelNum+=1;
		gameView.setLevelText(levelNum);
		gameModel = new GameModel(levelNum);
		Swamp.resetCtr();
		initFrog();
	    startGame();
		System.out.println("This is level "+ levelNum);
	}


	public void handleDoneLevel() {
		gameModel.handleDoneLevel();
		stopGame();
		showScoreDisplay();
	}
	
	public void handleGameOver() {
		gameModel.handleGameOver();
		stopGame();
		showScoreDisplay();
	}
	
	public void showScoreDisplay() {
	
		try {
		      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/scoreview.fxml"));
		      Pane pane = loader.load();
		      
		      Stage scoreStage = new Stage();
		      scoreStage.setScene(new Scene(pane));
		      scoreStage.initOwner(mainStage.getScene().getWindow());
		      scoreStage.setResizable(false);
		      scoreStage.setOnHiding(this::showNext);
		      scoreStage.setTitle("HIGH SCORES");
		      
		      ScoreViewController controller = loader.getController();
		      controller.init(gameModel);
		      
		      scoreStage.show();
		      
		 } catch (IOException e) {
		      e.printStackTrace();
		    }
	 }
	


	
	
	/**
	 * This method updates the view after user closes dialog box by 
	 * starting the next level or returning to main menu when maximum 
	 * level is reached 
	 */
    
	public void updateView(Event event) {
		
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
	

	public void handleExit(ActionEvent event) {
		Swamp.resetCtr();
		stopGame();
		levelNum=0;
		ScreenController.INSTANCE.showMenu();
		gamePane.getChildren().clear();
		gameModel.resetScoreList();
	}
	
	public void showNext(Event event) {
		displayAlert(gameModel.getProgressMessage());
	}
	
	public void updateLifeView(ObservableValue <?extends Number>observable, Number oldValue, Number newValue) {

		int life = newValue.intValue();
		
		if(life>=0) {
			for (int i = 3; i > life; i--) 
				gameView.getLifeArray().get(i-1).setVisible(false);
		}

	}
	
	public void updateScore(ObservableValue <?extends Number>observable, Number oldValue, Number newValue) {
		gameModel.setScore(newValue.intValue());
	}
	

	
	public void handlePause(ActionEvent event) {
		if(!pause) {
			pause=true;
			stopGame();
		} else {	
			pause=false;
			startGame();
		}
	}
	

	public void addToView(List<Node> list) {
		
		for(Node node: list) {
			gamePane.add(node);
		}
		
	}
	
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
	 * This method calls GameModel object to play bonus
	 * animation as the animation logic is contained 
	 * inside the model (MVC pattern)
	 */
	public void showBonus() {
		ImageView bonus = gameView.getBonus();
		bonus.setX(frog.getBonusX());
		gameModel.playBonus(); //method 2
		
		/*
		 * method 1
		 * BonusAnimation anim = new BonusAnimation(bonus); 
		 * anim.start();
		 */
	}
	
	

	/**
	 * This method is called to update the visibility of the
	 * ImageView 'bonus' in this GameView object (MVC pattern)
	 * @param show  boolean, true to make 'bonus' visible
	 */
	public void setBonusVisible(boolean show) {
		
		if(show) 
			bonus.setX(frog.getBonusX());
			
		bonus.setVisible(show);
	
	}
	
	
	private void initFrog() {
		this.frog = gameModel.getFrog();
		frog.addScoreListener(this::updateScore);
	    frog.addLifeListener(this::updateLifeView);
	}
	
	private void startGame() {
		AudioPlayer.INSTANCE.playMusic();
		gamePane.startMotion();
		gameModel.continueAllTimer();
		frog.setNoMove(false);
	}
	

	private void stopGame() {
		AudioPlayer.INSTANCE.stopMusic();
		gamePane.stopMotion();
		gameModel.pauseAllTimer();
		frog.setNoMove(true);
	}
	

	private void displayAlert(String string) {

		alert.setHeaderText(string);
		alert.show();
	}
	
	
	//lambda expression used (method reference)
	private void initGameScene() {
		this.scene = mainStage.getScene();
		this.scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPress);
		this.scene.addEventFilter(KeyEvent.KEY_RELEASED, this::handleKeyRelease);
	} //(event e - > handleKeyRelease(e))
	
	public void handleKeyPress(KeyEvent event) {
		gamePane.handleKeyTest(event, true);
		event.consume();
	}
	
	public void handleKeyRelease(KeyEvent event) {
		gamePane.handleKeyTest(event, false);
		event.consume();
	}
	

	/**
	 * This method displays an alert message and user scores for each 
	 * played level in descending order
	 */
	@Deprecated
	public void displayAlert(String[] string) {

		alert.setTitle(string[0]);
		alert.setHeaderText(string[1]);
		alert.setContentText(string[2]);
		alert.show();
	}

	

}





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



