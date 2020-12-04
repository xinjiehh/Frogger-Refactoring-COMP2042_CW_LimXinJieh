package frogger.model;


import frogger.constant.DIRECTION;
import frogger.constant.FilePath;
import frogger.constant.FrogDeath;
import frogger.util.AudioPlayer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;

/**
 * This class defines a {@link PlayerAvatar} of type Frog
 * which has properties such as {@link #lifeProp} and 
 * {@link #scoreProp} and basic functions such as {@link 
 * #jump(DIRECTION, boolean)}
 */

public class Frog extends PlayerAvatar {
	
	//Frog image initialization
	private static final int IMG_SIZE = 40;
	private static final Image imgW1 = new Image(FilePath.FROG_UP, IMG_SIZE, IMG_SIZE, true, true);
	private static final Image imgA1 = new Image(FilePath.FROG_LEFT, IMG_SIZE, IMG_SIZE, true, true);
	private static final Image imgS1 = new Image(FilePath.FROG_DOWN, IMG_SIZE, IMG_SIZE, true, true);
	private static final Image imgD1 = new Image(FilePath.FROG_RIGHT, IMG_SIZE, IMG_SIZE, true, true);
	private static final Image imgW2 = new Image(FilePath.FROG_UPJUMP, IMG_SIZE, IMG_SIZE, true, true);
	private static final Image imgA2 = new Image(FilePath.FROG_LEFTJUMP, IMG_SIZE, IMG_SIZE, true, true);
	private static final Image imgS2 = new Image(FilePath.FROG_DOWNJUMP, IMG_SIZE, IMG_SIZE, true, true);
	private static final Image imgD2 = new Image(FilePath.FROG_RIGHTJUMP, IMG_SIZE, IMG_SIZE, true, true);
	
	/** keeps track of the number of lives left */
	private IntegerProperty lifeProp;
	
	/** keeps track of the score of this {@code Frog} object throughout a level */
	private IntegerProperty scoreProp;
	
	/** keep track of the score of this frog object before it reaches the swamp */
	private int tempScore = 0;

	/** type of frog death */
	private FrogDeath frogDeath = FrogDeath.NULL;
	
	/** flag to determine if frog is mid jump */
	private boolean isJump = false;
	
	/** this determines if frog is able to move or not */
	private boolean noMove = false; 

	
	/**
	 * This method is a public constructor which sets this 
	 * {@code Frog} object and its properties to their starting 
	 * state, and initializes life and score of this {@code Frog} 
	 * object
	 * 
	 */
	public Frog() {
		restartFrog();
		lifeProp = new SimpleIntegerProperty(3);
		scoreProp = new SimpleIntegerProperty(0);
	}
	
	@Override
	public void act(long now) {
		
	}
	

	/**
	 * This method sets the property {@link #tempScore} which is 
	 * the temporary score that this {@code Frog} object accumulates 
	 * while travelling across the screen
	 * @param i  temporary score of this {@code Frog} object
	 */
	public void setTempScore(int i) {
		tempScore=i;
	}
	
	/**
	 * This method returns the boolean property {@link #noMove}
	 * which controls if the frog is able to move or not
	 * @return  true if this {@code Frog} object cannot move
	 */
	public boolean getNoMove() {
		return noMove;
	}
	
	
	/**
	 * This method handles the jump visual and audio for this 
	 * {@code Frog} object.
	 * @param direction  {@link DIRECTION} representing the frog jump direction
	 * @param keyPress  true if key pressed, false if key released
	 */
	public void jump(DIRECTION direction, boolean keyPress) {
		if (!noMove) {
			
			if(!keyPress && isJump) {
				isJump = false;
			} 
			
			if(keyPress) {
				AudioPlayer.INSTANCE.hopSound();
			}
		
			
			Image img;
			switch (direction) {
			case UP:
				img = isJump ?  Frog.imgW2 : Frog.imgW1;
				moveAnim(0, -Frog.MOVEMENT, img);
				if(!keyPress) checkUpY();
				break;

			case LEFT:
				img = isJump ? Frog.imgA2 : Frog.imgA1;
				moveAnim(-Frog.MOVEMENT_X, 0, img);
				break;

			case DOWN:
				img = isJump ? Frog.imgS2 : Frog.imgS1;
				moveAnim(0, Frog.MOVEMENT, img);
				if(!keyPress) checkDownY();
				break;

			case RIGHT:
				img = isJump ? Frog.imgD2 : Frog.imgD1;
				moveAnim(Frog.MOVEMENT_X, 0, img);
				break;
			default:
				break;

			}	
			
			isJump = isJump ? false : true;
		} 
	}

	
	/**
	 * This method resets this {@code Frog} object and its properties
	 * to its starting state
	 */
	public void restartFrog() {
		setStartFrog();
		noMove = false;
		tempScore = 0;

	}
	
	/**
	 * This method sets this {@code Frog} object in starting position
	 */
	private void setStartFrog() {
		setImage(imgW1);
		setX(START_XPOS);
		setY(START_YPOS);
	}

	/**
	 * This method sets the property {@link #noMove} which determines if
	 * the frog is allowed to move or not
	 * @param bool  true to allow movement, false otherwise
	 */
	public void setNoMove(boolean bool) {
		noMove=bool;
	}


	
	/**
	 * This method sets the score of this {@code Frog}
	 * @param i  new score of this {@code Frog}
	 */
	public void setScore(int i) {
		scoreProp.setValue(i);
	}
	
	
	/**
	 * This method returns integer value of the score, which is
	 * {@link #scoreProp} of this {@code Frog} object
	 * object
	 * @return  integer value of the score of this {@code Frog} object
	 */
	public int getScore() {
		return scoreProp.intValue();
	}
	
	/**
	 * This method adds the given integer amount to the current 
	 * score, which is {@link #scoreProp} of this {@code Frog} 
	 * object
	 * @param x  the integer amount of points to be added
	 */
	
	public void addScore(int x) {
		scoreProp.setValue(scoreProp.intValue()+x);
	}
	
	/**
	 * This method allows other classes to add listener to 
	 * the IntegerProperty {@link #scoreProp} which corresponds to the
	 * score of this {@code Frog} object
	 * @param listener  {@code ChangeListener} for {@code #scoreProp}
	 * 
	 */
	public void addScoreListener(ChangeListener<? super Number> listener) {
		scoreProp.addListener(listener);
	}
	
	/**
	 * This method allows other classes to add a {@code ChangeListener}
	 * which will be notified whenever there is a change in the
	 * IntegerProperty {@link #lifeProp} which corresponds to the lives
	 * of this {@code Frog} object
	 * @param listener  {@code ChangeListener} for {@code #lifeProp}
	 */
	public void addLifeListener(ChangeListener<? super Number> listener) {
		lifeProp.addListener(listener);
	}
	
	/**
	 * This method sets the death state {@link #FrogDeath} of 
	 * this object
	 * @param death  {@code #FrogDeath} representing the death type
	 * of this {@code Frog} object
	 */
	public void setDeath(FrogDeath death) {
		frogDeath=death;
	}
	
	/**
	 * This method gets the death state of this {@code Frog} 
	 * object
	 * @return  {@link #FrogDeath} representing the death state
	 */
	public FrogDeath getDeath() {
		return frogDeath;
	}
	
	/**
	 * This method reduces the lives of this {@code Frog} object 
	 * ({@link #lifeProp} by 1
	 */
	public void loseLife() {
		 lifeProp.setValue(lifeProp.intValue()-1);
		 System.out.println("Life left: " + lifeProp.toString());

	}
	
	/**
	 * This method enables this {@code Frog} object to 'attach' to 
	 * another moving character by following their speed
	 * @param dx  horizontal distance to be moved
	 * @param dy  vertical distance to be moved
	 */
	public void attachFrog(double dx, double dy) {
		move(dx, dy);
	}
	

	
	/**
	 * This method changes the x-position, y-position and image
	 * property of the frog object
	 * @param dx  horizontal distance to be moved
	 * @param dy  vertical distance to be moved
	 * @param value  Image object
	 */
	private void moveAnim(double dx, double dy, Image value) {
		move(dx, dy);
		setImage(value);
	}

	/**
	 * This method is called whenever this Frog object dies so that 
	 * the points gained is deducted. However, marks gained by other 
	 * Frog objects will not be affected
	 */
	public void resetScore() {
		addScore(-tempScore);
		tempScore=0;
	}
	
	

	/**
	 * This method checks the downward movement of this Frog object
	 * and deducts marks accordingly
	 */
	private void checkDownY() {
		/*frog moving downwards (but above starting pos.)
		deducts marks*/
		if (getY() < Frog.START_YPOS && getY() > Frog.LOWER_BOUND+1) { 
			addScore(-10);
			tempScore -= 10;
		}
		
	}

	/**
	 * This method checks the upward movement of this Frog object
	 * and adds marks accordingly
	 */
	private void checkUpY() {
		if(getDeath()==FrogDeath.NULL) {
			if (getY() < Frog.UPPER_BOUND && getY() > Frog.LOWER_BOUND) {
				addScore(10);
				tempScore += 10;
			}
		}

	}

	

}


//public int getScore() {
//return score;
//}

//public boolean changeScore() {
//if (changeScore) {
//	changeScore = false;
//	return true;
//} else
//	return false;
//
//}



//public boolean gameOver() { 
//if(swampFrogCount==MAX_FROG) {
//	swampFrogCount=0;
//	return true;
//	
//} else 
//	return false;
//}





//public static final String pref = "frogger";
//public final ArrayList<Image> moveImg = new ArrayList<>(){
//	{
//		add(new Image(pref + "Up.png", IMG_SIZE, IMG_SIZE, true, true));
//		add(new Image(pref + "Down.png", IMG_SIZE, IMG_SIZE, true, true));
//		add(new Image(pref + "Left.png", IMG_SIZE, IMG_SIZE, true, true));
//		add(new Image(pref + "Right.png", IMG_SIZE, IMG_SIZE, true, true));
//		
//	}
//	
//	
//};


//public int tempScore = 0;
//protected boolean isJump = false;
//private ArrayList<End> inter = new ArrayList<End>();
//public int deathAnimCtr = 0;
//private boolean carDeath = false;
//private boolean waterDeath = false;
//
///**
// * This method checks if this player object is out of visible 
// * bounds and reset player object position accordingly
// */
//private void checkOutOfBounds() {
//	
//	if (getY() > START_YPOS) {
//		setY(START_YPOS);
//	}
//	
//	else if (getX() < 0 || getX() > 600) {
//		
//		double m = getX() > 600 ? -movement : movement;
//		move(m * 2, 0);
//		
//	}
//	
//}
//
//
///*
// * TODO modified, check for intersecting object once, match class
// * instead of checking intersecting object for each class
// * 
// * This method checks if this player object intersect with any
// * game elements and handles the situation accordingly 
// */
//private void checkIntersecting() {
//	
//	Actor actor = getOneIntersectingObject(Actor.class);
//	
//	if(actor!=null) {
//
//
//		if(actor instanceof Obstacle) {
//			//carDeath=true;
//			
//			
//		} else if ((actor instanceof Log || actor instanceof Turtle) && !noMove) {
//			
//			move(actor.getSpeed(), 0);
//			
//		} else if(actor instanceof WetTurtle) {
//			
//			WetTurtle wetTurtle = getOneIntersectingObject(WetTurtle.class);
//			if(wetTurtle.isSunk()) {
//				
//				//waterDeath=true;
//				
//			} else {
//				move(actor.getSpeed(), 0);
//				
//			}
//			
//		} else if (actor instanceof End) {
//			
//			End end = getOneIntersectingObject(End.class);
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
//					score+=20;
//					flyBonus = true;
//					currentXPos = end.getX();
//				}
//
//				swampFrogCount++;
//				score += 50;
//				tempScore = 0;
//				end.setOccupied();
//				changeScore = true;
//				restartFrog();
//				
//			}
//		}
//		
//	} else if (getY() < 413) { //if reach this point no intersect and >413 frog is in water
//		//waterDeath=true;
//		
//	}
//	
//	
//}
//
//
//private void handleDeath(long now) {
//
//	String deathType = waterDeath? "waterdeath" : "cardeath";
//	noMove = true;
//	if ((now) % 11 == 0) {
//		deathAnimCtr++;
//	}
//
//	setDeathImage(deathType);
//}
//
//private void setDeathImage(String deathType) {
//
//	// if end of death animation
//	if (deathAnimCtr >= 5) {
//		restartFrog();
//		waterDeath = false; 
//		carDeath=false;
//		score-=tempScore;
//		changeScore = true;
//		tempScore = 0;
//		//deathAnimCtr = 0; TODO
//
//	} else if (deathAnimCtr > 0 && deathAnimCtr < 5) {
//		
//		if(deathAnimCtr==1) {
//			
//			if(carDeath) {
//				GamePane.carDeathSound();
//			}
//			
//			else if(waterDeath) {
//				GamePane.waterDeathSound();
//			}
//				
//		}
//		
//		setImage(new Image("file:src/main/resources/" + deathType + deathAnimCtr + ".png", imgSize, imgSize, true, true));
//
//	}
//
//}
//




//now: The timestamp of the current frame given in nanoseconds. 
//This value will be the same for all AnimationTimers called
//during one frame.



//public void setNoMove(boolean b) {
//	noMove=b;
//}

//@Override
//public void handleKeyPress(KeyEvent event) {
//	if (!noMove) {
//		KeyCode keyCode = event.getCode();
//		Image img;
//		switch (keyCode) {
//		case W:
//		case UP:
//			System.out.println("key pressed up " + isJump);
//			img = isJump ? imgW1 : imgW2;
//			moveAnim(0, -movement, img);
//			break;
//
//		case A:
//		case LEFT:
//			img = isJump ? imgA1 : imgA2;
//			moveAnim(-movementX, 0, img);
//			break;
//
//		case S:
//		case DOWN:
//			img = isJump ? imgS1 : imgS2;
//			moveAnim(0, movement, img);
//			break;
//
//		case D:
//		case RIGHT:
//			img = isJump ? imgD1 : imgD2;
//			moveAnim(movementX, 0, img);
//			break;
//		default:
//			break;
//
//		}
//		System.out.println("is Jump "  + isJump);		
//		isJump = isJump ? false : true;
//
//	} 
//
//}
//
//@Override
//public void handleKeyRelease(KeyEvent event) {
//
//		if (!noMove) {
//			isJump = false;
//			KeyCode keyCode = event.getCode();
//
//			switch (keyCode) {
//
//			case W:
//			case UP:
//				System.out.println("up release");
//				if (getY() < UPPER_BOUND && getY() > LOWER_BOUND) {
//					
//					score += 10;
//					changeScore = true;
//					System.out.println(tempScore);
//					tempScore += 10;
//					
//				}
//
//				moveAnim(0, -movement, imgW1);
//				break;
//
//			case A:
//			case LEFT:
//				moveAnim(-movementX, 0, imgA1);
//				System.out.println("here "  + getY());
//				break;
//
//			case S:
//			case DOWN:
//				if (getY() < START_YPOS) {
//					score -= 10;
//					changeScore = true;
//					tempScore -= 10;
//					moveAnim(0, movement, imgS1);
//					break;
//				}
//
//			case D:
//			case RIGHT:
//				moveAnim(movementX, 0, imgD1);
//				break;
//			default:
//				break;
//
//			}
//		} 
//	
//
//}


//@Override
//protected void createKeyListeners() {
//	
//
//	setOnKeyPressed(new EventHandler<KeyEvent>() {
//		public void handle(KeyEvent event) {
//			if (!noMove) {
//				
//				KeyCode keyCode = event.getCode();
//				Image img;
//
//				switch (keyCode) {
//				case W:
//				case UP:
//					System.out.println("key pressed up " + isJump);
//					img = isJump ? imgW1 : imgW2;
//					moveAnim(0, -movement, img);
//					
//					break;
//
//				case A:
//				case LEFT:
//					img = isJump ? imgA1 : imgA2;
//					moveAnim(-movementX, 0, img);
//					break;
//
//				case S:
//				case DOWN:
//					img = isJump ? imgS1 : imgS2;
//					moveAnim(0, movement, img);
//					break;
//
//				case D:
//				case RIGHT:
//					img = isJump ? imgD1 : imgD2;
//					moveAnim(movementX, 0, img);
//					break;
//				default:
//					break;
//
//				}
//				
//				isJump = isJump ? false : true;
//
//			} 
//		}
//	});
//
//	setOnKeyReleased(new EventHandler<KeyEvent>() {
//		public void handle(KeyEvent event) {
//			if (!noMove) {
//				isJump = false;
//				KeyCode keyCode = event.getCode();
//
//				switch (keyCode) {
//
//				case W:
//				case UP:
//					System.out.println("up release");
//					if (getY() < UPPER_BOUND && getY() > LOWER_BOUND) {
//						
//						score += 10;
//						changeScore = true;
//						System.out.println(tempScore);
//						tempScore += 10;
//						
//					}
//
//					moveAnim(0, -movement, imgW1);
//					break;
//
//				case A:
//				case LEFT:
//					moveAnim(-movementX, 0, imgA1);
//					System.out.println("here "  + getY());
//					break;
//
//				case S:
//				case DOWN:
//					if (getY() < START_YPOS) {
//						score -= 10;
//						changeScore = true;
//						tempScore -= 10;
//						moveAnim(0, movement, imgS1);
//						break;
//					}
//
//				case D:
//				case RIGHT:
//					moveAnim(movementX, 0, imgD1);
//					break;
//				default:
//					break;
//
//				}
//			} 
//		}
//
//	});
//}
//
//


///ORIGINAL 

//if (getOneIntersectingObject(Obstacle.class)!=null) {
//	//carDeath = true;
//}
//
//if (getOneIntersectingObject(Log.class)!= null && !noMove) { //can still move
//
//	double speed = getIntersectingObjects(Log.class).get(0).getSpeed();
//	move(speed, 0);
//	
//} else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
//	double speed = getIntersectingObjects(Turtle.class).get(0).getSpeed();
//	move(speed, 0);
//	
//} else if (getOneIntersectingObject(WetTurtle.class) != null) { //can or cannot move
//	if (getOneIntersectingObject(WetTurtle.class).isSunk()) {
//		//waterDeath = true;
//		
//	} else {
//		double speed = getOneIntersectingObject(WetTurtle.class).getSpeed();
//		move(speed, 0);
//	}
//} else if (getIntersectingObjects(End.class).size() >= 1) {
//	inter = (ArrayList<End>) getIntersectingObjects(End.class);
//	
//	//if frog reaches an occupied swamp spot
//	if (inter.get(0).isActivated()) {
//		//waterDeath = true;
//	} else {
//		swampFrogCount++;
//		score += 50;
//		tempScore = 0;
//		getIntersectingObjects(End.class).get(0).setOccupied();
//		changeScore = true;
//		restartFrog();
//		
//	}
//
//	
//} else if (getY() < 413) { //if reach this point no intersect and >413 frog is in water
//	//waterDeath=true;
//	
//}
//private void setWaterDeathImage() {
//
//	// if end of death animation
//	if (carD >= 5) {
//		restartFrog();
//		waterDeath = false; //carDeath=false;
//		score-=tempScore;
//		changeScore = true;
//		tempScore=0;
//
//	} else if (carD > 0 && carD < 5) {
//
//		setImage(new Image("file:src/main/resources/waterdeath" + carD + ".png", FrogModel.imgSize, FrogModel.imgSize, true, true));
//
//	}
//
//}
//
//private void setCarDeathImage() {
//
//	if (carD == 4) {
//		
//		restartFrog();
//		carDeath = false;
//		score-=tempScore;
//		changeScore = true;
//		tempScore = 0;
//
//	} else if (carD > 0 && carD < 4) {
//		setImage(new Image("file:src/main/resources/cardeath" + carD + ".png", FrogModel.imgSize, FrogModel.imgSize, true, true));
//
//	}
//}



//private void handleCarDeath2(long now) {
//noMove = true;
//score-=tempScore;
//changeScore = true;
//tempScore = 0;
////carDeathAnim("cardeath");
//carDeathAnim("cardeath");
//
//}
////working, small delay
//public void carDeathAnim(String death) {
//	//meant to replace setCarDeathImage without param now
//	String imgPath = "file:src/main/resources/" + death;
//	System.out.println(imgPath);
//	int duration = 100;
//	Animation animation1 = new Transition() {
//		
//
//		{
//			setCycleDuration(Duration.millis(duration));
//		}
//		
//		@Override
//		protected void interpolate(double frac) {
//			setImage(new Image(imgPath + "1" + ".png", 
//						FrogModel.imgSize, FrogModel.imgSize, true, true));
//
//				
//			
//			
//			
//		}
//		
//	};
//	
//	Animation animation2 = new Transition() {
//		
//		
//		{
//			setCycleDuration(Duration.millis(duration));
//		}
//		
//		@Override
//		protected void interpolate(double frac) {
//			setImage(new Image(imgPath + "2" + ".png", 
//						FrogModel.imgSize, FrogModel.imgSize, true, true));
//
//				
//			
//			
//			
//		}
//		
//	};
//	
//	Animation animation3 = new Transition() {
//		
//		
//		{
//			setCycleDuration(Duration.millis(duration));
//		}
//		
//		@Override
//		protected void interpolate(double frac) {
//			setImage(new Image(imgPath + "3" + ".png", 
//						FrogModel.imgSize, FrogModel.imgSize, true, true));
//
//				
//			
//			
//			
//		}
//		
//	};
//	
//	SequentialTransition loop = new SequentialTransition(animation1,animation2,animation3);
//	
//	loop.play();
//	loop.setOnFinished(new EventHandler<ActionEvent>() {
//
//		@Override
//		public void handle(ActionEvent event) {
//			//animation.stop();
//
//			restartFrog();
//			
//		}
//		
//		
//	});
//	
//	
//	
//}



//private void handleCarDeath(long now) {
//	noMove = true;
//	if ((now) % 11 == 0) {
//		carD++;
//	}
//
//	setCarDeathImage();
//	
//}


//private void setCarDeathImage() {
//	if(carD==3) {
//		score-=tempScore;
//		changeScore = true;
//		tempScore = 0;
//	}
//
//	if (carD == 4) {
//		changeScore = false;
//		carDeath = false;
//		restartFrog();
//
//	} else if (carD > 0 && carD < 4) {
//		setImage(new Image("file:src/main/resources/cardeath" + carD + ".png", FrogModel.imgSize, FrogModel.imgSize, true, true));
//
//	}
//}
////
//

////////////use timeline, not working
//public void setCarDeathImage2() {
//	//meant to replace original without param now
//	
//	Timeline timeline = new Timeline();
//
//	int seconds = 0;
//	for(int i=0; i<3;i++) {
//		seconds +=100;
//		timeline.getKeyFrames().add(
//				new KeyFrame(
//						Duration.millis(seconds), new KeyValue(this.imageProperty(), images.get(i))));
//		
//	}
//	timeline.setCycleCount(1);
//	
//	timeline.setOnFinished(new EventHandler<ActionEvent>() {
//
//		@Override
//		public void handle(ActionEvent event) {
//			timeline.stop();
//			restartFrog();
//			
//		}
//		
//	});
//	
//	timeline.play();
//	
//	
//	
//}
//
//

//
//System.out.println(this.getClass()==Frog.class);
//System.out.println(classN());
//System.out.println(Frog.class);


//private void collision() {
//
//ArrayList<?> list = gameElements.getList();
//
//for(int i=0; i<list.size();i++ ) {
//	Class<?> actor = (Class<?>) list.get(i);
//	
//	
//	if(actor.getClass()==Log.class && !noMove) {
//	}
//}
//}

//@Override
//public Class<?> getID() {
//	
//	return Frog.class;
//}


////score2.setValue(0);
//score2.addListener( new ChangeListener<Number>() {
//    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//        System.out.println(" new value " + newValue);
//    }
//} );
//

