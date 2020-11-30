package frogger.model;

import java.util.ArrayList;

import javafx.scene.input.KeyEvent;

//template
public abstract class PlayerAvatar extends Actor {

	public static final double START_YPOS = 706.467;//679.8 + FrogModel.movement;
	protected static final double START_XPOS = 300;
	public static final double LOWER_BOUND = 173.13;
	public static final double UPPER_BOUND = 800;
	public static final double MOVEMENT = 13.3333333 * 2;
	public static final double MOVEMENT_X = 10.666666 * 2;
	//get objects in World class by class, then check if they intersect with actor
	//pass world objects into method, then handle
	
	
	@Deprecated
    public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
        ArrayList<A> someArray = new ArrayList<A>();
        System.out.println("deprecated method used");
        for (A actor: getWorld().getObjects(cls)) { //gameElements.getList()
        	//get objects of class, check if each intersects with player object
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
            }
        }
        return someArray;
    }
    
    @Deprecated
    public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
        A intersectingObj = null;
        System.out.println("deprecated method used");
        
        if(getWorld()!=null) {
        	
        	for (A actor: getWorld().getObjects(cls)) { 
            	//if actor of class CLS intersects this,
                if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                    intersectingObj = actor;
                    break;
                }
            }
        	
        }
        
        return intersectingObj;
    }
    
    @Override
    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
        checkOutOfBounds();
        

    }
    
	/**
	 * This method checks if this player object is out of visible 
	 * bounds and reset player object position accordingly
	 */
	
    public void checkOutOfBounds() {
        if (getY() > START_YPOS) {
			setY(START_YPOS);
		}	
		 if (getX() < 0 || getX() > 556) {
			double m = getX() > 556 ? 556 : 0;
			setX(m);
			
		}
    }


//    public abstract void handleKeyPress(KeyEvent event);
//    public abstract void handleKeyRelease(KeyEvent event);



}
