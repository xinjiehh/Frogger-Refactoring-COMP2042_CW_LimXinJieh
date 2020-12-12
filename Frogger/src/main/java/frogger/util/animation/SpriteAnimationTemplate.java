package frogger.util.animation;

import frogger.model.GameModel;
import frogger.model.Observer;
import frogger.model.Subject;
import javafx.animation.Transition;

/**
 * This template class is created because all {@code Transition}
 * has the same behaviour: to subscribe to {@link GameModel} game
 * state (pause or play) and handle it accordingly.
 * 
 * For animations that are not continous, the flag {@link #donePlaying}
 * must be set to true once animation ends so it will not be played
 * again if the game state changes from pause to play. 
 * 
 */

public abstract class SpriteAnimationTemplate implements Observer {
	protected Transition animation;
	protected boolean donePlaying = true;
	
	
	public SpriteAnimationTemplate() {
		initAnimation();
		Subject.subscribe("pause", this);
		

	}

	public void play() {
		donePlaying = false;
		animation.play();
	}
	
	public void pause() {
		animation.pause();
	}

	@Override
	public void update(String eventType, Subject s) {
		boolean isPaused = ((GameModel) s).getIsPaused();
		if(isPaused) {
			animation.pause();
		} else {
			if(!donePlaying) {
				animation.play();
			}
				
		}
	}
	
	/**
	 * This method has to be overriden by subclasses to initialize 
	 * the field {@code animation} which is a {@code Transition} object 
	 */
	protected abstract void initAnimation();
	

	

}


//private int imageIndex = 0 ;
//private final int frameTime = 2000 ; // milliseconds
//
//// ...
//
//ImageView imageView = new ImageView();
//List<Image> images = new ArrayList<>();
//// populate images...
//
//Timeline timeline = new Timeline(new KeyEvent(Duration.millis(frameTime),
//    e -> imageView.setImage(images.get(imageIndex++))));
//
//timeline.setCycleCount(images.size());
//timeline.play();
//seq.getChildren().add(animation);
//seq.getChildren().add(new PauseTransition(Duration.millis(200)));
//seq.getChildren().add(animation2);



//animation2 = new Transition() {
//    {
//        setCycleDuration(Duration.millis(3000)); // total time for animation
//    }
//
//    @Override
//    protected void interpolate(double fraction) {
//      
//    }
//};
