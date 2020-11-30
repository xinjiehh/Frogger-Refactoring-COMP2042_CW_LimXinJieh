package frogger.controller.test;
import frogger.model.Frog;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import trial.FlyBonus;

public class BonusAnimation {
	
	ImageView imageView;
	Frog frog;

	Transition animation;
	FlyBonus bonus;


	public BonusAnimation(FlyBonus bonus) {
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
			System.out.println("invisible");
		});
		
	}
	public void start() {
		animation.play();
	}
	
	public void stop() {
		animation.stop();
	}


}