package frogger.constant;

public enum LOG_TYPE {
	
	LONG("file:src/main/resources/moving/logLong.png",300),
	MEDIUM("file:src/main/resources/moving/logMedium.png",225),
	SHORT("file:src/main/resources/moving/logShort.png",150);
	
	
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
