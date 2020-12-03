package frogger.model.npc;

import frogger.constant.FilePath;
import javafx.scene.image.Image;

/**
 * This class is the model for wet turtle
 *
 */
public class WetTurtle extends NPC {
	private static final int SIZE = 130;
	private static final Image turtle1 = new Image(FilePath.TURTLE1,SIZE,SIZE,true,true);
	private static final Image turtle2 = new Image(FilePath.TURTLE2_WET,SIZE,SIZE,true,true);
	private static final Image turtle3 = new Image(FilePath.TURTLE3_WET,SIZE,SIZE,true,true);
	private static final Image turtle4 = new Image(FilePath.TURTLE4_WET,SIZE,SIZE,true,true);
	private boolean sunk = false;

	public WetTurtle() {
		setImage(turtle2);
	}

	public boolean isSunk() {
		return sunk;
	}
	
	
	@Override
	protected void checkOutOfBounds() {
		if (getX() > 600 && speed>0)
			setX(-200);
		
		if (getX() < -75 && speed<0)
			setX(600);
	}
	
	@Override
	protected void playAnimation(long now) {
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
