package frogger.constant.variation;

import frogger.constant.FilePath;

/**
 * This enum defines the type of obstacles and their
 * corresponding file path and image size
 *
 */

public enum OBSTACLE_TYPE implements NPCType {
	
	TRUCK1(FilePath.TRUCK1, 120),
	TRUCK2(FilePath.TRUCK2, 200),
	CAR(FilePath.CAR, 50);
	
	/** the file path for the corresponding obstacle */
	private final String url; 
	
	/** the size of the corresponding obstacle */
	private final int size;
	
	/** 
	 * This method returns the {@code String} that is the file path
	 * of the media image
	 * @return {@code String} of the image url
	 */
	public String getURL() {
		return this.url;
	}
	
	/**
	 * This method returns the {@code integer} value corresponding
	 * to the size of the obstacle
	 * @return  {@code integer} value of size
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * This method is the private constructor for this enum
	 * @param url  {@code String} corresponding to the image url
	 * @param size  {@code integer} value corresponding to image size
	 */
	OBSTACLE_TYPE(String url, int size) {
		this.url = url;
		this.size = size;
	}

}
