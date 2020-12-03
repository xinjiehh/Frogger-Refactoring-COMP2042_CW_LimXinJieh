package frogger.model.npc;

import frogger.constant.OBSTACLE_TYPE;
import javafx.scene.image.Image;

/**
 * This method is the model for Obstacle
 *
 */
public class Obstacle extends NPC {

	public Obstacle(OBSTACLE_TYPE obstacle) {
		int size = obstacle.getSize();
		setImage(new Image(obstacle.getURL(), size, size, true, true));
	}


	@Override
	protected void checkOutOfBounds() {
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -50 && speed<0)
			setX(600);
	}


	@Override
	protected void playAnimation(long now) {
		
	}
	
	
	

}
