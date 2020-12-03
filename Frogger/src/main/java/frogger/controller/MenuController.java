package frogger.controller;

import java.io.IOException;

import frogger.Main;
import frogger.constant.FilePath;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This enum class implements singleton method. It acts as controller
 * for MenuScreen (View) which handles the different actions of the 
 * menu buttons such as showing selection screen, showing info screen
 * and exiting the application
 * 
 */
public enum MenuController {
	INSTANCE;
	
	private Pane pane = new Pane();
	private FXMLLoader loader;
	
	/**
	 * This method loads and shows the selection page from FXML for 
	 * users to choose their controls
	 */
	public void viewSelection() {
		try {
			this.loader = new FXMLLoader(getClass().getResource(FilePath.VIEW_SELECT));
		    this.pane = loader.load();
		    Main.getPrimaryStage().getScene().setRoot(pane);
		    Main.getPrimaryStage().show();
		      
		} catch (IOException e) {
			System.out.println("Error loading selection page");
			e.printStackTrace();
		}

	}
	
	/**
	 * This method loads and shows the info page from FXML which has 
	 * a brief overview of the game
	 */
	public void showInfo() {
		try {
			FXMLLoader loader = new FXMLLoader((getClass().getResource("/view/info.fxml")));
		    this.pane = loader.load();
		   
		    Stage infoStage = new Stage();
		    infoStage.setTitle("Info");
		    infoStage.setResizable(false);
		    infoStage.initOwner(Main.getPrimaryStage().getScene().getWindow());
		    infoStage.setScene(new Scene(pane));
		   
		    
		    infoStage.show();
		    
		  } catch (IOException e) {
			  System.out.println("Error loading info window in Screen Controller");
			  e.printStackTrace();
		  }
	}
	
	/**
	 * This method handles the exiting of the application
	 */
	public void exit() {
		Platform.exit();
		//https://stackoverflow.com/questions/26619566/javafx-stage-close-handler
	}

	

}
