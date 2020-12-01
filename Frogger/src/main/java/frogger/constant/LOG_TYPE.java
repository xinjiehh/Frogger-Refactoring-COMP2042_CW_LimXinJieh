package frogger.constant;

public enum LOG_TYPE {
	
	LONG(FilePath.L_LOG,300),
	MEDIUM(FilePath.M_LOG,225),
	SHORT(FilePath.S_LOG,150);
	
	
	private String url;
	private int size;
	
	public String getURL() {
		return this.url;
	}
	
	public int getSize() {
		return this.size;
	}
	
	private LOG_TYPE (String url, int size) {
		this.url = url;
		this.size=size;
	}
	
}
