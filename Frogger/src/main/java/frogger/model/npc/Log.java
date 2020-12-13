package frogger.model.npc;

import frogger.constant.LOG_TYPE;
import frogger.constant.RiverSprite;
import frogger.model.PlayerAvatar;

/**
 * Objects of this class acts as a platform for {@link PlayerAvatar} 
 * to hop on
 * 
 * @see CollisionDetector
 */
public class Log extends NPC implements RiverSprite {

	private LOG_TYPE type;
	
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
