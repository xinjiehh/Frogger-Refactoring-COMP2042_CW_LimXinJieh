package frogger.util;

import java.io.IOException;
import java.util.List;

import frogger.Main;
import frogger.constant.FilePath;
import frogger.controller.GameController;
import frogger.controller.ScoreScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public enum ViewLoader {
	
	INSTANCE;
	
	private final Stage mainStage = Main.getPrimaryStage();
	private Pane pane = new Pane();

	/** pop up {@code Stage} separate from main stage */
	private Stage stage;
	
	private FXMLLoader loader;
	
	/**
	 * This method loads the selection view
	 */
	public void loadSelection() {
		loadFXML(FilePath.VIEW_SELECT);
		mainStage.getScene().setRoot(pane);
		mainStage.show();
		
	}
	
	
	/**
	 * This method loads the info view
	 */
	public void loadInfo() {
		loadFXML(FilePath.INFO_FXML);
		initNewStage("Info");
		this.stage.show();
		
	}
	
	/**
	 * This method loads the score view layout from the scoreview FXML file 
	 * and initializes this Pane object with the loaded object hierarchy. This
	 * method also calls the related controller to initalize the data to be
	 * shown in the view.
	 * 
	 * @param levelData  list of levels played
	 * @param scoreData  list of scores
	 */
	public void loadScore(List<String>levelData, List<String>scoreData) {
		loadFXML(FilePath.VIEW_SCORE);
		initNewStage("High Score");
		((ScoreScreenController) loader.getController()).initView(levelData, scoreData);
		this.stage.setOnHiding(GameController.INSTANCE::showNext);
		this.stage.show();
		
	}
	
	/**
	 * This method initializes this {@link #stage} and
	 * its properties
	 */
	private void initNewStage(String title) {
		this.stage = new Stage();
		stage.setScene(new Scene(pane));
		stage.setTitle(title);
		stage.setResizable(false);
		stage.initOwner(mainStage.getScene().getWindow());
	}
	
	/**
	 * This method loads the FXML file and initializes this {@code Pane} 
	 * object
	 * @param str  file path of the FXML to be loaded
	 */
	private void loadFXML(String str) {
		
		this.loader = new FXMLLoader(getClass().getResource(str));
	    try {
	    	
			this.pane = loader.load();
			
		} catch (IOException e) {
			
			System.out.println("Error loading " + str);
			e.printStackTrace();
		}
	}
	

}

//
//package frogger.controller;
//
//import java.io.IOException;
//
//import frogger.Main;
//import frogger.constant.FilePath;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//
//public enum LoaderTest {
//	
//	INSTANCE;
//	
//	private Pane pane = new Pane();
//	private Stage stage;
//	
//	public void loadSelection() {
//		loader(FilePath.VIEW_SELECT);
//		Main.getPrimaryStage().getScene().setRoot(pane);
//		Main.getPrimaryStage().show();
//		
//	}
//	
//	
//	
//	public void loadInfo() {
//		loader(FilePath.INFO_FXML);
//		Stage infoStage = new Stage();
//		infoStage.setScene(new Scene(pane));
//		infoStage.setTitle("Info");
//		infoStage.setResizable(false);
//		infoStage.initOwner(Main.getPrimaryStage().getScene().getWindow());
//		
//	}
//	
//	
//	private void initStage(String title) {
//		this.stage = new Stage();
//		stage.setScene(new Scene(pane));
//		stage.setTitle(title);
//		stage.setResizable(false);
//		stage.initOwner(Main.getPrimaryStage().getScene().getWindow());
//	}
//	
//	/**
//	 * This method loads the FXML file and initializes this {@code Pane} 
//	 * object
//	 * @param str  file path of the FXML to be loaded
//	 */
//	private void loader(String str) {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
//	    try {
//	    	
//			this.pane = loader.load();
//			
//		} catch (IOException e) {
//			
//			System.out.println("Error loading " + str);
//			e.printStackTrace();
//		}
//	}
//	
//
//}

