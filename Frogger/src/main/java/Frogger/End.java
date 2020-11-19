package Frogger;

import javafx.scene.image.Image;

public class End extends Actor{
	private static final int WIDTH = 65;
	private static final int HEIGHT = 60;
	boolean activated = false;
	@Override
	public void act(long now) {
		// TODO Auto-generated method st
	}
	
	public End(int x, int y) {
		setX(x);
		setY(y);
		setEndImage("End.png");
	}
	
	public void setEnd() {
		setEndImage("FrogEnd.png");
		activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}
	
	private void setEndImage(String url) {
		setImage(new Image(url, WIDTH, HEIGHT, false, true));
	}
	

}
