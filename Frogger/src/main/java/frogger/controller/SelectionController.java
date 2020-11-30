package frogger.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import frogger.constant.AudioPlayer;
import frogger.constant.FilePath;
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
  @FXML private TextField nicknameA;
  @FXML private TextField nicknameB;
  @FXML private Button musicon;
  @FXML private Button musicoff;

  private int maxTextLength = 8;
	public enum Controls {
		A, //WASD
		B, //ARROW
		MULTI;
	}
	
	public enum Mode {
		SINGLE,
		DOUBLE;
	}


  @FXML
  public void initialize() {
	initFont();
	updateMusicButton();
    initTextListener(nicknameA);
    initTextListener(nicknameB);
    mode.setUserData(Mode.SINGLE);
    controls.setUserData(Controls.A);
    hideTextFieldB();
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

  private void initTextListener(TextField textField) {
    textField.textProperty().addListener((obs, oldValue, newValue) -> {
      String temp = newValue;
      temp = temp.replaceAll("[^a-zA-Z]", "");
      if (temp.length() > maxTextLength) {
        temp = temp.substring(0, maxTextLength);
      }
      textField.setText(temp);
    });
  }

  @FXML
  public void switchMode() {
    if (mode.getUserData() == Mode.SINGLE) {
      mode.setUserData(Mode.DOUBLE);
      mode.setText("< DOUBLE MODE >");
      showTextFieldB();
      
    } else {
      mode.setUserData(Mode.SINGLE);
      mode.setText("< SINGLE MODE >");
      hideTextFieldB();
    }
  }

  private void showTextFieldB() {
    nicknameB.setVisible(true);
    nicknameB.setDisable(false);
    controls.setVisible(false);
    controls.setDisable(true);
  }

  private void hideTextFieldB() {
    nicknameB.setVisible(false);
    nicknameB.setDisable(true);
    controls.setVisible(true);
    controls.setDisable(false);
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
		    	controls.setText("<       WASD       >");
		        break;
		      case MULTI:
		    	 break;
		    } 
	  

  }

  @FXML
  public void startGame() {
	  if(mode.getUserData() == Mode.DOUBLE)
		  controls.setUserData(Controls.MULTI);
    ScreenController.INSTANCE.startGame((Controls) controls.getUserData(), nicknameA.getText(), nicknameB.getText());
  }

  @FXML
  public void switchMusicState() {
    if (AudioPlayer.INSTANCE.isOn()) {
      AudioPlayer.INSTANCE.setVolume(0);

    } else {
      AudioPlayer.INSTANCE.setVolume(0.5);

    }
    updateMusicButton();
  }

  private void updateMusicButton() {
	  
	  if (AudioPlayer.INSTANCE.isOn()) {
		  	musicon.setVisible(true);
	      	musicoff.setVisible(false);
	    } else {
	    	musicon.setVisible(false);
	    	musicoff.setVisible(true);
	      
	    }

  }
}
