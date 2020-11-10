package Frogger;

import javafx.scene.image.Image;

public class End extends Actor{
	private static final int SIZE = 60;
	boolean activated = false;
	@Override
	public void act(long now) {
		// TODO Auto-generated method st
	}
	
	public End(int x, int y) {
		setX(x);
		setY(y);
		setEndImage("file:src/main/resources/End.png");
	}
	
	public void setEnd() {
		setEndImage("file:src/main/resources/FrogEnd.png");
		activated = true;
	}
	
	private void setEndImage(String url) {
		setImage(new Image(url, SIZE, SIZE, true, true));
	}
	
	public boolean isActivated() {
		return activated;
	}
	

}
