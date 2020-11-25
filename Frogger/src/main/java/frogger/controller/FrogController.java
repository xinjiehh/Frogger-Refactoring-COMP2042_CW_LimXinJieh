package frogger.controller;


import frogger.model.Actor;
import frogger.model.End;
import frogger.model.Frog;
import frogger.GamePane;
import frogger.model.Log;
import frogger.model.Obstacle;
import frogger.model.Turtle;
import frogger.model.WetTurtle;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import frogger.DeathAnimation;
import frogger.model.Frog.FrogDeath;

public class FrogController {
	
	private boolean isJump = false;
	private Frog frog;
	private AnimationTimer collisionTimer;
	private int tempScore = 0;
	public boolean noMove = false; 

	
	public FrogController(Frog frog) {
		this.frog=frog;
		createCollisionTimer();
		
	}
	boolean isMoving=false;
	public void test () {
		if(!noMove) {
			
		}
	}
	public void handleKeyPress(KeyEvent event) {
		if (!noMove) {
			KeyCode keyCode = event.getCode();
			Image img;
			switch (keyCode) {
			case W:
			case UP:
				img = isJump ? Frog.imgW1 : Frog.imgW2;
				moveAnim(0, -Frog.MOVEMENT, img);
				break;

			case A:
			case LEFT:
				img = isJump ? Frog.imgA1 : Frog.imgA2;
				moveAnim(-Frog.MOVEMENT_X, 0, img);
				break;

			case S:
			case DOWN:
				img = isJump ? Frog.imgS1 : Frog.imgS2;
				moveAnim(0, Frog.MOVEMENT, img);
				break;

			case D:
			case RIGHT:
				img = isJump ? Frog.imgD1 : Frog.imgD2;
				moveAnim(Frog.MOVEMENT_X, 0, img);
				break;
			default:
				break;

			}	
			isJump = isJump ? false : true;

		} 

	}
	
	public void handleKeyRelease(KeyEvent event) {
		
		if (!noMove) {
			isJump = false;
			KeyCode keyCode = event.getCode();
			switch (keyCode) {

			case W:
			case UP:
				if (frog.getY() < Frog.UPPER_BOUND && frog.getY() > Frog.LOWER_BOUND) {
					frog.addScore(10);
					tempScore += 10;
				}

				moveAnim(0, -Frog.MOVEMENT, Frog.imgW1);
				break;

			case A:
			case LEFT:
				moveAnim(-Frog.MOVEMENT_X, 0, Frog.imgA1);
				System.out.println("here "  + frog.getY());
				break;

			case S:
			case DOWN:
				if (frog.getY() < Frog.START_YPOS) {
					frog.addScore(-10);
					tempScore -= 10;
					moveAnim(0, Frog.MOVEMENT, Frog.imgS1);
					break;
				}

			case D:
			case RIGHT:
				moveAnim(Frog.MOVEMENT_X, 0, Frog.imgD1);
				break;
			default:
				break;

			}
		} 
	

	}
	/**
	 * This method changes the x-position, y-position and image
	 * property of the frog object
	 * @param dx - horizontal distance to be moved
	 * @param dy - vertical distance to be moved
	 * @param value - Image object
	 */
	public void moveAnim(double dx, double dy, Image value) {
		frog.move(dx, dy);
		frog.setImage(value);
	}
	
	private void handleDeathTest(FrogDeath death) {
	
		if(frog.getDeath()==FrogDeath.NULL) {
			playsound(death);
			frog.addScore(-tempScore);
			DeathAnimation test = new DeathAnimation(frog, death);
			test.play();
			frog.setDeath(death);
		}
		
	}
	
	
	/**
	 * This method checks if this player object is out of visible 
	 * bounds and reset player object position accordingly
	 */
	
	private void checkOutOfBounds() {
		
		
		if (frog.getY() > Frog.START_YPOS) {
			frog.setY(Frog.START_YPOS);
		}
		
		else if (frog.getX() < 0 || frog.getX() > 600) {
			
			double m = frog.getX() > 600 ? -Frog.MOVEMENT : Frog.MOVEMENT;
			frog.move(m * 2, 0);
			
		}
		
	}

	
	/*
	 * TODO modified, check for intersecting object once, match class
	 * instead of checking intersecting object for each class
	 * 
	 * This method checks if this player object intersect with any
	 * game elements and handles the situation accordingly 
	 */
	private void checkIntersecting() {
		
		Actor actor = frog.getOneIntersectingObject(Actor.class);

		if(actor!=null) {


			if(actor instanceof Obstacle) {
				handleDeathTest(FrogDeath.CAR);
				System.out.println("here");
				
			} else if ((actor instanceof Log || actor instanceof Turtle) && !noMove) {
				
				frog.attachFrog(actor.getSpeed(), 0);
				//frog.move(actor.getSpeed(), 0);
				
			} else if(actor instanceof WetTurtle) {
				
				WetTurtle wetTurtle = frog.getOneIntersectingObject(WetTurtle.class);
				if(wetTurtle.isSunk()) {
					//waterDeath=true;
					
				} else {
					frog.attachFrog(actor.getSpeed(), 0);
					
				}
				
			} else if (actor instanceof End) {
				
				End end = frog.getOneIntersectingObject(End.class);

				//if frog reaches an occupied swamp spot
				if (end.isActivated()) {
					//waterDeath = true;
				} else if (end.hasCroc()) {
					//waterDeath=true;
					
				} else {
					
					if(end.hasFly()) {
						frog.addScore(20);
						frog.setFlyBonus(true, end.getX());
					}

					frog.increment();
					frog.addScore(50);
					end.setOccupied();
					restartFrog();
					
				}
			}
			
		} else if (frog.getY() < 413) { //if reach this point no intersect and >413 frog is in water
			//handleDeathTest("water");
			
		}
		
		
	}
	
	/**
	 * This method plays death audio according to the type of death
	 * @param str -"car" for car death and "water" for water death
	 */
	public void playsound(FrogDeath death) {
		if(death==FrogDeath.CAR)
			GamePane.squashSound();
		else if(death==FrogDeath.WATER)
			GamePane.plunkSound();
	}
	
	/**
	 * This method resets frog to its starting state
	 */
	public void restartFrog() {
		frog.setStartFrog();
		noMove = false;
		tempScore=0;
//		if (score > 50) {
//			score -= 50;
//			changeScore = true;
//		}
	}

	/**
	 * This method sets the property noMove which controls if
	 * the frog is able to move or not
	 * @param bool
	 */
	public void setNoMove(boolean bool) {
		noMove=bool;
	}
	
	
	
	//Timer-related methods
	
	/**
	 * This method creates a timer that checks if this player object goes 
	 * out of visible bounds or intersects with other game element objects and 
	 * handles each case accordingly
	 */
	private void createCollisionTimer() {
		
		collisionTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				checkIntersecting();
				checkOutOfBounds();
			}
			
		};
		
		collisionTimer.start();
	
	}
	/**
	 * This method stops the collision timer
	 */
	public void stopTimer() {
		collisionTimer.stop();
	}
	
	/**
	 * This method starts the collision timer
	 */
	public void startTimer() {
		collisionTimer.start();
	}
	
	



}
