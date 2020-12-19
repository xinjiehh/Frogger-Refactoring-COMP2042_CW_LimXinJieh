package frogger.model.npc;

import java.util.ArrayList;

import frogger.constant.FilePath;
import frogger.constant.WaterSprite;
import frogger.util.animation.NPCAnimation;
import javafx.scene.image.Image;

/**
 * This class is the model for turtle 
 *
 */
public class Turtle extends NPC implements WaterSprite {
	
	/** size of all images belonging to this object */
	private static final int SIZE = 130;
	
	/** image for animation */ 
	private static final Image turtle1 = new Image(FilePath.TURTLE1,SIZE,SIZE,true,true);
	
	/** image for animation */ 
	private static final Image turtle2 = new Image(FilePath.TURTLE2_DRY,SIZE,SIZE,true,true);
	
	/** image for animation */ 
	private static final Image turtle3 = new Image(FilePath.TURTLE3_DRY,SIZE,SIZE,true,true);
	
	/** list containing the images for animation */
	private static final ArrayList<Image> images = new ArrayList<Image>() {
		{
			add(turtle1);
			add(turtle2);
			add(turtle3);
		}};



	/** This is the public constructor for this {@code Turtle} object */
	public Turtle() {
		super();
		setImage(turtle2);
		initAnimation();
		
	}

	/** 
	 * This method initializes and playes the animation for this 
	 * {@code Turtle} object
	 */
	private void initAnimation() {
		NPCAnimation anim = new NPCAnimation(this, images);
		anim.play();

	}

	@Override
	public NPC clone() {
		return new Turtle();
	}


}
