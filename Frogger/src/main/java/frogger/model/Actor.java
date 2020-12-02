package frogger.model.NPC;

import frogger.model.World;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;

/**
 * This class allows to create an object with basic properties 
 * such as x-position, y-position and speed
 * 
 * @author Lim Xin Jieh (20082200)
 *
 */

public abstract class Actor extends ImageView {

	protected double speed; 
	
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
	 * @param s - speed of the object
	 */
	
	public void setSpeed(double s) { //TODO test factory
		this.speed = s; 
	}

	
	/**
	 * This method returns the speed of the object
	 * @return speed of object
	 */
	
	public double getSpeed() { //TODO test factory
		return this.speed;
	}
	
	//TEST TODO
	public void increaseSpeed(int increase) {
		this.speed+=increase;
	}
	
	/**
	 * This method 'moves' the object by shifting its original 
	 * x- and y-position by a given value
	 * 
	 * @param dx - horizontal distance to be shifted 
	 * @param dy - vertical distance to be shifted
	 */
    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
    
    
    public World getWorld() {
        return (World) getParent();
    }
    

    public double getWidth() {
        return this.getBoundsInLocal().getWidth();
    }

    public double getHeight() {
        return this.getBoundsInLocal().getHeight();
    }

    public void manageInput(InputEvent e) {
        
    }
    

    /**
     * {@inheritdoc}
     * @param now - the timestamp of the current frame given in nanoseconds.
     */
    public abstract void act(long now);
 

}