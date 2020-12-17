package frogger.model;

import javafx.scene.image.ImageView;

/**
 * This class allows to create an object with basic properties 
 * such as x-position and y-position and common functions
 * such as moving ({@link #move}) and animation ({@link #act})
 */

public abstract class Actor extends ImageView {

	/**
	 * This method 'moves' the object by shifting its original 
	 * x- and y-position by a given value
	 * 
	 * @param dx  horizontal distance to be shifted 
	 * @param dy  vertical distance to be shifted
	 */
	
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
		checkOutOfBounds();
	}
	/**
	 * This method will be called by the {@code AnimationTimer}
	 * in {@link World} class
	 * @param now  timestamp of the current frame in nanoseconds
	 * @see World#createMotionTimer()
     */
	public abstract void act(long now);
	
	/**
	 * This method must be overridden by subclasses to check if
	 * this object is out of bounds and carry out the necessary 
	 * steps to correct it
	 */
	protected abstract void checkOutOfBounds();
	


}