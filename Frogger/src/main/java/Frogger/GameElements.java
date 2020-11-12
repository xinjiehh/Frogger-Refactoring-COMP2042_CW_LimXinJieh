package Frogger;

//mainpane.getURL path
import java.util.ArrayList;

public class GameElements {
	//Animal frog;
	//gamePane is not passed as param so only GVM can access it 
	

	private ArrayList<Actor> gameElements = new ArrayList<Actor>();
	
	public GameElements() {
		//createFrog();
		createBackground();
		addEnds();

	}

	
	private void createBackground() {
		
		BackgroundImage froggerback = new BackgroundImage();
		//gamePane.add(froggerback);
		gameElements.add(froggerback);
	}
	




	
	private void addStuff() {

		//Obstacle obstacle = new Obstacle("file:src/main/resources/truck1Right.png", 25, 25, 3);
		//Obstacle obstacle1 = new Obstacle("file:src/main/resources/truck2Right.png", 100, 100,2 );
		//Obstacle obstacle2 = new Obstacle("file:src/main/resources/truck1Right.png",0,  150, 1);

	}


	private void addEnds() { //size 60
		int yPos = 96;
		int initialXPos = 12;
		for(int i=0; i<5;i++) {
			int xPos = initialXPos + i*127;
			gameElements.add(new End(xPos,yPos));
		}
	}
	
	public void addLane1Elements(int num) { //3
		int yPos = 649;
		int initialXPos = 0;
		for(int i=0; i<num;i++) {
			int xPos = initialXPos + i*300;
			gameElements.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", xPos, yPos, 1, 120, 120));
		}
		
		gameElements.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", 0, 649, 1, 120, 120));
		gameElements.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", 300, 649, 1, 120, 120));
		gameElements.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", 600, 649, 1, 120, 120));
		//background.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", 720, 649, 1, 120, 120));

	}
	
	public void addLane2Elements(int num) { //4
		int yPos = 597;
		int initialXPos = 100;
		for(int i=0; i<num;i++) {
			int xPos = initialXPos + i*150;
			gameElements.add(new Obstacle("file:src/main/resources/car1Left.png", xPos, yPos, -1, 50, 50));
		}
		
	}
	
	public void addLane3Elements(int num) { //2
		int yPos = 540;
		int initialXPos = 100;
		for(int i=0; i<num;i++) {
			int xPos = initialXPos + i*150;
			gameElements.add(new Obstacle("file:src/main/resources/truck2Right.png", xPos, yPos, -1, 50, 50));
		}
		
	}
	
	public void addLane4Elements(int num) { //1
		int yPos = 490;
		int initialXPos = 500;
		for(int i=0; i<num;i++) {
			int xPos = initialXPos + i*100;
			gameElements.add(new Obstacle("file:src/main/resources/car1Left.png", xPos, yPos, -5, 50, 50));
		}
		
	}
	public void addLane5Elements() {
		
		gameElements.add(new Turtle(500, 376, -1, 130, 130)); //x,y,s,w,h
		gameElements.add(new Turtle(300, 376, -1, 130, 130));
		gameElements.add(new WetTurtle(700, 376, -1, 130, 130));
//		
		
	}
	
	
	public void addLane6Elements() {
		gameElements.add(new Turtle(500, 376, -1, 130, 130)); //x,y,s,w,h
		gameElements.add(new Turtle(300, 376, -1, 130, 130));
		gameElements.add(new WetTurtle(700, 376, -1, 130, 130));
	}

	//53
	public void addLane6Elements(int num) { //150
		//LogImage type = LogImage.SHORT;
		//ArrayList<Log> elementList = new ArrayList<Log>();
		int initialXPos = 50;
		int yPos = 329;
		
		for(int i = 0; i < num; i++) {
			int xPos = i*220 + initialXPos;
			gameElements.add(new Log(LogImage.SHORT, 150, xPos, yPos, 0.75));
		}
		
	}


	public void addLane7Elements(int num) { //300
		int initialXPos = 0;
		int yPos = 276;
		
		for(int i = 0; i < num; i++) {
			int xPos = initialXPos + i*400;
			gameElements.add(new Log(LogImage.LONG, 300, xPos, yPos, -2)); //size,x,y,speed
			
		}
		//background.add(new Log("file:src/main/resources/logs.png", 300, 800, 276, -2));
		
	}
//276 - 166( 300) = 110
	//329 - 276 ( = 
	
	public void addLane8Elements(int num) {
		int initialXPos = 200;
		int yPos = 217;
		for(int i = 0; i<num; i++) {
			int xPos = initialXPos + i*200;
			gameElements.add(new WetTurtle(xPos, yPos, -1, 130, 130));
		}
		
	}

	public void addLane9Elements(int num) { //150
		int yPos = 166;
		for(int i = 0; i < num; i++) {
			int xPos = i + i*220;
			gameElements.add(new Log(LogImage.SHORT, 150, xPos, yPos, 0.75));
			
		}
		//size,x,y,speed
		//background.add(new Log("file:src/main/resources/log3.png", 150, 0, 166, 0.75));
		 
		
	}
	
	public ArrayList<Actor> getGameElementsList() {
		return gameElements;
	}
	


}


//background.add(new Log("file:src/main/resources/log2.png", 200, 100, 1));
//background.add(new Log("file:src/main/resources/log2.png", 0, 100, 1));
//background.add(new Log("file:src/main/resources/log2.png", 100, 120, -1));
//background.add(new Log("file:src/main/resources/log2.png", 200, 120, -1));
//background.add(new Log("file:src/main/resources/log2.png", 100, 140, 1));
//background.add(new Log("file:src/main/resources/log2.png", 200, 140, 1));
//background.add(new Log("file:src/main/resources/log2.png", 100, 160, -1));
//background.add(new Log("file:src/main/resources/log2.png", 300, 160, -1));
//background.add(new Log("file:src/main/resources/log2.png", 100, 180, 1));
//background.add(new Log("file:src/main/resources/log2.png", 200, 180, 1));
//background.add(new Log("file:src/main/resources/log2.png", 100, 200, -1));
//background.add(new Log("file:src/main/resources/log2.png", 200, 200, -1));
//background.add(new Log("file:src/main/resources/log2.png", 100, 220, 1));
//background.add(new Log("file:src/main/resources/log2.png", 200, 220, 1));
//background.add(new Log("file:src/main/resources/log2.png", 400, 220, 1));
//End end2 = new End();
//End end3 = new End();
//End end4 = new End();
//End end5 = new End();

