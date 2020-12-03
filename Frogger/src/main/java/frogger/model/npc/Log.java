package frogger.model.npc;

import frogger.constant.LOG_TYPE;
import javafx.scene.image.Image;

/**
 * This is the model for log
 */
public class Log extends NPC {

	
	public Log(LOG_TYPE name) {
		int size = name.getSize();
		setImage(new Image(name.getURL(), size, size, true, true));

	}

	@Override
	protected void checkOutOfBounds() {
		if (getX()>600 && speed>0)
			setX(-180);
		if (getX()<-300 && speed<0)
			setX(700);
		
	}

	@Override
	protected void playAnimation(long now) {
		
	}

	
}
