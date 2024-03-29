package frogger.model.npc;

import frogger.constant.variation.OBSTACLE_TYPE;
import frogger.model.PlayerAvatar;
import frogger.util.CollisionDetector;

/**
 * Objects of this class will cause {@link PlayerAvatar} object to lose
 * life on contact
 * 
 * @see CollisionDetector
 *
 */
public class Obstacle extends NPC {
	
	/** the type of obstacle image to be displayed */
	private final OBSTACLE_TYPE type;


	/**
	 * This is the public constructor for this {@code Obstacle} object
	 * @param obstacle  {@link OBSTACLE_TYPE} to be created
	 */
	public Obstacle(OBSTACLE_TYPE obstacle) {
		super(obstacle);
		this.type=obstacle;
	}

	@Override
	public NPC clone() {
		return new Obstacle(type);
	}
	
	
	
	
//	@Override
//	protected void checkOutOfBounds() {
//		if (getX() > 600 && speed>0)
//			setX(-200);
//		if (getX() < -size && speed<0)
//			setX(600); //50,120,200
//	}
	
//	this.size = obstacle.getSize();
//	setImage(new Image(obstacle.getURL(), size, size, true, true));
//	ImageView a = new ImageView();
//	ColorAdjust colorAdjust = new ColorAdjust();
//	colorAdjust.setContrast(1);     
//    setEffect(colorAdjust); 

}
