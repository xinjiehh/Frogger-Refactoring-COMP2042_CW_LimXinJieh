package Frogger;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Animal extends Actor {
	int score = 0;
	int end = 0;
	private boolean second = false;
	boolean noMove = false;
	boolean carDeath = false;
	boolean waterDeath = false;
	boolean stop = false;
	boolean changeScore = false;
	int carD = 0;
	double w = 800;
	ArrayList<End> inter = new ArrayList<End>();

	public Animal(String imageLink) {
		setStartFrog();
		createKeyListeners();

	}

	private void setStartFrog() {
		setImage(AnimalData.imgW1);
		setX(300);
		setY(679.8 + AnimalData.movement);
	}


	private void moveAnim(double dx, double dy, Image value) {
		move(dx, dy);
		setImage(value);
	}

	private void createKeyListeners() {

		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (noMove) {

				} else {

					KeyCode keyCode = event.getCode();
					Image img;

					
					switch (keyCode) {
					case W:
					case UP:
						img = second ? AnimalData.imgW1 : AnimalData.imgW2;
						moveAnim(0, -AnimalData.movement, img);
						changeScore = false;
						break;

					case A:
					case LEFT:
						img = second ? AnimalData.imgA1 : AnimalData.imgA2;
						moveAnim(-AnimalData.movementX, 0, img);
						break;

					case S:
					case DOWN:
						img = second ? AnimalData.imgS1 : AnimalData.imgS2;
						moveAnim(0, AnimalData.movement, img);
						break;

					case D:
					case RIGHT:
						img = second ? AnimalData.imgD1 : AnimalData.imgD2;
						moveAnim(AnimalData.movementX, 0, img);
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
				if (noMove) {
				} else {
					second = false;
					KeyCode keyCode = event.getCode();

					switch (keyCode) {

					case W:
					case UP:
						if (getY() < w) {
							changeScore = true;
							w = getY();
							score += 10;
						}

						moveAnim(0, -AnimalData.movement, AnimalData.imgW1);
						break;

					case A:
					case LEFT:
						moveAnim(-AnimalData.movementX, 0, AnimalData.imgA1);
						break;

					case S:
					case DOWN:
						moveAnim(0, AnimalData.movement, AnimalData.imgS1);
						break;

					case D:
					case RIGHT:
						moveAnim(AnimalData.movementX, 0, AnimalData.imgD1);
						break;
					default:
						break;

					}

				}
			}

		});
	}

	@Override
	public void act(long now) {
		int bounds = 0;
		
		if (getY() < 0 || getY() > 734) {
			setX(300);
			setY(679.8 + AnimalData.movement);
		}
		if (getX() < 0) {
			move(AnimalData.movement * 2, 0);
		}
		if (carDeath) { 
			//handleCarDeath(now);
		 } 
		if (waterDeath) { 
			//handleWaterDeath(now);
		}
		 

		
		checkIntersecting();
	
	}

	private void checkIntersecting() {
		if (getIntersectingObjects(Obstacle.class).size() >= 1) {
			carDeath = true;
		}
		if (getX() == 240 && getY() == 82) {
			stop = true;
		}
		if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {
			if (getIntersectingObjects(Log.class).get(0).getLeft())
				move(-2, 0);
			else
				move(.75, 0);
		} else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
			move(-1, 0);
		} else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
				waterDeath = true;
			} else {
				move(-1, 0);
			}
		} else if (getIntersectingObjects(End.class).size() >= 1) {
			inter = (ArrayList<End>) getIntersectingObjects(End.class);
			if (getIntersectingObjects(End.class).get(0).isActivated()) {
				end--;
				score -= 50;
			}
			score += 50;
			changeScore = true;
			w = 800;
			getIntersectingObjects(End.class).get(0).setEnd();
			end++;
			setX(300);
			setY(679.8 + AnimalData.movement);
		} else if (getY() < 413) {
			waterDeath = true;
			// setX(300);
			// setY(679.8+movement);
		}
	}

	@SuppressWarnings("unused")
	private void handleWaterDeath(long now) {
		noMove = true;
		if ((now) % 11 == 0) {
			carD++;
		}
		
		setWaterDeathImage();
	}

	private void setWaterDeathImage() {

		// if end of death animation
		if (carD == 5) {
			restartFrog();
			waterDeath = false;

		} else if (carD > 0 && carD < 5) {

			setImage(new Image("file:src/main/resources/waterdeath" + carD + ".png", AnimalData.imgSize, AnimalData.imgSize, true, true));

		}

	}

	private void restartFrog() {
		setStartFrog();
		carD = 0;
		noMove = false;
		if (score > 50) {
			score -= 50;
			changeScore = true;
		}
	}

	@SuppressWarnings("unused")
	private void handleCarDeath(long now) {
		noMove = true;
		if ((now) % 11 == 0) {
			carD++;
		}
		setCarDeathImage();
	}

	private void setCarDeathImage() {

		if (carD == 4) {
			carDeath = false;
			restartFrog();

		} else if (carD > 0 && carD < 4) {
			setImage(new Image("file:src/main/resources/cardeath" + carD + ".png", AnimalData.imgSize, AnimalData.imgSize, true, true));

		}
	}

	public boolean getStop() {
		return end == 5;
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

}
