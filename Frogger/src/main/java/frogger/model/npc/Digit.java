package frogger.model.npc;

import frogger.constant.FilePath;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This is the model for digit that is shown on the game
 * screen
 *
 */
public class Digit extends ImageView {
	/** width of all images belonging to this object */
	private static final int WIDTH = 30;
	
	/** height of all images belonging to this object */
	private static final int HEIGHT = 30;

	/**
	 * This is the public constructor for this object
	 * @param n  the integer value of this object
	 * @param x  the x position for this object
	 * @param y  the y position for this object
	 */
	public Digit(int n, int x, int y) {
		setImage(new Image(FilePath.DIGIT_PATH + n + ".png", WIDTH, HEIGHT, true, true));
		setX(x);
		setY(y);
	}

	
}
