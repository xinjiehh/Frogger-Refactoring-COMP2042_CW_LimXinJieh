package frogger.util;

import java.util.List;

import frogger.model.Frog;
import frogger.model.Frog.FrogDeath;
import frogger.model.NPC.Actor;
import frogger.model.NPC.Log;
import frogger.model.NPC.Obstacle;
import frogger.model.NPC.Swamp;
import frogger.model.NPC.Turtle;
import frogger.model.NPC.WetTurtle;

public enum CollisionDetector {
	INSTANCE;
	
	
	/*
	 * TODO modified, check for intersecting object once, match class
	 * instead of checking intersecting object for each class
	 * 
	 * This method checks if this player object intersect with any
	 * game elements and handles the situation accordingly 
	 */

	public void checkIntersectingTest(Frog frog, List<Actor> actors) {
		
		Actor intersectingObj = null;
		
		for(Actor actor : actors) {
			
			if(actor!=frog && actor.intersects(frog.getBoundsInLocal())) {
				intersectingObj = actor;
			}
		}

			if(intersectingObj instanceof Obstacle) {
				//CollisionHandler.INSTANCE.handleDeath(frog, FrogDeath.CAR);
				
			} else if ((intersectingObj instanceof Log || intersectingObj instanceof Turtle) && !frog.getNoMove()) {

				CollisionHandler.INSTANCE.handleAttach(frog,intersectingObj.getSpeed());
				
				
			} else if(intersectingObj instanceof WetTurtle) {
				
				WetTurtle wetTurtle = (WetTurtle) intersectingObj;
				if(wetTurtle.isSunk()) {
					//frog.handleDeathTest(FrogDeath.WATER);
					
				} else {
					//frog.attachFrog(intersectingObj.getSpeed(), 0);
					CollisionHandler.INSTANCE.handleAttach(frog,intersectingObj.getSpeed());
					
					
				}
				
			} else if (intersectingObj instanceof Swamp) {
				
				Swamp swamp = (Swamp) intersectingObj;

				//if frog reaches an occupied swamp spot
				if (swamp.isActivated()) {
					//frog.handleDeathTest(FrogDeath.WATER);
				} else if (swamp.hasCroc()) {
					//frog.handleDeathTest(FrogDeath.WATER);
					
				} else {
					
					CollisionHandler.INSTANCE.handleSwamp(frog,swamp);
					
				}
			}
			
			if (intersectingObj==null && frog.getY() < 413) { //if reach this point no intersect and >413 frog is in water
				//frog.handleDeathTest(FrogDeath.WATER);
			
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


