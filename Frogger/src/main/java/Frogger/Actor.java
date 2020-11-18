package Frogger;

import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;

import java.util.ArrayList;

//TODO is it good to put speed here
public abstract class Actor extends ImageView {
//	protected float x,y;
	protected double speed;

	//protected ObjectID id;
	
	public void setPosition(double x, double y) {
		setX(x);
		setY(y);
		
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
//	public Actor(double x, double y, ObjectID id) {
//		this.x = x;
//		this.y = y;
//		this.id = id;
//		
//	}
	
//	public abstract Rectangle getBounds();

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
    

    public abstract void act(long now);
    
    //public abstract Class<?> getClass();

}
