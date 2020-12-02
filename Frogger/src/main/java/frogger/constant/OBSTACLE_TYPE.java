package frogger.constant;

public enum OBSTACLE_TYPE {
	
	TRUCK1(FilePath.TRUCK1, 120),
	TRUCK2(FilePath.TRUCK2, 200),
	CAR(FilePath.CAR, 50);
	
	private String url; 
	private int size;
	
	
	public String getURL() {
		return this.url;
	}
	
	public int getSize() {
		return this.size;
	}
	
	OBSTACLE_TYPE(String url, int size) {
		this.url = url;
		this.size = size;
	}
}
