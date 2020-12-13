package frogger.model.npc;

/**
 * This class is useful for extension. It ties all the {@link NPC} 
 * objects with sinking abilities together. Currently there is only 
 * {@link WetTurtle}, but this class can easily be extended to create 
 * Crocodile character which has sinking abilities as well. This class 
 * is useful to easily determine if an {@link NPC} is sunken (eg. in 
 * {@link CollisionDetector})
 */
 public interface Sinkable {
	
	public boolean isSunk();
	
	public void setSunk(boolean b);


}
