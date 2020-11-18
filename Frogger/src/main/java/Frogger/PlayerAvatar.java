package Frogger;

import java.util.ArrayList;

public abstract class PlayerAvatar extends Actor {

	protected static final double START_YPOS = 679.8 + FrogModel.movement;
	protected static final double START_XPOS = 300;
	protected static final double LOWER_BOUND = 173;
	protected static final double UPPER_BOUND = 800;
	
	//get objects in World class by class, then check if they intersect with actor
	//pass world objects into method, then handle
	
	

    protected <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
        ArrayList<A> someArray = new ArrayList<A>();
        for (A actor: getWorld().getObjects(cls)) { //gameElements.getList()
        	//get objects of class, check if each intersects with player object
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
            }
        }
        return someArray;
    }
    
    
    protected <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
        A intersectingObj = null;
        for (A actor: getWorld().getObjects(cls)) { 
        	//if actor of class CLS intersects this,
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                intersectingObj = actor;
                break;
            }
        }
        return intersectingObj;
    }
    

    protected abstract void createKeyListeners();
    



}
