package frogger.util;

import frogger.Main;
import frogger.constant.FilePath;
import frogger.controller.GameController;
import frogger.controller.ProgressScreenController;
import frogger.controller.ScoreScreenController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * This util class implements SINGLETON pattern. It is responsible for loading the FXML
 * files and only one instance is needed per JVM.
 */
public enum ViewLoader {
	/** singleton instance for this enum class */
	INSTANCE;
	
	/** the {@code Stage} object created in {@link Main} */
	private final Stage mainStage = Main.getPrimaryStage();
	
	/** the {@code Scene} object created in {@link Main} */
	private Pane pane = new Pane();

	/** pop up {@code Stage} separate from main stage */
	private Stage stage;
	
	/** loader to load FXML */
	private FXMLLoader loader;
	
	/**
	 * This method loads the selection view
	 */
	public void loadSelection() {
		loadFXML(FilePath.SELECTION_FXML);
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
	 * This method loads the progress screen
	 * @param header  {@code String} message to be shown
	 * @param buttonText {@code String} placeholder for button
	 * @param x  {@code EventHandler} for {@code onAction} property of button
	 */
	public void loadProgressScreen(String header,String buttonText, EventHandler<ActionEvent> x) {
		loadFXML(FilePath.PROGRESS_FXML);
		ProgressScreenController controller = loader.getController();
		pane.addEventFilter(KeyEvent.KEY_PRESSED,controller::handleOnKeyPressed);
		pane.requestFocus();
		controller.setText(header);
		controller.setButtonText(buttonText);
		controller.setButtonAction(x);

		mainStage.getScene().setRoot(pane);
		mainStage.getScene().getRoot().requestFocus();
		mainStage.show();
	}
	
	/**
	 * This method loads the score view layout from the scoreview FXML file 
	 * and initializes this Pane object with the loaded object hierarchy. This
	 * method also calls the related controller to initialize the data to be
	 * shown in the view.
	 * 
	 * @param levelData  list of levels played
	 * @param scoreData  list of scores
	 */
	public void loadScore(List<String>levelData, List<String>scoreData) {
		loadFXML(FilePath.SCORE_FXML);
		initNewStage("High Score");
		((ScoreScreenController) loader.getController()).initView(levelData, scoreData);
		this.stage.setOnHiding(GameController.INSTANCE::showNextScreen);
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