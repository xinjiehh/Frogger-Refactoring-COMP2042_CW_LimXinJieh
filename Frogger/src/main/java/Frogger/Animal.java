package Frogger;

import java.util.ArrayList;

import javafx.event.EventHandler;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Animal extends Actor {
	Image imgW1;
	Image imgA1;
	Image imgS1;
	Image imgD1;
	Image imgW2;
	Image imgA2;
	Image imgS2;
	Image imgD2;
	int points = 0;
	int end = 0;
	private boolean second = false;
	boolean noMove = false;
	double movement = 13.3333333*2;
	double movementX = 10.666666*2;
	int imgSize = 40;
	boolean carDeath = false;
	boolean waterDeath = false;
	boolean stop = false;
	boolean changeScore = false;
	int carD = 0;
	double w = 800;
	ArrayList<End> inter = new ArrayList<End>();
	
	public Animal(String imageLink) {
		setImage(new Image(imageLink, imgSize, imgSize, true, true));
		setX(300);
		setY(679.8+movement);
		createImages();
		createKeyListeners();
	
	}
	
	private void moveAnim(double dx, double dy, Image value) {
		move(dx,dy);
		setImage(value);
	}
			

	private void createKeyListeners() {
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event){
				if (noMove) {
					
				}
				else {
					
					KeyCode keyCode = event.getCode();
					Image img;
						
					second = second ? false : true;
					
					switch(keyCode) {
					case W: 
					case UP: 
						img = second ? imgW1 : imgW2;
						moveAnim(0, -movement, img);
			            changeScore = false;
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

				}
			}
		});	
		
		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (noMove) {}
				else {
					second = false;
					KeyCode keyCode = event.getCode();
					
					switch(keyCode) {
					
					case W: 
					case UP: 
						if (getY() < w) {
							changeScore = true;
							w = getY();
							points+=10;
						}
						
						moveAnim(0, -movement, imgW1);
						break;
							
					case A:
					case LEFT:
						moveAnim(-movementX, 0, imgA1);
						break;
						
					case S:
					case DOWN:
						moveAnim(0, movement, imgS1);
						break;
							
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


	private void createImages() {
		imgW1 = new Image("file:src/main/resources/froggerUp.png", imgSize, imgSize, true, true);
		imgA1 = new Image("file:src/main/resources/froggerLeft.png", imgSize, imgSize, true, true);
		imgS1 = new Image("file:src/main/resources/froggerDown.png", imgSize, imgSize, true, true);
		imgD1 = new Image("file:src/main/resources/froggerRight.png", imgSize, imgSize, true, true);
		imgW2 = new Image("file:src/main/resources/froggerUpJump.png", imgSize, imgSize, true, true);
		imgA2 = new Image("file:src/main/resources/froggerLeftJump.png", imgSize, imgSize, true, true);
		imgS2 = new Image("file:src/main/resources/froggerDownJump.png", imgSize, imgSize, true, true);
		imgD2 = new Image("file:src/main/resources/froggerRightJump.png", imgSize, imgSize, true, true);
	}
	
	@Override
	public void act(long now) {
		int bounds = 0;
		if (getY()<0 || getY()>734) {
			setX(300);
			setY(679.8+movement);
		}
		if (getX()<0) {
			move(movement*2, 0);
		}
		/*
		 * if (carDeath) { handleCarDeath(now);
		 * 
		 * } if (waterDeath) { handleWaterDeath(now);
		 * 
		 * }
		 */
		
		if (getX()>600) {
			move(-movement*2, 0);
		}
		if (getIntersectingObjects(Obstacle.class).size() >= 1) {
			carDeath = true;
		}
		if (getX() == 240 && getY() == 82) {
			stop = true;
		}
		if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {
			if(getIntersectingObjects(Log.class).get(0).getLeft())
				move(-2,0);
			else
				move (.75,0);
		}
		else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
			move(-1,0);
		}
		else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
				waterDeath = true;
			} else {
				move(-1,0);
			}
		}
		else if (getIntersectingObjects(End.class).size() >= 1) {
			inter = (ArrayList<End>) getIntersectingObjects(End.class);
			if (getIntersectingObjects(End.class).get(0).isActivated()) {
				end--;
				points-=50;
			}
			points+=50;
			changeScore = true;
			w=800;
			getIntersectingObjects(End.class).get(0).setEnd();
			end++;
			setX(300);
			setY(679.8+movement);
		}
		else if (getY()<413){
			waterDeath = true;
			//setX(300);
			//setY(679.8+movement);
		}
	}

	private void handleWaterDeath(long now) {
		noMove = true;
		if ((now)% 11 ==0) {
			carD++;
		}
		if (carD==1) {
			setImage(new Image("file:src/main/resources/waterdeath1.png", imgSize,imgSize , true, true));
		}
		if (carD==2) {
			setImage(new Image("file:src/main/resources/waterdeath2.png", imgSize,imgSize , true, true));
		}
		if (carD==3) {
			setImage(new Image("file:src/main/resources/waterdeath3.png", imgSize,imgSize , true, true));
		}
		if (carD == 4) {
			setImage(new Image("file:src/main/resources/waterdeath4.png", imgSize,imgSize , true, true));
		}
		if (carD == 5) {
			setX(300);
			setY(679.8+movement);
			waterDeath = false;
			carD = 0;
			setImage(new Image("file:src/main/resources/froggerUp.png", imgSize, imgSize, true, true));
			noMove = false;
			if (points>50) {
				points-=50;
				changeScore = true;
			}
		}
	}

	private void handleCarDeath(long now) {
		noMove = true;
		if ((now)% 11 ==0) {
			carD++;
		}
		if (carD==1) {
			setImage(new Image("file:src/main/resources/cardeath1.png", imgSize, imgSize, true, true));
		}
		if (carD==2) {
			setImage(new Image("file:src/main/resources/cardeath2.png", imgSize, imgSize, true, true));
		}
		if (carD==3) {
			setImage(new Image("file:src/main/resources/cardeath3.png", imgSize, imgSize, true, true));
		}
		if (carD == 4) {
			setX(300);
			setY(679.8+movement);
			carDeath = false;
			carD = 0;
			setImage(new Image("file:src/main/resources/froggerUp.png", imgSize, imgSize, true, true));
			noMove = false;
			if (points>50) {
				points-=50;
				changeScore = true;
			}
		}
	}
	
	public boolean getStop() {
		return end==5;
	}
	
	public int getPoints() {
		return points;
	}
	
	public boolean changeScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;
		
	}
	

}
