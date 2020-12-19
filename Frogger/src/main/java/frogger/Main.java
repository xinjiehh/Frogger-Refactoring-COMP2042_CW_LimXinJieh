package frogger;

import frogger.controller.ScreenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/** This class is the default entry point of JavaFX application */
public class Main extends Application {
	/** the default stage height */
	public static final double STAGE_H = 800;
	
	/** the default stage width */
	public static final double STAGE_W = 600;	
	
	/** the {@code Stage} window */
	private static Stage primaryStage;
	
	/** the {@code Pane} object */
	private static Pane pane = new Pane();
	
    @Override
	public void start(Stage primaryStage) {
    	
    	primaryStage.setResizable(false);
    	primaryStage.toFront();
    	Main.primaryStage = primaryStage;
    	
    	Font.loadFont(getClass().getResourceAsStream("/font/RetroGaming.ttf"), 20);

    	
		try {

			primaryStage.setScene(new Scene(pane,STAGE_W,STAGE_H));
			ScreenController.INSTANCE.showMenu();
			

		} catch(Exception e) {
			System.out.println("Main application failed to load");
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * This method returns the {@code Stage} of this {@code Main}
	 * object to be used primarily for all scenes
	 * @return  the primary {@code Stage} object
	 */
	public static Stage getPrimaryStage() {
		return Main.primaryStage;
	}
	
}

