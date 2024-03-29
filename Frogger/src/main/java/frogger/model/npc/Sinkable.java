package frogger.model.npc;

import frogger.util.CollisionDetector;

/**
 * This class is useful for extension. It ties all the {@link NPC} 
 * objects with sinking abilities together. Currently there is only 
 * {@link WetTurtle}, but this class can easily be extended to create 
 * Crocodile character which has sinking abilities as well. This class 
 * is useful to easily determine if an {@link NPC} is sunken (eg. in 
 * {@link CollisionDetector})
 */
 public interface Sinkable {
	/**
	 * This method returns true if this object is sunken, false otherwise
	 * @return  true if this object is sunken, false otherwise
	 */
	boolean isSunk();

	/**
	 * This method allows to modify {@code isSunk} state of this object
	 * @param b  true if this object is sunken, false otherwise
	 */
 	void setSunk(boolean b);


}
