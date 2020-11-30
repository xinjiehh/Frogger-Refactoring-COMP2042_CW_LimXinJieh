package frogger.controller;

import frogger.Main;
import frogger.view.MenuScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import frogger.controller.SelectionController.Controls;

public enum ScreenController {
	INSTANCE;
	private Stage stage = Main.getPrimaryStage();
	private Pane pane = new Pane();
	FXMLLoader loader;

	
	public void showMenu() {
		MenuScreen menu = MenuScreen.getInstance();
		pane = menu.getPane();		
		stage.getScene().setRoot(pane);
		stage.show();
		
	}

	//pass on the controls to game controller
	public void startGame(Controls cont, String str1, String str2) {
		
		System.out.println("Meg used="+(Runtime.getRuntime().totalMemory()-
				Runtime.getRuntime().freeMemory())/(1000*1000)+"M");
		
		GameController.INSTANCE.createNewGame(cont);
		
		System.out.println("Meg used="+(Runtime.getRuntime().totalMemory()-
				Runtime.getRuntime().freeMemory())/(1000*1000)+"M");

	}
	

	
//	//gets called for new level
//	//pass level into createNewLevel
//	public void startGameTest(int i) {
//		
//		GameControllerTest.INSTANCE.createNewtest();
//		//nextLevel(int i)
//		//if 2 then 
//
//	}

//	
//	public void nextLevel() {
//		level++;
//		startGameTest(level);
//	}
	


}


//public void startGame() {
//	//GVMTest gameManager = new GVMTest();
//	//gameManager.createNewLevel();
//	//scene = gameManager.getScene();
//	System.out.println("clicekd");
//	GameControllerTest.INSTANCE.createNewtest();
//	System.out.println("after");
//	//switchView();
//
//}
