package frogger.model;


import frogger.DeathAnimation;
import frogger.constant.AudioPlayer;
import frogger.constant.Direction;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;

/**
 * Each frog has its own set of images
 * 
 * @author xinji
 *
 */

public class Frog extends PlayerAvatar {
	
	public enum FrogDeath {
		WATER,
		CAR,
		NULL;
	}
	
	public boolean isJump = false;
	public boolean noMove = false; 
	public int tempScore = 0;
	
	private static final int MAX_FROG = 2;
	public static final int IMG_SIZE = 40;
	public static final String PREFIX = "/frog/";
	public static final Image imgW1 = new Image(PREFIX + "froggerUp.png", IMG_SIZE, IMG_SIZE, true, true);
	public static final Image imgA1 = new Image(PREFIX + "froggerLeft.png", IMG_SIZE, IMG_SIZE, true, true);
	public static final Image imgS1 = new Image(PREFIX + "froggerDown.png", IMG_SIZE, IMG_SIZE, true, true);
	public static final Image imgD1 = new Image(PREFIX + "froggerRight.png", IMG_SIZE, IMG_SIZE, true, true);
	public static final Image imgW2 = new Image(PREFIX + "froggerUpJump.png", IMG_SIZE, IMG_SIZE, true, true);
	public static final Image imgA2 = new Image(PREFIX + "froggerLeftJump.png", IMG_SIZE, IMG_SIZE, true, true);
	public static final Image imgS2 = new Image(PREFIX + "froggerDownJump.png", IMG_SIZE, IMG_SIZE, true, true);
	public static final Image imgD2 = new Image(PREFIX + "froggerRightJump.png", IMG_SIZE, IMG_SIZE, true, true);
	
	private IntegerProperty lifeProp;
	private IntegerProperty scoreProp;
	

	private FrogDeath frogDeath = FrogDeath.NULL;
	
	private int swampFrogCount = 0; 
	private boolean flyBonus;
	private double bonusXPos;
	private boolean attached;
	private double dx;
	private double dy;
	

	public void handleDeath(FrogDeath death) {
		
		if(getDeath()==FrogDeath.NULL) {
			System.out.println("One life lost");
			playAudio(death);
			addScore(-tempScore);
			tempScore=0;
			DeathAnimation test = new DeathAnimation(this, death);
			test.play();
			setDeath(death);
			int lifeLeft = loseLife();
			if(lifeLeft==-1) {
				 noMove=true;
			}
		}
		
	}


	public void jump(Direction direction, boolean keyPress) {
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
	 * This method resets frog to its starting state
	 */
	public void restartFrog() {
		setStartFrog();
		noMove = false;
		tempScore = 0;
		System.out.println("tempScore " + tempScore);
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

	@Override
	/**
	 * This method is called in each frame to check if this player object goes 
	 * out of visible bounds or intersects with other game element objects and 
	 * handles each case accordingly
	 * 
	 */
	
	public void act(long now) {
		
		if(attached) {
			move(dx, dy);
			attached=false;
		}
//		checkIntersecting();
//		checkOutOfBounds();
//		
//		if (waterDeath || carDeath) { 
//			handleDeath(now);
//		}

	}
	
	public void setScore(int i) {
		scoreProp.setValue(i);
	}
	
	public boolean isDone() { //TODO renamed from get Stop
		if(swampFrogCount==MAX_FROG) {
			swampFrogCount=0;
			return true;
			
		} else 
			return false;
	}

	public int getScore() {
		return scoreProp.intValue();
	}
	
	public boolean noLivesLeft() {
		return lifeProp.intValue()==-1;
	}

	public boolean hasFlyBonus() {
		if(flyBonus) {
			
			flyBonus=false;
			return true;
		}
		
		else return false;
	}
	
	public void setFlyBonus(boolean bool) {
		flyBonus=bool;
	}
	
	public void setBonusX(double xpos) {
		bonusXPos = xpos;
	}
	
	public double getBonusX() {
		return bonusXPos;
	}
	
	public void increment() {
		swampFrogCount++;
	}
	
	public Frog() {
		setStartFrog();
		lifeProp = new SimpleIntegerProperty(3);
		scoreProp = new SimpleIntegerProperty(0);
	}
	

	public void addScore(int x) {
		scoreProp.setValue(scoreProp.intValue()+x);
	}
	
	public void addScoreListener(ChangeListener<? super Number> listener) {
		scoreProp.addListener(listener);
	}
	
	public void addLifeListener(ChangeListener<? super Number> listener) {
		lifeProp.addListener(listener);
	}
	

	/**
	 * This method sets player character in starting mode
	 */
	public void setStartFrog() {
		setImage(imgW1);
		setX(START_XPOS);
		setY(START_YPOS);
	}
	
	public void setDeath(FrogDeath death) {
		frogDeath=death;
	}
	
	public FrogDeath getDeath() {
		return frogDeath;
	}
	
	public int loseLife() {
		 lifeProp.setValue(lifeProp.intValue()-1);
		 System.out.println("Life left: " + lifeProp.toString());
		 return lifeProp.intValue();
	}
	
	public void attachFrog(double dx, double dy) {
		attached = true;
		this.dx=dx;
		this.dy=dy;
	}
	

	
	/**
	 * This method changes the x-position, y-position and image
	 * property of the frog object
	 * @param dx - horizontal distance to be moved
	 * @param dy - vertical distance to be moved
	 * @param value - Image object
	 */
	private void moveAnim(double dx, double dy, Image value) {
		move(dx, dy);
		setImage(value);
	}
	
	private void checkDownY() {
		//frog moving downwards (but above starting pos.)
		//deducts marks
		if (getY() < Frog.START_YPOS && getY() > Frog.LOWER_BOUND+1) { 
			addScore(-10);
			tempScore -= 10;
			System.out.println("tempScore " + tempScore);
		}
		
	}

	private void checkUpY() {
		if(getDeath()==FrogDeath.NULL) {
			if (getY() < Frog.UPPER_BOUND && getY() > Frog.LOWER_BOUND) {
				addScore(10);
				tempScore += 10;
				System.out.println("tempScore add " + tempScore);
			}
		}

	}

	/**
	 * This method plays death audio according to the type of death
	 * @param str -"car" for car death and "water" for water death
	 */
	private void playAudio(FrogDeath death) {
		if(death==FrogDeath.CAR)
			AudioPlayer.INSTANCE.squashSound();
		else if(death==FrogDeath.WATER)
			AudioPlayer.INSTANCE.plunkSound();
	}



//public int getScore() {
//	return score;
//}

//public boolean changeScore() {
//	if (changeScore) {
//		changeScore = false;
//		return true;
//	} else
//		return false;
//
//}



//public boolean gameOver() { 
//	if(swampFrogCount==MAX_FROG) {
//		swampFrogCount=0;
//		return true;
//		
//	} else 
//		return false;
//}



	
	
//	public static final String pref = "frogger";
//	public final ArrayList<Image> moveImg = new ArrayList<>(){
//		{
//			add(new Image(pref + "Up.png", IMG_SIZE, IMG_SIZE, true, true));
//			add(new Image(pref + "Down.png", IMG_SIZE, IMG_SIZE, true, true));
//			add(new Image(pref + "Left.png", IMG_SIZE, IMG_SIZE, true, true));
//			add(new Image(pref + "Right.png", IMG_SIZE, IMG_SIZE, true, true));
//			
//		}
//		
//		
//	};
	

//	public int tempScore = 0;
//	protected boolean isJump = false;
//	private ArrayList<End> inter = new ArrayList<End>();
//	public int deathAnimCtr = 0;
//	private boolean carDeath = false;
//	private boolean waterDeath = false;
//	
//	/**
//	 * This method checks if this player object is out of visible 
//	 * bounds and reset player object position accordingly
//	 */
//	private void checkOutOfBounds() {
//		
//		if (getY() > START_YPOS) {
//			setY(START_YPOS);
//		}
//		
//		else if (getX() < 0 || getX() > 600) {
//			
//			double m = getX() > 600 ? -movement : movement;
//			move(m * 2, 0);
//			
//		}
//		
//	}
//
//	
//	/*
//	 * TODO modified, check for intersecting object once, match class
//	 * instead of checking intersecting object for each class
//	 * 
//	 * This method checks if this player object intersect with any
//	 * game elements and handles the situation accordingly 
//	 */
//	private void checkIntersecting() {
//		
//		Actor actor = getOneIntersectingObject(Actor.class);
//		
//		if(actor!=null) {
//
//
//			if(actor instanceof Obstacle) {
//				//carDeath=true;
//				
//				
//			} else if ((actor instanceof Log || actor instanceof Turtle) && !noMove) {
//				
//				move(actor.getSpeed(), 0);
//				
//			} else if(actor instanceof WetTurtle) {
//				
//				WetTurtle wetTurtle = getOneIntersectingObject(WetTurtle.class);
//				if(wetTurtle.isSunk()) {
//					
//					//waterDeath=true;
//					
//				} else {
//					move(actor.getSpeed(), 0);
//					
//				}
//				
//			} else if (actor instanceof End) {
//				
//				End end = getOneIntersectingObject(End.class);
//
//				//if frog reaches an occupied swamp spot
//				if (end.isActivated()) {
//					//waterDeath = true;
//				} else if (end.hasCroc()) {
//					//waterDeath=true;
//					
//				} else {
//					
//					if(end.hasFly()) {
//						score+=20;
//						flyBonus = true;
//						currentXPos = end.getX();
//					}
//
//					swampFrogCount++;
//					score += 50;
//					tempScore = 0;
//					end.setOccupied();
//					changeScore = true;
//					restartFrog();
//					
//				}
//			}
//			
//		} else if (getY() < 413) { //if reach this point no intersect and >413 frog is in water
//			//waterDeath=true;
//			
//		}
//		
//		
//	}
//	
//	
//	private void handleDeath(long now) {
//
//		String deathType = waterDeath? "waterdeath" : "cardeath";
//		noMove = true;
//		if ((now) % 11 == 0) {
//			deathAnimCtr++;
//		}
//
//		setDeathImage(deathType);
//	}
//	
//	private void setDeathImage(String deathType) {
//
//		// if end of death animation
//		if (deathAnimCtr >= 5) {
//			restartFrog();
//			waterDeath = false; 
//			carDeath=false;
//			score-=tempScore;
//			changeScore = true;
//			tempScore = 0;
//			//deathAnimCtr = 0; TODO
//
//		} else if (deathAnimCtr > 0 && deathAnimCtr < 5) {
//			
//			if(deathAnimCtr==1) {
//				
//				if(carDeath) {
//					GamePane.carDeathSound();
//				}
//				
//				else if(waterDeath) {
//					GamePane.waterDeathSound();
//				}
//					
//			}
//			
//			setImage(new Image("file:src/main/resources/" + deathType + deathAnimCtr + ".png", imgSize, imgSize, true, true));
//
//		}
//
//	}
//	
	

	
	
	//now: The timestamp of the current frame given in nanoseconds. 
	//This value will be the same for all AnimationTimers called
	//during one frame.
	

	
//	public void setNoMove(boolean b) {
//		noMove=b;
//	}

//	@Override
//	public void handleKeyPress(KeyEvent event) {
//		if (!noMove) {
//			KeyCode keyCode = event.getCode();
//			Image img;
//			switch (keyCode) {
//			case W:
//			case UP:
//				System.out.println("key pressed up " + isJump);
//				img = isJump ? imgW1 : imgW2;
//				moveAnim(0, -movement, img);
//				break;
//
//			case A:
//			case LEFT:
//				img = isJump ? imgA1 : imgA2;
//				moveAnim(-movementX, 0, img);
//				break;
//
//			case S:
//			case DOWN:
//				img = isJump ? imgS1 : imgS2;
//				moveAnim(0, movement, img);
//				break;
//
//			case D:
//			case RIGHT:
//				img = isJump ? imgD1 : imgD2;
//				moveAnim(movementX, 0, img);
//				break;
//			default:
//				break;
//
//			}
//			System.out.println("is Jump "  + isJump);		
//			isJump = isJump ? false : true;
//
//		} 
//
//	}
//	
//	@Override
//	public void handleKeyRelease(KeyEvent event) {
//	
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
//		
//
//}

	
//	@Override
//	protected void createKeyListeners() {
//		
//
//		setOnKeyPressed(new EventHandler<KeyEvent>() {
//			public void handle(KeyEvent event) {
//				if (!noMove) {
//					
//					KeyCode keyCode = event.getCode();
//					Image img;
//
//					switch (keyCode) {
//					case W:
//					case UP:
//						System.out.println("key pressed up " + isJump);
//						img = isJump ? imgW1 : imgW2;
//						moveAnim(0, -movement, img);
//						
//						break;
//
//					case A:
//					case LEFT:
//						img = isJump ? imgA1 : imgA2;
//						moveAnim(-movementX, 0, img);
//						break;
//
//					case S:
//					case DOWN:
//						img = isJump ? imgS1 : imgS2;
//						moveAnim(0, movement, img);
//						break;
//
//					case D:
//					case RIGHT:
//						img = isJump ? imgD1 : imgD2;
//						moveAnim(movementX, 0, img);
//						break;
//					default:
//						break;
//
//					}
//					
//					isJump = isJump ? false : true;
//
//				} 
//			}
//		});
//
//		setOnKeyReleased(new EventHandler<KeyEvent>() {
//			public void handle(KeyEvent event) {
//				if (!noMove) {
//					isJump = false;
//					KeyCode keyCode = event.getCode();
//
//					switch (keyCode) {
//
//					case W:
//					case UP:
//						System.out.println("up release");
//						if (getY() < UPPER_BOUND && getY() > LOWER_BOUND) {
//							
//							score += 10;
//							changeScore = true;
//							System.out.println(tempScore);
//							tempScore += 10;
//							
//						}
//
//						moveAnim(0, -movement, imgW1);
//						break;
//
//					case A:
//					case LEFT:
//						moveAnim(-movementX, 0, imgA1);
//						System.out.println("here "  + getY());
//						break;
//
//					case S:
//					case DOWN:
//						if (getY() < START_YPOS) {
//							score -= 10;
//							changeScore = true;
//							tempScore -= 10;
//							moveAnim(0, movement, imgS1);
//							break;
//						}
//
//					case D:
//					case RIGHT:
//						moveAnim(movementX, 0, imgD1);
//						break;
//					default:
//						break;
//
//					}
//				} 
//			}
//
//		});
//	}
//	
//	
	
	
	///ORIGINAL 
	
//	if (getOneIntersectingObject(Obstacle.class)!=null) {
//		//carDeath = true;
//	}
//	
//	if (getOneIntersectingObject(Log.class)!= null && !noMove) { //can still move
//
//		double speed = getIntersectingObjects(Log.class).get(0).getSpeed();
//		move(speed, 0);
//		
//	} else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
//		double speed = getIntersectingObjects(Turtle.class).get(0).getSpeed();
//		move(speed, 0);
//		
//	} else if (getOneIntersectingObject(WetTurtle.class) != null) { //can or cannot move
//		if (getOneIntersectingObject(WetTurtle.class).isSunk()) {
//			//waterDeath = true;
//			
//		} else {
//			double speed = getOneIntersectingObject(WetTurtle.class).getSpeed();
//			move(speed, 0);
//		}
//	} else if (getIntersectingObjects(End.class).size() >= 1) {
//		inter = (ArrayList<End>) getIntersectingObjects(End.class);
//		
//		//if frog reaches an occupied swamp spot
//		if (inter.get(0).isActivated()) {
//			//waterDeath = true;
//		} else {
//			swampFrogCount++;
//			score += 50;
//			tempScore = 0;
//			getIntersectingObjects(End.class).get(0).setOccupied();
//			changeScore = true;
//			restartFrog();
//			
//		}
//
//		
//	} else if (getY() < 413) { //if reach this point no intersect and >413 frog is in water
//		//waterDeath=true;
//		
//	}
//	private void setWaterDeathImage() {
//
//		// if end of death animation
//		if (carD >= 5) {
//			restartFrog();
//			waterDeath = false; //carDeath=false;
//			score-=tempScore;
//			changeScore = true;
//			tempScore=0;
//
//		} else if (carD > 0 && carD < 5) {
//
//			setImage(new Image("file:src/main/resources/waterdeath" + carD + ".png", FrogModel.imgSize, FrogModel.imgSize, true, true));
//
//		}
//
//	}
//	
//	private void setCarDeathImage() {
//
//		if (carD == 4) {
//			
//			restartFrog();
//			carDeath = false;
//			score-=tempScore;
//			changeScore = true;
//			tempScore = 0;
//	
//		} else if (carD > 0 && carD < 4) {
//			setImage(new Image("file:src/main/resources/cardeath" + carD + ".png", FrogModel.imgSize, FrogModel.imgSize, true, true));
//	
//		}
//	}


	
//	private void handleCarDeath2(long now) {
//	noMove = true;
//	score-=tempScore;
//	changeScore = true;
//	tempScore = 0;
//	//carDeathAnim("cardeath");
//	carDeathAnim("cardeath");
//	
//}
////working, small delay
//	public void carDeathAnim(String death) {
//		//meant to replace setCarDeathImage without param now
//		String imgPath = "file:src/main/resources/" + death;
//		System.out.println(imgPath);
//		int duration = 100;
//		Animation animation1 = new Transition() {
//			
//	
//			{
//				setCycleDuration(Duration.millis(duration));
//			}
//			
//			@Override
//			protected void interpolate(double frac) {
//				setImage(new Image(imgPath + "1" + ".png", 
//							FrogModel.imgSize, FrogModel.imgSize, true, true));
//
//					
//				
//				
//				
//			}
//			
//		};
//		
//		Animation animation2 = new Transition() {
//			
//			
//			{
//				setCycleDuration(Duration.millis(duration));
//			}
//			
//			@Override
//			protected void interpolate(double frac) {
//				setImage(new Image(imgPath + "2" + ".png", 
//							FrogModel.imgSize, FrogModel.imgSize, true, true));
//
//					
//				
//				
//				
//			}
//			
//		};
//		
//		Animation animation3 = new Transition() {
//			
//			
//			{
//				setCycleDuration(Duration.millis(duration));
//			}
//			
//			@Override
//			protected void interpolate(double frac) {
//				setImage(new Image(imgPath + "3" + ".png", 
//							FrogModel.imgSize, FrogModel.imgSize, true, true));
//
//					
//				
//				
//				
//			}
//			
//		};
//		
//		SequentialTransition loop = new SequentialTransition(animation1,animation2,animation3);
//		
//		loop.play();
//		loop.setOnFinished(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				//animation.stop();
//
//				restartFrog();
//				
//			}
//			
//			
//		});
//		
//		
//		
//	}


	
//	private void handleCarDeath(long now) {
//		noMove = true;
//		if ((now) % 11 == 0) {
//			carD++;
//		}
//
//		setCarDeathImage();
//		
//	}
	
	
//	private void setCarDeathImage() {
//		if(carD==3) {
//			score-=tempScore;
//			changeScore = true;
//			tempScore = 0;
//		}
//
//		if (carD == 4) {
//			changeScore = false;
//			carDeath = false;
//			restartFrog();
//
//		} else if (carD > 0 && carD < 4) {
//			setImage(new Image("file:src/main/resources/cardeath" + carD + ".png", FrogModel.imgSize, FrogModel.imgSize, true, true));
//
//		}
//	}
////	
//	
	
//////////// use timeline, not working
//	public void setCarDeathImage2() {
//		//meant to replace original without param now
//		
//		Timeline timeline = new Timeline();
//
//		int seconds = 0;
//		for(int i=0; i<3;i++) {
//			seconds +=100;
//			timeline.getKeyFrames().add(
//					new KeyFrame(
//							Duration.millis(seconds), new KeyValue(this.imageProperty(), images.get(i))));
//			
//		}
//		timeline.setCycleCount(1);
//		
//		timeline.setOnFinished(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				timeline.stop();
//				restartFrog();
//				
//			}
//			
//		});
//		
//		timeline.play();
//		
//		
//		
//	}
//	
//	

//
//	System.out.println(this.getClass()==Frog.class);
//	System.out.println(classN());
//	System.out.println(Frog.class);


//	private void collision() {
//	
//	ArrayList<?> list = gameElements.getList();
//
//	for(int i=0; i<list.size();i++ ) {
//		Class<?> actor = (Class<?>) list.get(i);
//		
//		
//		if(actor.getClass()==Log.class && !noMove) {
//		}
//	}
//}
	
//	@Override
//	public Class<?> getID() {
//		
//		return Frog.class;
//	}

	
//	//score2.setValue(0);
//	score2.addListener( new ChangeListener<Number>() {
//	    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//	        System.out.println(" new value " + newValue);
//	    }
//	} );
//	
	
	
}
