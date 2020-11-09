package Frogger;


import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GameViewManager {
	
	private static final int GAME_WIDTH = 600;
	private static final int GAME_HEIGHT = 800;
	private Animal animal;
	private static final String BACKGROUND_IMG = "resources/background.png";
	private GamePane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private Stage menuStage;
	private AnimationTimer timer;
	
	public GameViewManager() {
		initializeStage();
		//createKeyListeners(); 
		
	}


	private void initializeStage() {
		gamePane = new GamePane(); 
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
		
	}
	

	
	private void addStuff(GamePane gamePane) {

		gamePane.add(new Log("file:src/main/resources/log3.png", 150, 0, 166, 0.75));
		gamePane.add(new Log("file:src/main/resources/log3.png", 150, 220, 166, 0.75));
		gamePane.add(new Log("file:src/main/resources/log3.png", 150, 440, 166, 0.75));
		//background.add(new Log("file:src/main/resources/log3.png", 150, 0, 166, 0.75));
		gamePane.add(new Log("file:src/main/resources/logs.png", 300, 0, 276, -2));
		gamePane.add(new Log("file:src/main/resources/logs.png", 300, 400, 276, -2));
		//background.add(new Log("file:src/main/resources/logs.png", 300, 800, 276, -2));
		gamePane.add(new Log("file:src/main/resources/log3.png", 150, 50, 329, 0.75));
		gamePane.add(new Log("file:src/main/resources/log3.png", 150, 270, 329, 0.75));
		gamePane.add(new Log("file:src/main/resources/log3.png", 150, 490, 329, 0.75));
		//background.add(new Log("file:src/main/resources/log3.png", 150, 570, 329, 0.75));
		//Obstacle obstacle = new Obstacle("file:src/main/resources/truck1Right.png", 25, 25, 3);
		//Obstacle obstacle1 = new Obstacle("file:src/main/resources/truck2Right.png", 100, 100,2 );
		//Obstacle obstacle2 = new Obstacle("file:src/main/resources/truck1Right.png",0,  150, 1);

		gamePane.add(new Turtle(500, 376, -1, 130, 130));
		gamePane.add(new Turtle(300, 376, -1, 130, 130));
		gamePane.add(new WetTurtle(700, 376, -1, 130, 130));
		gamePane.add(new WetTurtle(600, 217, -1, 130, 130));
		gamePane.add(new WetTurtle(400, 217, -1, 130, 130));
		gamePane.add(new WetTurtle(200, 217, -1, 130, 130));
		//background.add(new Log("file:src/main/resources/log2.png", 200, 100, 1));
		//background.add(new Log("file:src/main/resources/log2.png", 0, 100, 1));
		//background.add(new Log("file:src/main/resources/log2.png", 100, 120, -1));
		//background.add(new Log("file:src/main/resources/log2.png", 200, 120, -1));
		//background.add(new Log("file:src/main/resources/log2.png", 100, 140, 1));
		//background.add(new Log("file:src/main/resources/log2.png", 200, 140, 1));
		//background.add(new Log("file:src/main/resources/log2.png", 100, 160, -1));
		//background.add(new Log("file:src/main/resources/log2.png", 300, 160, -1));
		//background.add(new Log("file:src/main/resources/log2.png", 100, 180, 1));
		//background.add(new Log("file:src/main/resources/log2.png", 200, 180, 1));
		//background.add(new Log("file:src/main/resources/log2.png", 100, 200, -1));
		//background.add(new Log("file:src/main/resources/log2.png", 200, 200, -1));
		//background.add(new Log("file:src/main/resources/log2.png", 100, 220, 1));
		//background.add(new Log("file:src/main/resources/log2.png", 200, 220, 1));
		//background.add(new Log("file:src/main/resources/log2.png", 400, 220, 1));
		//End end2 = new End();
		//End end3 = new End();
		//End end4 = new End();
		//End end5 = new End();
		gamePane.add(new End(13,96));
		gamePane.add(new End(141,96));
		gamePane.add(new End(141 + 141-13,96));
		gamePane.add(new End(141 + 141-13+141-13+1,96));
		gamePane.add(new End(141 + 141-13+141-13+141-13+3,96));
		animal = new Animal("file:src/main/resources/froggerUp.png");
		gamePane.add(animal);
		gamePane.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", 0, 649, 1, 120, 120));
		gamePane.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", 300, 649, 1, 120, 120));
		gamePane.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", 600, 649, 1, 120, 120));
		//background.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", 720, 649, 1, 120, 120));
		gamePane.add(new Obstacle("file:src/main/resources/car1Left.png", 100, 597, -1, 50, 50));
		gamePane.add(new Obstacle("file:src/main/resources/car1Left.png", 250, 597, -1, 50, 50));
		gamePane.add(new Obstacle("file:src/main/resources/car1Left.png", 400, 597, -1, 50, 50));
		gamePane.add(new Obstacle("file:src/main/resources/car1Left.png", 550, 597, -1, 50, 50));
		gamePane.add(new Obstacle("file:src/main/resources/truck2Right.png", 0, 540, 1, 200, 200));
		gamePane.add(new Obstacle("file:src/main/resources/truck2Right.png", 500, 540, 1, 200, 200));
		gamePane.add(new Obstacle("file:src/main/resources/car1Left.png", 500, 490, -5, 50, 50));
		gamePane.add(new Digit(0, 30, 360, 25));
		//background.add(obstacle);
		//background.add(obstacle1);
		//background.add(obstacle2);
	}
	
	
	
	
	
	//prepare and start animation timer object which
	//is called in each frame while it is active
	
	private void createGameLoop() {
		timer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				moveFrog();
				
			}


			
		};
		timer.start();
		
	}
	
	private void moveFrog() {
		// TODO Auto-generated method stub
		
	}
	
	
	//TODO should logic be separated into moveFrog()
	//movement logic implemented in createGameLoop()
	private void createKeyListeners() {
		//instead of setOnAction, listening to specific values
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	
	public void createNewStage(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		//createFrog();
		
		
		//actors loop
		gamePane.start(); //createGameLoop
		start(); //play music, loop to get scores and check for end game
		addStuff(gamePane);
		gameStage.show();
		
	}
	
	//play music, loop to get scores and check for end game
	public void start() { //TODO World start method
		gamePane.playMusic();
    	createTimer();
        timer.start();
    }
	
	
	
	private void createFrog() {
		
		//Animal frog = new Animal();
		//frog.setLayoutX(GAME_WIDTH/2);
		//frog.setLayoutY(GAME_HEIGHT-90);
		//gamePane.getChildren().add(frog);
		
	}
	
	
	private void createBackground() {
		
		BackgroundImage froggerback = new BackgroundImage();
		gamePane.add(froggerback);
	}
	
	public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	if (animal.changeScore()) {
            		setNumber(animal.getPoints());
            	}
            	if (animal.getStop()) {
            		System.out.print("STOPP:");
            		gamePane.stopMusic();
            		stop();
            		gamePane.stopWorldTimer();
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("You Have Won The Game!");
            		alert.setHeaderText("Your High Score: "+ animal.getPoints()+"!");
            		alert.setContentText("Highest Possible Score: 800");
            		alert.show();
            	}
            }
        };
    }


	
	
    public void setNumber(int n) {
    	int shift = 0;
    	while (n > 0) {
    		  int d = n / 10;
    		  int k = n - d * 10;
    		  n = d;
    		  gamePane.add(new Digit(k, 30, 360 - shift, 25));
    		  shift+=30;
    		}
    }
    
    
    public void stop() {
        if(timer!=null) timer.stop();
    }
	
	

}
