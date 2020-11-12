package Frogger;

import java.io.File;
import java.util.List;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class ViewManager {
	private static final double HEIGHT = 800;//PaneModel.getPaneHeight();
	private static final double WIDTH = 600;//PaneModel.getPaneWidth();;
	private static final int BUTTON_START_XPOS = 100;
	private static final int BUTTON_START_YPOS = 200;
	private MenuPane menuPane;
	private Scene mainScene;
	private FrogSubscene infoSubscene;
	private FrogSubscene sceneShowing;
	private Stage mainStage;
	private ArrayList<ButtonMod> menuButtonList;
	//private GamePane gamePane;

	
	public ViewManager() {
		menuButtonList = new ArrayList<ButtonMod>();
		menuPane = new MenuPane();
		menuPane.setCache(true);
		mainScene = new Scene(menuPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setResizable(false);
		createMenuBackground();
		createButtons();
		createInfoSubscene();
		mainStage.setScene(mainScene);
		System.out.println(menuPane.getChildren());
		mainStage.show();
		

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
		menuPane.getChildren().add(infoSubscene);
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
				startGame(mainStage);
				
			}
			
		});
		
	}
	
	private void startGame(Stage menuStage) { 
		GameViewManager gameManager = new GameViewManager();
		gameManager.createNewStage(mainStage);
		
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	
	//methods for menu UI

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
		menuPane.add(menuBackground);
		
		
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
		menuPane.getChildren().add(button);
		
	}
	

}
