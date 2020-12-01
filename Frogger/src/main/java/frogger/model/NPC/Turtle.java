package frogger.model.NPC;

import javafx.scene.image.Image;

public class Turtle extends Actor{
	private static final int SIZE = 130;
	private static final String PREFIX = "/moving/";
	Image turtle1;
	Image turtle2;
	Image turtle3;
	//private int speed;
	int i = 1;
	boolean bool = true;
	@Override
	public void act(long now) {

				if (now/900000000  % 3 ==0) {
					setImage(turtle2);
					
				}
				else if (now/900000000 % 3 == 1) {
					setImage(turtle1);
					
				}
				else if (now/900000000 %3 == 2) {
					setImage(turtle3);
					
				}
			
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -75 && speed<0)
			setX(600);
	}
	
	public Turtle(int xpos, int ypos, int s, int w, int h) {
		turtle1 = new Image(PREFIX + "TurtleAnimation1.png", w, h, true, true);
		turtle2 = new Image(PREFIX + "TurtleAnimation2.png", w, h, true, true);
		turtle3 = new Image(PREFIX + "TurtleAnimation3.png", w, h, true, true);
		setX(xpos);
		setY(ypos);
		speed = s;
		setImage(turtle2);
	}
	
	public Turtle() {
		turtle1 = new Image(PREFIX + "TurtleAnimation1.png", SIZE, SIZE, true, true);
		turtle2 = new Image(PREFIX + "TurtleAnimation2.png", SIZE, SIZE, true, true);
		turtle3 = new Image(PREFIX + "TurtleAnimation3.png", SIZE, SIZE, true, true);
		setImage(turtle2);
		System.out.println("Turtle width: " + getWidth() + 
				"\nheight: " + getHeight());
	}
}
