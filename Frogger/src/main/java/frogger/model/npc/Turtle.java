package frogger.model.npc;

import java.util.ArrayList;

import frogger.constant.FilePath;
import frogger.constant.RiverSprite;
import frogger.util.animation.NPCAnimation;
import javafx.scene.image.Image;

/**
 * This class is the model for turtle 
 *
 */
public class Turtle extends NPC implements RiverSprite {
	private static final int SIZE = 130;
	private static final Image turtle1 = new Image(FilePath.TURTLE1,SIZE,SIZE,true,true);
	private static final Image turtle2 = new Image(FilePath.TURTLE2_DRY,SIZE,SIZE,true,true);
	private static final Image turtle3 = new Image(FilePath.TURTLE3_DRY,SIZE,SIZE,true,true);
	private ArrayList<Image> images = new ArrayList<Image>() {
		{
			add(turtle1);
			add(turtle2);
			add(turtle3);
		}
		
	
	};

	public Turtle() {
		super();
		setImage(turtle2);
		NPCAnimation anim = new NPCAnimation(this, images);
		anim.play();
	}
	

	@Override
	protected void playAnimation(long now) {
//		if (now/900000000  % 3 ==0) {
//			setImage(turtle2);
//			
//		}
//		else if (now/900000000 % 3 == 1) {
//			setImage(turtle1);
//			
//		}
//		else if (now/900000000 %3 == 2) {
//			setImage(turtle3);
//			
//		}
	}


	@Override
	public NPC clone() {
		return new Turtle();
	}


}
