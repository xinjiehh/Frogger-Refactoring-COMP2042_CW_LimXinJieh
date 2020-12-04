package frogger.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import frogger.constant.FilePath;
//import frogger.constant.GameLevel;
//import frogger.constant.GameMode;
//import frogger.util.MusicPlayer;
//import frogger.util.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

/**
 * This class is the controller for the selection view
 */

public class SelectionController {
	
  @FXML private Button mode;
  @FXML private Button controls;
  @FXML private Button start;
  
  
  /**
   * This enum represents the two key control options for users
   *
   */
  public enum Controls {
	  /**WASD*/
	  A, 
	 /**ARROW KEYS*/
	  B; 
  }
  
  /**
   * This enum represents the two modes for user to choose 
   * from (to be implemented)
   *
   */
  public enum Mode {
	  SINGLE,
	  DOUBLE;
  }

  
  @FXML
  public void initialize() {
	initFont();
    mode.setUserData(Mode.SINGLE);
    controls.setUserData(Controls.A);
  }
  
  /**
   * This method initializes the font to be used in the 
   * related fxml file
   */
  private void initFont() {
	  Font font;
		
		try {font = Font.loadFont(new FileInputStream(FilePath.DEFAULT_FONT), 20);} 
		catch (FileNotFoundException e){
			System.out.println("Error loading font for selection");
			font = Font.font("Sans Serif", 20);
		}
	
	  mode.setFont(font);
	  controls.setFont(font);
	  start.setFont(font);
  }


  /**
   * This method allows users to switch between different playing 
   * modes (to be implemented)
   */
  @FXML
  public void switchMode() {
  }

  
  /**
   * This method is responsible for updating the view to 
   * allow users to select their key controls
   */
  @FXML
  public void switchControls() {
	  switch ((Controls) controls.getUserData()) {
	  case A:
		  controls.setUserData(Controls.B);
		  controls.setText("< ARROW KEYS >");
		  break;
	  case B:
		  controls.setUserData(Controls.A);
		  controls.setText("<    WASD    >");
		  break;
	  } 
	  
  }

  /**
   * This method starts the game by calling {@link 
   * ScreenController#startGame(Controls)}
   */
  @FXML
  public void startGame() {
    ScreenController.INSTANCE.startGame((Controls) controls.getUserData());
  }



}
