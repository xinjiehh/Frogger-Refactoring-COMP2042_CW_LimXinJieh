package frogger.util.animation;
import frogger.model.PlayerAvatar;
import frogger.model.npc.Swamp;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * This class is responsible for showing and hiding the bonus once
 * {@link PlayerAvatar} object touches {@link Swamp} object which
 * contains a fly
 *
 */
public class BonusAnimation extends SpriteAnimationTemplate {
	
	/** the bonus image of this animation */
	private final ImageView bonus;

	/**
	 * This is the public constructor for this class
	 * @param bonus  the {@code Image} for bonus to be animated
	 */
	public BonusAnimation(ImageView bonus) {
		super();
		this.bonus=bonus;

	}

	@Override
	protected void initAnimation() {
		animation = new Transition() {
		    {
		        setCycleDuration(Duration.millis(1000)); // total time for animation
		    }

		    @Override
		    protected void interpolate(double fraction) {
		        bonus.setVisible(true);
		    }
		};

		animation.setOnFinished(e->{
			bonus.setVisible(false);
			donePlaying = true;
		});
		
	}


}