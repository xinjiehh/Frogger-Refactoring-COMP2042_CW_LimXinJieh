package frogger.constant;

/**
 * This enum represents the different game over states 
 *
 */
public enum EndGame {
	/** Next level, when all frog reaches the swamp*/
	NEXT, 
	/**When all levels are completed*/
	WIN,
	/**When all {@code PlayerAvatar} lives are lost*/
	LOSE
}