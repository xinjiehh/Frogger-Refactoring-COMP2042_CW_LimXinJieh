package frogger.model.npc;

import java.util.ArrayList;

import frogger.constant.FilePath;
import frogger.constant.RiverSprite;
import frogger.util.CollisionDetector;
import frogger.util.animation.NPCAnimation;
import frogger.util.animation.SpriteAnimationTemplate;
import javafx.scene.image.Image;

/**
 * This class is the model for wet turtle, which implements {@link 
 * Sinkable} that has the property {@code sunk} that is useful in 
 * {@link CollisionDetector} to easily determine if an {@link NPC} 
 * is sunken. There is also an option to define animation without
 * using {@link SpriteAnimationTemplate} by overriding the
 * {@link NPC#playAnimation(long)} which changes the {@code Image}
 * of this object based on the time frame passed
 *
 */
public class WetTurtle extends NPC implements Sinkable, RiverSprite {
	
	private static final int SIZE = 130;
	private static final Image turtle1 = new Image(FilePath.TURTLE1,SIZE,SIZE,true,true);
	private static final Image turtle2 = new Image(FilePath.TURTLE2_WET,SIZE,SIZE,true,true);
	private static final Image turtle3 = new Image(FilePath.TURTLE3_WET,SIZE,SIZE,true,true);
	private static final Image turtle4 = new Image(FilePath.TURTLE4_WET,SIZE,SIZE,true,true);
	private static final ArrayList<Image> images = new ArrayList<Image>() {
		{
			add(turtle1);
			add(turtle2);
			add(turtle3);
			add(turtle4);
		}

	};
	private boolean isSunk;


	public WetTurtle() {
		super();
		setImage(turtle2);
		initAnimation();
	}

	private void initAnimation() {
		NPCAnimation anim = new NPCAnimation(this, images);
		anim.play();
	}

	@Override
	public boolean isSunk() {
		return isSunk;
	}


	@Override
	public void setSunk(boolean b) {
		isSunk = b;
		
	}

	@Override
	public NPC clone() {
		return new WetTurtle();
	}
	
//	if (now/900000000  % 4 ==0) {
//	setImage(turtle2);
//	sunk = false;
//}
//
//else if (now/900000000 % 4 == 1) {
//	setImage(turtle1);
//	sunk = false;
//}
//
//else if (now/900000000 %4 == 2) {
//	setImage(turtle3);
//	sunk = false;
//} 
//
//else if (now/900000000 %4 == 3) {
//	setImage(turtle4);
//	sunk = true;
//}
	
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
