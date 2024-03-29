package frogger.model.npc;

import frogger.constant.variation.LOG_TYPE;
import frogger.constant.WaterSprite;
import frogger.constant.variation.OBSTACLE_TYPE;
import frogger.model.PlayerAvatar;
import frogger.util.CollisionDetector;

/**
 * Objects of this class acts as a platform for {@link PlayerAvatar} 
 * to hop on
 * 
 * @see CollisionDetector
 */
public class Log extends NPC implements WaterSprite {
	
	/** the type of log to display */
	private final LOG_TYPE type;

	/**
	 * This is the public constructor for this {@code Log} object
	 * @param log  {@link LOG_TYPE} to be created
	 */
	public Log(LOG_TYPE log) {
		super(log);
		this.type = log;

	}

	@Override
	public NPC clone() {
		return new Log(this.type);
	}



	
//	@Override
//	protected void checkOutOfBounds() {
//		if (getX()>600 && speed>0)
//			setX(-180);
//		if (getX()<-size && speed<0)
//			setX(600); //300,225,150
//		
//	}
	
}
