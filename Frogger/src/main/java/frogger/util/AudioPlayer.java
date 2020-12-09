package frogger.util;

import frogger.constant.FilePath;
import frogger.model.PlayerAvatar;
import frogger.model.npc.Obstacle;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public enum AudioPlayer {
	
	INSTANCE;
	
	private static final AudioClip waterDeathAudio = new AudioClip(new File(FilePath.WATERDEATH_AUDIO).toURI().toString());
	private static final AudioClip hopAudio = new AudioClip(new File(FilePath.HOP_AUDIO).toURI().toString());
	private static final AudioClip carDeathAudio = new AudioClip(new File(FilePath.CARDEATH_AUDIO).toURI().toString());
	
	private final Media theme = new Media(new File(FilePath.THEME_AUDIO).toURI().toString());
	private final MediaPlayer themePlayer = new MediaPlayer(theme);
	private double volume = 0.5;

	/**
	 * This method sets the volume of all the audio in this
	 * game
	 * @param volume  {@code double} value of the volume to be set 
	 */
	public void setVolume(double volume) {
		this.volume=volume;
		themePlayer.setVolume(volume);
		hopAudio.setVolume(volume);
		carDeathAudio.setVolume(volume);
		waterDeathAudio.setVolume(volume);
	}

	/**
	 * This method plays the theme song in an indefinite 
	 * loop
	 */
	public void playMusic() {
		themePlayer.setCycleCount(MediaPlayer.INDEFINITE);
	    themePlayer.play();
	}

	/**
	 * This method plays the hop sound for each movement
	 * made by {@link PlayerAvatar} object
	 */
	public void hopSound() {
		 hopAudio.play();
		
	}
	
	/**
	 * This method plays the squash sound when 
	 * {@link PlayerAvatar} object is run over by
	 * {@link Obstacle} object
	 */
	public void squashSound() {
		carDeathAudio.setCycleCount(1);
		carDeathAudio.play();
	}
	
	
	/**
	 * This method plays the drowning sound when 
	 * {@link PlayerAvatar} object dies in the water
	 */
	public void plunkSound() {
		waterDeathAudio.setCycleCount(1);
		waterDeathAudio.play();
	}
	
	/**
	 * This method stops the playing of the theme
	 * song whenever game is paused or exited
	 */
	public void stopMusic() {
		themePlayer.stop();
	}
	
	/**
	 * This method gets the current volume of the 
	 * audio 
	 * @return {@code double} value of the volume
	 */
	public double getVolume() {
		return volume;
	}
	

}
