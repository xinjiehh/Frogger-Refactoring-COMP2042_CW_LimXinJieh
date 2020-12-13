package frogger.model;

import frogger.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class allows creation of background image
 * of standard style and size, following the width 
 * and height of the {@code Stage} object in {@link 
 * Main}
 * 
 */
public class Background extends ImageView {

	public Background(String url) {
		setImage(new Image(url, Main.STAGE_W, Main.STAGE_H, false, true));
	}

}
