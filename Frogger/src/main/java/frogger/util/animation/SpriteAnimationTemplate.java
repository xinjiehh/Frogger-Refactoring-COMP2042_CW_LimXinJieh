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
 * For animations that are not continuous, the flag {@link #donePlaying}
 * must be set to true once animation ends so it will not be played
 * again if the game state changes from pause to play. 
 * 
 */

public abstract class SpriteAnimationTemplate implements Observer {
	
	/** the {@code Transition} responsible for this animation */
	protected Transition animation;
	
	/** flag to determine if this animation is in progress */
	protected boolean donePlaying = true;

	/**
	 * This method is a public constructor which initializes
	 * the animation using implementation provided by subclasses
	 * and subscribes this instance to the "pause" event
	 *
	 * @see Subject
	 */
	public SpriteAnimationTemplate() {
		initAnimation();
		Subject.subscribe(this,"pause");
		

	}
	/**
	 * This method plays the animation and sets the 
	 * {@code donePlaying} flag to false
	 */
	public void play() {
		donePlaying = false;
		animation.play();
	}
	
	/**
	 * This method pauses the animation
	 */
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
	 * This method has to be overridden by subclasses to initialize
	 * the field {@code animation} which is a {@code Transition} object 
	 */
	protected abstract void initAnimation();
	

	

}