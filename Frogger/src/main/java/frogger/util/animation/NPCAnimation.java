package frogger.util.animation;

import frogger.model.Actor;
import frogger.model.npc.Sinkable;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.List;

public class NPCAnimation extends SpriteAnimationTemplate {
	List<Image> images;
	Actor actor;
	

	public NPCAnimation(Actor actor, List<Image> images) {
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
		        index = index > images.size()-1 ? images.size()-1 : index;
		        actor.setImage(images.get(index)); 
		        
		        if(actor instanceof Sinkable) {
		        	if(index==images.size()-1) 
		        		((Sinkable) actor).setSunk(true);
		        	else 
		        		((Sinkable) actor).setSunk(false);

		        }
		    }
		};
		
	}

}
