package frogger.util;

import java.util.List;

import frogger.constant.DEATH;
import frogger.model.PlayerAvatar;
import frogger.model.World;
import frogger.model.npc.Log;
import frogger.model.npc.NPC;
import frogger.model.npc.Obstacle;
import frogger.model.npc.Sinkable;
import frogger.model.npc.Swamp;
import frogger.model.npc.Turtle;

/**
 * This class implements singleton method. This class is responsible
 * for detecting the intersection of {@link PlayerAvatar} and another
 * {@link NPC} object
 */

public enum CollisionDetector {
	/** singleton instance for this class */
	INSTANCE;
	

	/**
	 * Compared to the original collision detection method which gets objects
	 * different classes and checks for collision, this method first checks 
	 * for any collision of {@link NPC} object with the given {@link PlayerAvatar}, 
	 * then only identifies the subclass of the object and calls the 
	 * {@link CollisionHandler} methods accordingly
	 * <p>
	 * 
	 * @param frog  the player character
	 * @param actors  the {@code NPC} objects in {@link World}
	 */
	public void checkIntersect(PlayerAvatar frog, List<NPC> actors) {
		
		NPC intersectingObj = null;
		
		for(NPC actor : actors) {
			
			if(actor.intersects(frog.getBoundsInLocal())) {
				intersectingObj = actor;
			}
		}

			if(intersectingObj instanceof Obstacle) {
				CollisionHandler.INSTANCE.handleDeath(frog, DEATH.CAR);
				
			} else if ((intersectingObj instanceof Log || intersectingObj instanceof Turtle) && !frog.getNoMove()) {

				CollisionHandler.INSTANCE.handleAttach(frog,intersectingObj.getSpeed());
				
				
			} else if(intersectingObj instanceof Sinkable) {
				
				Sinkable sinkable = (Sinkable) intersectingObj;
				if(sinkable.isSunk()) {
					CollisionHandler.INSTANCE.handleDeath(frog, DEATH.WATER);
					
				} else {
					CollisionHandler.INSTANCE.handleAttach(frog,intersectingObj.getSpeed());
				}
				
			} else if (intersectingObj instanceof Swamp) {
				
				Swamp swamp = (Swamp) intersectingObj;

				//if frog reaches an occupied swamp spot
				if (swamp.isActivated() || swamp.hasCroc()) {
					
					CollisionHandler.INSTANCE.handleDeath(frog, DEATH.WATER);
					
				} else {
					
					CollisionHandler.INSTANCE.handleSwamp(frog,swamp);
					
				}
			}
			
			if (intersectingObj==null && frog.getY() < 413) { //if reach this point no intersect and >413 frog is in water
				CollisionHandler.INSTANCE.handleDeath(frog, DEATH.WATER);
			
		}
	}
	

}

//original check intersecting

//public void checkIntersecting(Frog frog) {
//	Actor actor = frog.getOneIntersectingObject(Actor.class);
//
//	if(actor!=null) {
//
//
//		if(actor instanceof Obstacle) {
//			frog.handleDeathTest(FrogDeath.CAR);
//			//AudioPlayer.INSTANCE.squashSound();
//			System.out.println("here");
//			
//		} else if ((actor instanceof Log || actor instanceof Turtle) && !FrogController.noMove) {
//			
//			frog.attachFrog(actor.getSpeed(), 0);
//			//frog.move(actor.getSpeed(), 0);
//			
//		} else if(actor instanceof WetTurtle) {
//			
//			WetTurtle wetTurtle = frog.getOneIntersectingObject(WetTurtle.class);
//			if(wetTurtle.isSunk()) {
//				//waterDeath=true;
//				
//			} else {
//				frog.attachFrog(actor.getSpeed(), 0);
//				
//			}
//			
//		} else if (actor instanceof End) {
//			
//			End end = frog.getOneIntersectingObject(End.class);
//
//			//if frog reaches an occupied swamp spot
//			if (end.isActivated()) {
//				//waterDeath = true;
//			} else if (end.hasCroc()) {
//				//waterDeath=true;
//				
//			} else {
//				
//				if(end.hasFly()) {
//					frog.addScore(20);
//					System.out.println("fc here");
//					frog.setFlyBonus(true, end.getX()+5);
//				}
//
//				frog.increment();
//				frog.addScore(50);
//				end.setOccupied();
//				restartFrog(frog);
//				
//			}
//		}
//		
//	} else if (frog.getY() < 413) { //if reach this point no intersect and >413 frog is in water
//		//handleDeathTest("water");
//		
//	}
//}


