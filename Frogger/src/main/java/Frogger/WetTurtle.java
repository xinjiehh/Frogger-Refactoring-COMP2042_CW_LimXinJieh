package Frogger;

import javafx.scene.image.Image;

public class WetTurtle extends Actor{
	private static final int SIZE = 130;

	private static final Image turtle1 = new Image("file:src/main/resources/turtle/TurtleAnimation1.png",SIZE,SIZE,true,true);
	private static final Image turtle2 = new Image("file:src/main/resources/turtle/TurtleAnimation2Wet.png",SIZE,SIZE,true,true);
	private static final Image turtle3 = new Image("file:src/main/resources/turtle/TurtleAnimation3Wet.png",SIZE,SIZE,true,true);
	private static final Image turtle4 = new Image("file:src/main/resources/turtle/TurtleAnimation4Wet.png",SIZE,SIZE,true,true);
//	private int speed;
//	int i = 1;
//	boolean bool = true;
	private boolean sunk = false;
	@Override
	public void act(long now) {

				if (now/900000000  % 4 ==0) {
					setImage(turtle2);
					sunk = false;
				}
				
				else if (now/900000000 % 4 == 1) {
					setImage(turtle1);
					sunk = false;
				}
				
				else if (now/900000000 %4 == 2) {
					setImage(turtle3);
					sunk = false;
				} 
				
				
				else if (now/900000000 %4 == 3) {
					setImage(turtle4);
					sunk = true;
				}
			
		move(speed , 0);
		
		if (getX() > 600 && speed>0)
			setX(-200);
		
		if (getX() < -75 && speed<0)
			setX(600);
	}
	
	
	public WetTurtle(int xpos, int ypos, int s, int w, int h) {
		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(turtle2);
	}
	public boolean isSunk() {
		return sunk;
	}
	
//	
//	private Image newTurtle(String link) {
//		
//		return new Image(link,SIZE,SIZE,true,true);
//		
//	}
//	@Override
//	public Class<?> getID() {
//		// TODO Auto-generated method stub
//		return this.getClass();
//	}
}
