package frogger.model;

import javafx.scene.image.Image;
import trial.model.OBSTACLE_TYPE;


public class Obstacle extends Actor {

	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -50 && speed<0)
			setX(600);
	}
	
	public Obstacle(OBSTACLE_TYPE obstacle, int xpos, int ypos, int s) {
		int size = obstacle.getSize();
		setImage(new Image(obstacle.getURL(), size, size, true, true));
		System.out.println("this " + obstacle.getURL() + " " + size);
		setX(xpos);
		setY(ypos);
		this.speed = s;
	}
	
	public Obstacle(OBSTACLE_TYPE obstacle) {
		int size = obstacle.getSize();
		setImage(new Image(obstacle.getURL(), size, size, true, true));
		System.out.println(obstacle.getURL() + "width: " + getWidth() + "\nheight: " + getHeight());
	}
	
	
	
	
	//TEST
	public Obstacle(OBSTACLE_TYPE obstacle, int xpos, int ypos) {
		int size = 500;
		setImage(new Image(obstacle.getURL(), size, size, true, true));
		setX(xpos);
		setY(ypos);
	}

}
