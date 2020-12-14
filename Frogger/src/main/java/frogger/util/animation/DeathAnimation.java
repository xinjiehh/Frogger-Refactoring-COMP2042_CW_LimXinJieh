package frogger.util.animation;

import java.util.List;

import frogger.constant.DEATH;
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
 * @see BonusAnimation for example of non-sprite animation
 *
 */
public class DeathAnimation extends SpriteAnimationTemplate {
	
	private final PlayerAvatar player;
	private final List<Image> images;
	
	public DeathAnimation(PlayerAvatar player, List<Image> images) {
		super();
		this.player=player;
		this.images=images;
		initAnimation();

	}
	

	@Override
	protected void initAnimation() {
		animation = new Transition() {
		    {
		    	
		        setCycleDuration(Duration.millis(600)); // total time for animation
		        
		    }

		    @Override
		    protected void interpolate(double fraction) {
		        int index = (int) (fraction*(images.size()));
		        index = Math.min(index, images.size() - 1);
		        player.setImage(images.get(index)); 
		        

		        	
		    }
		};
		
		
		animation.setOnFinished(e->{
			player.restartPlayer();
			player.setDeath(DEATH.NULL);
			donePlaying = true;
		});
		
		
			
		
		
	}


}