package frogger.model.npc;

import frogger.model.Actor;
import frogger.model.World;
import javafx.scene.input.InputEvent;

/**
 * This class represents all the non-playing characters
 * which has a few properties in common 
 */

public abstract class NPC extends Actor {
	
	protected double speed; 
	
    /**
     * @param now  the timestamp of the current frame given in nanoseconds.
     */
	@Override
	public void act(long now) {
		move(speed,0);
		playAnimation(now);
	}
	
	/**
	 * This method allows subclasses to define their 
	 * animation which will be called in each frame
	 * @param now  the timestamp of the current frame given in nanoseconds.
	 */
	protected abstract void playAnimation(long now);

	
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
	
	public void setSpeed(double s) { //TODO test factory
		this.speed = s; 
	}

	
	/**
	 * This method returns the speed of the object
	 * @return speed of object
	 */
	
	public double getSpeed() { 
		return this.speed;
	}
	
	/**
	 * This method increases the speed of this character
	 * object by a given amount 
	 * @param increase  the integer amount of speed to 
	 * increase
	 */
	public void increaseSpeed(int increase) {
		this.speed+=increase;
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
     * This method returns the width of this object
     * @return  double value of the width of this object
     */
    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }

    /**
     * This method returns the height of this object
     * @return  double value of the height of this object
     */
    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }



}
