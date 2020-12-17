package frogger.util.animation;

import java.util.List;

import frogger.model.npc.NPC;
import frogger.model.npc.Sinkable;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * This class is responsible for the transforming animation of the {@link NPC}
 * objects
 */
public class NPCAnimation extends SpriteAnimationTemplate {
	
	/** the images to be rotated for this animation */
	private List<Image> images;
	
	/** the {@link NPC} object to be animated */
	private NPC npc;

	/**
	 * @param npc  {@link NPC} object to be transformed
	 * @param images  {@code List} of {@code Image} objects for animation
	 */
	public NPCAnimation(NPC npc, List<Image> images) {
		super();
		this.npc = npc;
		this.images= images;
	}
	
	@Override
	protected void initAnimation() {
		animation = new Transition() {
		    {
		    	setCycleCount(INDEFINITE);
		        setCycleDuration(Duration.millis(2000)); // total time for animation
		    }

		    @Override
		    protected void interpolate(double fraction) {
		        int index = (int) (fraction*(images.size()-1));
		        index = Math.min(index, images.size() - 1);
		        npc.setImage(images.get(index)); 
		        
		        if(npc instanceof Sinkable) {
					((Sinkable) npc).setSunk(index == images.size() - 1);

		        }
		    }
		};
		
	}

}
