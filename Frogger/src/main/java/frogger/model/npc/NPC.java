package frogger.model.npc;

import frogger.Main;
import frogger.constant.variation.NPCType;
import frogger.model.Actor;
import frogger.model.World;
import frogger.util.animation.SpriteAnimationTemplate;
import javafx.scene.image.Image;

/**
 * This is an abstract base class which specifies an abstract
 * method clone() following the PROTOTYPE pattern. This class
 * also follows TEMPLATE pattern, acting as template to make
 * non player characters. There are hooks (optional steps with
 * empty body) such as {@code playAnimation()} which can be 
 * overridden if needed
 * <p>
 * This base class represents all the non-playing characters
 * such as {@link Log}, {@link Obstacle} and {@link Turtle}
 * which have common properties such as speed and common
 * functions such as automatic animation ({@link 
 * #playAnimation(long)})
 */

public abstract class NPC extends Actor {
	
	/** the distance to be moved by this object in each frame */
	protected double speed; 
	
	/** 
	 * This constructor initializes the size and image of this {@code NPC}
	 * object using the size and image URL defined in the given {@link NPCType}
	 * @param type  {@code NPCType} of this object
	 */
	public NPC(NPCType type) {
		int size = type.getSize();
		Image image = new Image(type.getURL(), size, size, true, true);
		setImage(image);
	}
	
	/**
	 * This is the default public constructor 
	 */
	public NPC() {
		
	}
	
	
	/**
	 * This method will be called by the {@code AnimationTimer}
	 * in {@link World} class
	 * @param now  timestamp of the current frame in nanoseconds
	 * @see World#createMotionTimer()
     */
	@Override
	public void act(long now) {
		move(speed,0);
		playAnimation(now);
	}


	/**
	 * This method is the implementation of PROTOTYPE
	 * method. This method has to be overridden by concrete
	 * subclasses to return the subclass object
	 */
	public abstract NPC clone();
	
	/**
	 * This method sets the coordinates of object
	 * @param x - x-coordinates of the object  
	 * @param y - y-coordinates of the object
	 */
	
	public void setPosition(double x, double y) {
		setX(x);
		setY(y);
		
	}
	
	/**
	 * This method sets the speed of object
	 * @param s  speed of the object
	 */
	
	public void setSpeed(double s) { 
		this.speed = s; 
	}

	
	/**
	 * This method returns the speed of the object
	 * @return {@code integer} speed of object
	 */
	
	public double getSpeed() { 
		return this.speed;
	}
	
    /**
     * This method gets the value of the property parent
     * as a {@link World} object
     * @return parent {@link World} object
     */
    public World getWorld() {
        return (World) getParent();
    }
    
    /**
     * This method returns the horizontal length of this object
     * @return  double value of the width of this object
     */
    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }

    /**
     * This method returns the vertical length of this object
     * @return  double value of the height of this object
     */
    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }

	
	@Override
	protected void checkOutOfBounds() {
		if (getX() > Main.STAGE_W && speed>0)
			setX(-getWidth());
		if (getX() < -getWidth() && speed<0)
			setX(Main.STAGE_W);
	}
	

	/**
	 * This method is a hook which allows subclasses to define and customize
	 * their animation which will be called in each frame. However, there is
	 * an easier, predefined way to create generic animation using {@link
	 * SpriteAnimationTemplate}.
	 * @param now  the timestamp of the current frame given in nanoseconds.
	 */
	protected void playAnimation(long now) {
		
	}


}
