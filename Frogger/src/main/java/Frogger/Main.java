package Frogger;

import java.io.File;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;




public class Main extends Application {
	AnimationTimer timer;
	Animal animal;
	private static final int HEIGHT = 800;
	private static final int WIDTH = 600;
	private static final int BUTTON_START_XPOS = 100;
	private static final int BUTTON_START_YPOS = 200;
	private MyStage mainPane;
	private Scene mainScene;
	private FrogSubscene infoSubscene;
	private FrogSubscene sceneShowing;
	private Stage mainStage;
	private Stage menuStage;
	ArrayList<ButtonMod> menuButtonList;
	private MyStage gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			menuButtonList = new ArrayList<ButtonMod>();
			mainPane = new MyStage();
			mainScene = new Scene(mainPane, WIDTH, HEIGHT);
			mainStage = new Stage();
			createMenuBackground();
			createButtons();
			
			createInfoSubscene();
			mainStage.setScene(mainScene);
			mainStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void showSubscene(FrogSubscene subScene) {
		if(sceneShowing!=null) {
			sceneShowing.moveSubscene();
			
		} 
		
		subScene.moveSubscene();
		sceneShowing = subScene;
	}
	

	private void createInfoSubscene() {
		infoSubscene = new FrogSubscene();
		mainPane.getChildren().add(infoSubscene);
	};
	
	
	public Stage getMainStage() {
		return mainStage;
	};
	
	
	private void createButtons() {
		createStartButton();
		createInfoButton();
		createExitButton();

	}
	
	
	private void createStartButton() {
		ButtonMod startButton = new ButtonMod("start");
		System.out.println("start button created");
		addMenuButton(startButton);
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				createNewGame(mainStage);
				
			}
			
		});
		
	}
	
	private void createNewGame(Stage menuStage) { //GameViewManager
		initializeStage();
		//createKeyListeners();
		this.menuStage = menuStage;
		this.menuStage.hide();
		//createBackground();
		//createFrog();
		//createGameLoop(); //gamePane.start
		gameStage.show();
		start();
		
	}
	
	private void createGameLoop() {
		//timer
		
	}


	private void createFrog() {
		//Animal
		
	}


	private void initializeStage() {
		gamePane = new MyStage(); 
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		gameStage = new Stage();
		gamePane.start();
		addStuff(gamePane);
		gameStage.setScene(gameScene);
		
	}
	
	private void addStuff(MyStage gamePane) {
		BackgroundImage froggerback = new BackgroundImage();
	    
		gamePane.add(froggerback);
		
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
	
	private void createInfoButton() {
		
		ButtonMod infoButton = new ButtonMod("info");
		addMenuButton(infoButton);
		System.out.println("info button created");
		
		infoButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubscene(infoSubscene);
				System.out.println("show");
//				infoSubscene.moveSubscene();
				
				
			}
			
		});
		
	}

	private void createExitButton() {
		ButtonMod exitButton = new ButtonMod("exit");
		addMenuButton(exitButton);
		System.out.println("exit button");
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mainStage.close();
			
				
			}
			
		});
		
	}

	private void createMenuBackground() {
		
		BackgroundImage menuBackground = new BackgroundImage("file:src/main/resources/frog background.png");
	    
		mainPane.add(menuBackground);
		
		
	}


	/**
	 * This method sets the position of a menu button and add it to <code>menuButtonList</code> 
	 * X position is standardized
	 * Y position increases with the number of menu buttons 
	 */
	private void addMenuButton(ButtonMod button) {
		button.setLayoutX(BUTTON_START_XPOS);
		button.setLayoutY(BUTTON_START_YPOS + menuButtonList.size() * 100);
		menuButtonList.add(button);
		mainPane.getChildren().add(button);
		System.out.println(mainPane.getChildren());
	}
	
	/*
	 * private void createLogo() {
	 * 
	 * ImageView logo = new ImageView("insert url here"); 
	 * logo.setLayoutX(arg0);
	 * logo.setLayoutY(arg0);
	 * 
	 * mainPane.getChildren().add(logo);
	 * 
	 * }
	 */

	//original 
	
	
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
            		gamePane.stop();
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("You Have Won The Game!");
            		alert.setHeaderText("Your High Score: "+animal.getPoints()+"!");
            		alert.setContentText("Highest Possible Score: 800");
            		alert.show();
            	}
            }
        };
    }
	public void start() {
		gamePane.playMusic();
    	createTimer();
        timer.start();
    }

    public void stop() {
        if(timer!=null)timer.stop();
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
	
	
	
	
	
}
