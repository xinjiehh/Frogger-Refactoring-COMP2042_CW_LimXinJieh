package frogger.controller;

import java.io.IOException;

import frogger.Main;
import frogger.constant.FilePath;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public enum MenuController {
	INSTANCE;
	
	private Pane pane = new Pane();
	FXMLLoader loader;
	
	public void viewSelection() {
		try {
		      this.loader = new FXMLLoader(getClass().getResource(FilePath.VIEW_SELECT));
		      this.pane = loader.load();
		      Main.getPrimaryStage().getScene().setRoot(pane);
		      Main.getPrimaryStage().show();
		} catch (IOException e) {
		      e.printStackTrace();
		}

		// add a jumping frog on the screen
		//root.getChildren().add(frog);
	}
	
	
	public void showInfo() {
		try {
		      FXMLLoader loader = new FXMLLoader((getClass().getResource("/help.fxml")));
		      Pane root = loader.load();
		      Stage infoStage = new Stage();
		      infoStage.setScene(new Scene(root));
		      infoStage.initOwner(Main.getPrimaryStage().getScene().getWindow());
		      infoStage.setResizable(false);
		      infoStage.setTitle("Info");
		      infoStage.show();
		    } catch (IOException e) {
		    	System.out.println("Error loading info window in Screen Controller");
		    	e.printStackTrace();
		    }
	}
	
	public void exit() {
		Platform.exit();
		//Main.getPrimaryStage().close();
		//https://stackoverflow.com/questions/26619566/javafx-stage-close-handler
	}

	

}
