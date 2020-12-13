package frogger.constant;

/**
 * This interface
 * <u1>
 * <li> achieves loose coupling by reducing dependency of {@link NPC} 
 * on the enums. 
 * <li> improves extendability by tying {@link NPC} variation enums 
 * together (such as {@link LOG_TYPE} and {@link OBSTACLE_TYPE})
 * </li>
 * <p>
 * To add a new enum for variation, implement this interface. 
 * 
 */
public interface NPCType {
	/**
	 * This method returns the predefined size of the image
	 * @return  integer value of the image size
	 */
	public int getSize();

	/**
	 * This method returns the predefined URL of the image
	 * @return  {@code String} value of the image URL path
	 */
	public String getURL();
}
