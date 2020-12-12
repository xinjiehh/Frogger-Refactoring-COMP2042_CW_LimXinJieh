package frogger.util;

import java.util.List;
import java.util.Arrays;

import frogger.constant.DEATH;
import frogger.model.Actor;
import frogger.model.PlayerAvatar;
import frogger.model.npc.NPC;
import frogger.model.npc.Sinkable;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * This class is responsible for all the sprite animation.
 * 
 * Following the single responsibility principle, this was supposed 
 * to be 3 different classes, but the code was too similar, hence it 
 * is currently all combined in one class. In the future, if the 
 * animation gets more complex, this class can be easily be further 
 * separated into:
 * <u1>
 * <li> auto animation for {@link NPC}
 * <li> death animation for {@link PlayerAvatar}
 * <li> sinkable animation for {@link Sinkable}
 * @see {@link BonusAnimation} for example of non-sprite animation
 *
 */
public class SpriteAnimation extends MyAnimation {
	
	private final Actor actor;
	private final List<Image> images;
	
	public SpriteAnimation(Actor actor, List<Image> images) {
		super();
		this.actor=actor;
		this.images=images;
		initAnimation();

	}
	

	@Override
	protected void initAnimation() {
		animation = new Transition() {
		    {
		        if(actor instanceof NPC) {
		    		setCycleCount(INDEFINITE);
		    		setCycleDuration(Duration.millis(2000));
		    	} else 
		    		setCycleDuration(Duration.millis(600)); // total time for animation
		        
		    }

		    @Override
		    protected void interpolate(double fraction) {
		        int index = (int) (fraction*(images.size()));
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
		
		if(actor instanceof PlayerAvatar) {
			
			PlayerAvatar player = (PlayerAvatar) actor;
			animation.setOnFinished(e->{
				player.restartPlayer();
				player.setDeath(DEATH.NULL);
				donePlaying = true;
			});
		}
		
			
		
		
	}


}