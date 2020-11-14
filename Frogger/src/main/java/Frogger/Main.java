package Frogger;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;



public class Main extends Application {
	
	public static final String RESOURCE_PATH = "src/main/resources/";
	
    @Override
	public void start(Stage primaryStage) {
		try {
			ViewManager manager = ViewManager.getInstance();
			primaryStage = manager.getMainStage();
			
			
			System.out.println("Meg used="+(Runtime.getRuntime().totalMemory()-
					Runtime.getRuntime().freeMemory())/(1000*1000)+"M");
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
