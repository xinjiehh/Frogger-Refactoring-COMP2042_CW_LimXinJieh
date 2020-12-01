package frogger.util;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BonusAnimation {
	private Transition animation;
	private ImageView bonus;


	public BonusAnimation(ImageView bonus) {
		initImages();
		this.bonus=bonus;
		

	}
	
	
	public void initImages() {
		
	
		animation = new Transition() {
		    {
		        setCycleDuration(Duration.millis(500)); // total time for animation
		    }

		    @Override
		    protected void interpolate(double fraction) {
		        bonus.setVisible(true);
		    }
		};

		animation.setOnFinished(e->{
			bonus.setVisible(false);
		});
		
	}
	public void start() {
		animation.play();
	}
	
	public void stop() {
		animation.stop();
	}


}