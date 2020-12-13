package frogger.model;

import frogger.view.GameScreen;

/**
 * This class is an implementation of the Observer pattern. 
 * 
 * Any classes interested in the events can implement {@code Observer}, 
 * override the {@link #update()}, and call the 
 * {@link Subject#subscribe()} method to add itself to the 
 * list of subscribers of that particular event. 
 * <p>
 * All individual observers (example: {@link GameScreen})
 * implement this common interface to eliminate direct 
 * dependencies between the subject (example: {@link 
 * PlayerAvatar}, {@link GameModel}) and the dependent objects 
 * (observers).
 *
 */

@FunctionalInterface
public interface Observer {
	/**
	 * This method calls a refresh on the concrete {@code Observer} object
	 *  
	 * @param eventType  {@code String} representation of the event subscribed
	 * @param s  {@link Subject} that has been changed
	 */
    void update(String eventType, Subject s); 

}
