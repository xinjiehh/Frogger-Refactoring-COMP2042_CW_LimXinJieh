package frogger.model.npc;

import frogger.constant.OBSTACLE_TYPE;

/**
 * Objects of this class will cause {@link PlayerAvatar} object to lose
 * life on contact
 * 
 * @see CollisionDetector
 *
 */
public class Obstacle extends NPC {
	
	private OBSTACLE_TYPE type;

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
