package frogger.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import frogger.constant.FilePath;
import frogger.util.AudioPlayer;
//import frogger.constant.GameLevel;
//import frogger.constant.GameMode;
//import frogger.util.MusicPlayer;
//import frogger.util.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Font;


public class SelectionController {
	

  @FXML private Button mode;
  @FXML private Button controls;
  @FXML private Button start;
  @FXML private Button musicon;
  @FXML private Button musicoff;

  public enum Controls {
	  A, //WASD
	  B, //ARROW
  }
  
  public enum Mode {
	  SINGLE,
	  DOUBLE;
  }

  @FXML
  public void initialize() {
	initFont();
	switchMusicButton();
    mode.setUserData(Mode.SINGLE);
    controls.setUserData(Controls.A);
  }
  
  private void initFont() {
	  Font font;
		
		try {
			
			font = Font.loadFont(new FileInputStream(FilePath.DEFAULT_FONT), 20);

		} catch (FileNotFoundException e){
			
			System.out.println("error load font for selection");
			font = Font.font("Sans Serif", 20);

		}
	
	  mode.setFont(font);
	  controls.setFont(font);
	  start.setFont(font);
  }



  @FXML
  public void switchMode() {
  }


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

  @FXML
  public void startGame() {
    ScreenController.INSTANCE.startGame((Controls) controls.getUserData());
  }

  @FXML
  public void switchMusicState() {
    if (AudioPlayer.INSTANCE.isOn())
    	AudioPlayer.INSTANCE.setVolume(0);

     else
    	 AudioPlayer.INSTANCE.setVolume(0.5);

    switchMusicButton();
  }

  
  private void switchMusicButton() {
	  
	  if (AudioPlayer.INSTANCE.isOn()) {
		  	musicon.setVisible(true);
	      	musicoff.setVisible(false);
	    } else {
	    	musicon.setVisible(false);
	    	musicoff.setVisible(true);
	      
	    }

  }
}
