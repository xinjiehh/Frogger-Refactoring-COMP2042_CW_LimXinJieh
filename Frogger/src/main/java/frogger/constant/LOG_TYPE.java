package frogger.constant;

/**
 * This enum defines the different types of logs and their
 * corresponding file path and image size
 *
 */
public enum LOG_TYPE implements NPCType {
	
	LONG(FilePath.L_LOG,300),
	MEDIUM(FilePath.M_LOG,225),
	SHORT(FilePath.S_LOG,150);
	
	/** the file path for the corresponding log */
	private final String url;
	
	/** the size of the corresponding log */
	private final int size;
	
	/** 
	 * This method returns the {@code String} that is the file path
	 * of the media image
	 * @return {@code String} of the image url
	 */
	@Override
	public String getURL() {
		return this.url;
	}
	
	/**
	 * This method returns the {@code integer} value corresponding
	 * to the size of the log
	 * @return  {@code integer} value of size
	 */
	@Override
	public int getSize() {
		return this.size;
	}
	
	/**
	 * This method is the private constructor for this enum
	 * @param url  {@code String} corresponding to the image url
	 * @param size  {@code integer} value corresponding to image size
	 */
	LOG_TYPE(String url, int size) {
		this.url=url;
		this.size=size;
	}

	@Override
	public String getType() {
		return "log";
	}
	
}
