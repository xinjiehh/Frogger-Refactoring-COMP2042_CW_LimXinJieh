package frogger.constant.variation;

import frogger.model.npc.NPC;

/**
 * This interface achieves loose coupling by reducing dependency of {@link NPC}
 * on the enums and improves extendability by tying {@link NPC} variation enums
 * together (such as {@link LOG_TYPE} and {@link OBSTACLE_TYPE}).
 *
 * To add a new enum for variation, implement this interface. 
 * 
 */
public interface NPCType {
	/**
	 * This method returns the predefined size of the image
	 * @return  integer value of the image size
	 */
	int getSize();

	/**
	 * This method returns the predefined URL of the image
	 * @return  {@code String} value of the image URL path
	 */
	String getURL();
}
