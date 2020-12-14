package frogger.util.animation;

import java.util.List;

import frogger.model.npc.NPC;
import frogger.model.npc.Sinkable;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class NPCAnimation extends SpriteAnimationTemplate {
	List<Image> images;
	NPC actor;
	

	public NPCAnimation(NPC actor, List<Image> images) {
		super();
		this.actor = actor;
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
		        actor.setImage(images.get(index)); 
		        
		        if(actor instanceof Sinkable) {
					((Sinkable) actor).setSunk(index == images.size() - 1);

		        }
		    }
		};
		
	}

}
