package frogger.model;

import javafx.scene.image.Image;

public class Digit extends Actor{
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
	public Digit(int n, int x, int y) {
		setImage(new Image("/digit/"+n+".png", WIDTH, HEIGHT, true, true));
		setX(x);
		setY(y);
	}
	
}
