package frogger.model.NPC;

import frogger.constant.LOG_TYPE;
import javafx.scene.image.Image;


public class Log extends Actor {

	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX()>600 && speed>0)
			setX(-180);
		if (getX()<-300 && speed<0)
			setX(700);
	}
	
	public Log(LOG_TYPE name, double speed) {
		int size = name.getSize();
		setImage(new Image(name.getURL(), size, size, true, true));
		this.speed = speed;
	}
	
	public Log(LOG_TYPE name, int size, double xpos, double ypos, double speed) {
		//int size = name.getSize();
		setImage(new Image(name.getURL(), size, size, true, true));
		this.speed = speed;
		setX(xpos);
		setY(ypos);
	}
	
	public Log(LOG_TYPE name) {
		int size = name.getSize();
		setImage(new Image(name.getURL(), size, size, true, true));

	}
	
	public void initializeLog(int xpos, int ypos, double s) {
		setX(xpos);
		setY(ypos);
		speed = s;
		

	}
	

	
//	public Log(LogImage name, int size, int xpos, int ypos, double s) {
//
//		
//		setImage(new Image(name.getURL(), size, size, true, true));
//		setX(xpos);
//		setY(ypos);
//		speed = s;
//		
//	}
}
