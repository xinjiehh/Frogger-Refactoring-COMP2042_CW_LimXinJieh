package frogger.util;

import java.io.File;

import frogger.constant.FilePath;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public enum AudioPlayer {
	
	INSTANCE;
	
	private static AudioClip waterDeathAudio = new AudioClip(new File(FilePath.WATERDEATH_AUDIO).toURI().toString());
	private static AudioClip hopAudio = new AudioClip(new File(FilePath.HOP_AUDIO).toURI().toString());
	private static AudioClip carDeathAudio = new AudioClip(new File(FilePath.CARDEATH_AUDIO).toURI().toString());
	
	private Media theme = new Media(new File(FilePath.THEME_AUDIO).toURI().toString());
	private MediaPlayer themePlayer = new MediaPlayer(theme);
	private double volume = 0.5;

	
	public void setVolume(double volume) {
		this.volume=volume;
		themePlayer.setVolume(volume);
		hopAudio.setVolume(volume);
		carDeathAudio.setVolume(volume);
		waterDeathAudio.setVolume(volume);
	}
	
	public boolean isOn() {
		return volume!=0;
	}

	

	public void playMusic() {
		themePlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    themePlayer.play();
	}

	
	public void hopSound() {
		 hopAudio.play();
		
	}
	
	public void squashSound() {
		carDeathAudio.setCycleCount(1);
		carDeathAudio.play();
	}
	
	public void plunkSound() {
		waterDeathAudio.setCycleCount(1);
		waterDeathAudio.play();
	}
	
	public void stopMusic() {
		themePlayer.stop();
	}

	public double getVolume() {
		return volume;
	}
	

}
