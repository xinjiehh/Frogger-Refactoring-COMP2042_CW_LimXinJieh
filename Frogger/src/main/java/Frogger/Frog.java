package Frogger;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import levels.GameElements;


public class Frog extends PlayerAvatar implements FrogModel {
	private GameElements gameElements = new GameElements();
	private boolean second = false;
	private boolean noMove = false;
	private boolean carDeath = false;
	private boolean waterDeath = false;
	private boolean changeScore = false;
	private int score = 0;
	private int swampFrogCount = 0;
	private int deathAnimCtr = 0;
	private int tempScore = 0;
	private ArrayList<End> inter = new ArrayList<End>();


	public Frog() {
		setStartFrog();
		createKeyListeners();
		this.score=0;
	}

	private void setStartFrog() {
		setImage(imgW1);
		setX(START_XPOS);
		setY(START_YPOS);
	}


	private void moveAnim(double dx, double dy, Image value) {
		move(dx, dy);
		setImage(value);
	}
	
	@Override
	//noMove, second, TODO override? 
	protected void createKeyListeners() {

		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (!noMove) {
					
					KeyCode keyCode = event.getCode();
					Image img;

					switch (keyCode) {
					case W:
					case UP:
						img = second ? imgW1 : imgW2;
						moveAnim(0, -movement, img);
						
						break;

					case A:
					case LEFT:
						img = second ? imgA1 : imgA2;
						moveAnim(-movementX, 0, img);
						break;

					case S:
					case DOWN:
						img = second ? imgS1 : imgS2;
						moveAnim(0, movement, img);
						break;

					case D:
					case RIGHT:
						img = second ? imgD1 : imgD2;
						moveAnim(movementX, 0, img);
						break;
					default:
						break;

					}
					
					second = second ? false : true;

				} 
			}
		});

		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (!noMove) {
					second = false;
					KeyCode keyCode = event.getCode();

					switch (keyCode) {

					case W:
					case UP:
						if (getY() < UPPER_BOUND && getY() > LOWER_BOUND) {
							
							score += 10;
							changeScore = true;
							System.out.println(tempScore);
							tempScore += 10;
							
						}

						moveAnim(0, -movement, imgW1);
						break;

					case A:
					case LEFT:
						moveAnim(-movementX, 0, imgA1);
						System.out.println("here "  + getY());
						break;

					case S:
					case DOWN:
						if (getY() < START_YPOS) {
							score -= 10;
							changeScore = true;
							tempScore -= 10;
							moveAnim(0, movement, imgS1);
							break;
						}

					case D:
					case RIGHT:
						moveAnim(movementX, 0, imgD1);
						break;
					default:
						break;

					}
				} 
			}

		});
	}
	
	//called in each frame to check for frog position status
	@Override
	public void act(long now) {

		checkIntersecting();
		checkOutOfBounds();
		
		if (waterDeath || carDeath) { 
			handleDeath(now);
		}

	}
	
	private void checkOutOfBounds() {
		
		if (getY() > START_YPOS) {
			setY(START_YPOS);
		}
		
		else if (getX() < 0 || getX() > 600) {
			
			double m = getX() > 600 ? -movement : movement;
			move(m * 2, 0);
			
		}
		
	}

	private double getSpeed(java.lang.Class<? extends Actor> cls) {
		return getIntersectingObjects(cls).get(0).getSpeed();
	}
	

	private void checkIntersecting() {
		
		if (getIntersectingObjects(Obstacle.class).size() >= 1) {
			//carDeath = true;
		}
		if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) { //can still move

			double speed = getIntersectingObjects(Log.class).get(0).getSpeed();
			move(speed, 0);
			
		} else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
			double speed = getIntersectingObjects(Turtle.class).get(0).getSpeed();
			move(speed, 0);
			
		} else if (getIntersectingObjects(WetTurtle.class).size() >= 1) { //can or cannot move
			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
				//waterDeath = true;
				
			} else {
				move(-1, 0);
			}
		} else if (getIntersectingObjects(End.class).size() >= 1) {
			inter = (ArrayList<End>) getIntersectingObjects(End.class);
			
			//if frog reaches an occupied swamp spot
			if (inter.get(0).isActivated()) {
				//waterDeath = true;
			} else {
				swampFrogCount++;
				score += 50;
				tempScore = 0;
				getIntersectingObjects(End.class).get(0).setEnd();
				changeScore = true;
				restartFrog();
				
			}

			
		} else if (getY() < 413) { //if reach this point no intersect and >413 frog is in water
			//waterDeath=true;
			
		}
	}
	
	
	private void handleDeath(long now) {

		String deathType = waterDeath? "waterdeath" : "cardeath";
		noMove = true;
		if ((now) % 11 == 0) {
			deathAnimCtr++;
		}

		setDeathImage(deathType);
	}
	
	private void setDeathImage(String deathType) {

		// if end of death animation
		if (deathAnimCtr >= 5) {
			restartFrog();
			waterDeath = false; 
			carDeath=false;
			score-=tempScore;
			changeScore = true;
			tempScore = 0;

		} else if (deathAnimCtr > 0 && deathAnimCtr < 5) {

			setImage(new Image("file:src/main/resources/" + deathType + deathAnimCtr + ".png", imgSize, imgSize, true, true));

		}

	}


	private void restartFrog() {
		setStartFrog();
		deathAnimCtr = 0;
		noMove = false;
//		if (score > 50) {
//			score -= 50;
//			changeScore = true;
//		}
	}
	//now: The timestamp of the current frame given in nanoseconds. 
	//This value will be the same for all AnimationTimers called
	//during one frame.
	

	public boolean isDone() { //TODO renamed from get Stop
		if(swampFrogCount==3) {
			
			swampFrogCount=0;
			return true;
			
		} else 
			return false;
	}
	
	public int getScore() {
		return score;
	}

	public boolean changeScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;

	}
////
////	
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
