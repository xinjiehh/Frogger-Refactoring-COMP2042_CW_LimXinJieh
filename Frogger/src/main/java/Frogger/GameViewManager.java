package Frogger;

//mainpane.getURL path

//import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.control.DialogEvent;

public class GameViewManager {
	
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 800;
	private Animal frog;
	private static final String BACKGROUND_IMG = "resources/background.png";
	private GamePane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private Stage menuStage;
	private AnimationTimer timer;
	private static int scoreXPos = 360; //TODO changed
	private static int scoreYPos = 25; //TODO changed
	public static int level = 0;
	
	public GameViewManager() {
		initializeStage();
		level+=1;
		//createKeyListeners(); 
		
	}

	//initializes gamePane, gameScene and gameStage
	private void initializeStage() {
		gamePane = new GamePane(); 
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);

	}
	
	
	public void createNewStage(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		gamePane.start(); //createGameLoop (actors loop)
		start(); //play music, loop to get scores and check for end game
		addGameElements();
		createFrog();
		
		gameStage.show();
		
	}

	
	private void addGameElements() {
		
		GameElements lvl1 = new GameElements();
		lvl1.addLane1Elements(3);
		lvl1.addLane2Elements(4);
		lvl1.addLane3Elements(2);
		lvl1.addLane4Elements(1);
		lvl1.addLane5Elements();
		lvl1.addLane6Elements(3);
		lvl1.addLane7Elements(2);
		lvl1.addLane8Elements(2);
		lvl1.addLane9Elements(3);
		
		for (Actor element : lvl1.getGameElementsList()) {
			
			gamePane.add(element);
			
		}
		
		gamePane.add(new Digit(0, 30, 360, 25));

	}


	

	
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

	
	//play music, loop to get scores and check for end game
	public void start() { //TODO World start method
		gamePane.playMusic();
    	createTimer();
        timer.start();
    }
	
	
	private void createFrog() {
		
		frog = new Animal("file:src/main/resources/froggerUp.png");
		//gamePane.add(frog);
		gamePane.add(frog);
		
	}
	

	public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if(level<=3) {
            		
                	if (frog.changeScore()) {
                		setNumber(frog.getScore());
                	}
                	if (frog.getStop()) {
                		System.out.print("STOPP:");
                		gamePane.stopMusic();
                		stop();
                		gamePane.stopWorldTimer();
                		Alert alert = new Alert(AlertType.INFORMATION);
                		alert.setTitle("You Have Won The Game!");
                		alert.setHeaderText("Your High Score: "+ frog.getScore()+"!");
                		alert.setContentText("Highest Possible Score: 800");
                		alert.show();
                		alert.setOnHiding(new EventHandler<DialogEvent>(){

    						@Override
    						public void handle(DialogEvent event) {
    							gamePane.getChildren().clear();
    							GameViewManager gameManager = new GameViewManager();
    							gameManager.createNewStage(gameStage);

    						}
                			
                		});
                		

                	}
            		
            	}

            }
        };
    }

	
	
	/**
	 * renders score digit from right to left 
	 * each loop renders one digit, points decrease by 10 with each while loop
	 * @param score is the user's current score
	 */
	  public void setNumber(int score) { //TODO changed param name
		  int xShift = 0; //TODO changed name
		  int number = score; //TODO added because dont want to modify params bad programming practice
		  while (number > 0) {
			  //int d = number / 10; 
			  int ones = number % 10;  //ones = points - quotient * 10; TODO changed
			  gamePane.add(new Digit(ones, 30, scoreXPos - xShift, scoreYPos));
			  number/=10;
			  xShift+=30; //shift digit to the left with each while loop
	    		  
	    }
	   }
	  
	public void setNumber2(int n) {
	    	int shift = 0;
	    	while (n > 0) {
	    		  int d = n / 10; //removed as it is unnecessary
	    		  int k = n - d * 10; //gets ones
	    		  n = d;
	    		  //int k = n%10
	    		  //n/=10;
	    		  gamePane.add(new Digit(k, 30, 360 - shift, 25));
	    		  shift+=30;
	    		}
	    }
    
    
    public void stop() {
        if(timer!=null)
        	timer.stop();
    }
	
	

}



