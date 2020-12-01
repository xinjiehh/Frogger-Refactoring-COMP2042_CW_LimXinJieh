package frogger.constant;

public enum OBSTACLE_TYPE {
	
	TRUCK1("file:src/main/resources/moving/truck1Right.png", 120),
	TRUCK2("file:src/main/resources/moving/truck2Right.png", 200),
	CAR("file:src/main/resources/moving/car1Left.png", 50);
	
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
