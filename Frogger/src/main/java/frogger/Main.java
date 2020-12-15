package frogger;

import frogger.controller.ScreenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


///**
// * Returns the number of elements in this list.  If this list contains
// * more than {@code Integer.MAX_VALUE} elements, returns
// * {@code Integer.MAX_VALUE}.
// *
// * @return the number of elements in this list
// */
///* TODO ideas
// * stuff needed:
// * 1. create class to declare / initialize all levels
// *
// */

/* https://techterms.com/definition/mvc
 * https://www.oracle.com/technical-resources/articles/java/java-se-app-design-with-mvc.html
addScoreListener and addLifeListener methods in frog so game controller can define listener to be updated when frog score changes


Model:
	Contains the state of the control (e.g. a label's text), and
	The operations that manipulate the state

View:
	Computes minimum, maximum, and preferred sizes
	Responsible for containment testing
	
Controller:
	Interacts with user
	Responds to GUI events
	Defines control logic (eg receives update from view then notifies model to add item)

PlayerAvatar & Actor is template method / strategy?


 */
/*
 * view model observer relationship
 * view registers to model 
 * model calls back to view when it has changed
 * view redisplay
 * 
 */

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
	
	public static Stage getPrimaryStage() {
		return Main.primaryStage;
	}
	
}

