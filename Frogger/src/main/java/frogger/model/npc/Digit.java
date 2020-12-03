package frogger.model.npc;

import frogger.constant.FilePath;
import frogger.model.Actor;
import javafx.scene.image.Image;

/**
 * This is the model for digit
 *
 */
public class Digit extends Actor {
	
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;


	public Digit(int n, int x, int y) {
		setImage(new Image( FilePath.DIGIT_PATH + n + ".png", WIDTH, HEIGHT, true, true));
		setX(x);
		setY(y);
	}
	
	@Override
	public void act(long now) {
	}
	
	@Override
	protected void checkOutOfBounds() {
	}
	

	
}
