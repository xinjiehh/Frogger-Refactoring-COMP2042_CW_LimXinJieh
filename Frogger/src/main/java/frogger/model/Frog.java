package frogger.model;


import frogger.constant.DIRECTION;
import frogger.constant.FilePath;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * This class defines a {@link PlayerAvatar} of type {@code Frog}
 * which has properties such as {@link #lifeProp} and 
 * {@link #scoreProp} and basic functions such as {@link 
 * #jump(DIRECTION, boolean)}
 */

public class Frog extends PlayerAvatar {
	
	/** the size of all images belonging to this object */
	private static final int IMG_SIZE = 40;

	/**
	 * @param startX  starting x position of this {@code Frog} object
	 * @param startY  starting y position of this {@code Frog} object
	 */
	public Frog(double startX, double startY) {
		super(startX,startY);
		 
	}
	
	@Override
	protected void initImages() {
		imgW1 = new Image(FilePath.FROG_UP, IMG_SIZE, IMG_SIZE, true, true);
		imgW2 = new Image(FilePath.FROG_UPJUMP, IMG_SIZE, IMG_SIZE, true, true);
		carDeath = new ArrayList<>() {
			{
				for(int i=1;i<4;i++)
				add(new Image("file:src/main/resources/death/cardeath" + i + ".png", IMG_SIZE, IMG_SIZE, true, true));
			}
		};
		
		
		waterDeath = new ArrayList<>(){
			{
				for(int i=1;i<5;i++)
				add(new Image("file:src/main/resources/death/waterdeath" + i + ".png", IMG_SIZE, IMG_SIZE, true, true));
			}
		};
	
	}



}
