package frogger.model;


import java.util.ArrayList;

import frogger.Main;
import frogger.constant.LOG_TYPE;
import frogger.constant.OBSTACLE_TYPE;
import frogger.model.npc.Log;
import frogger.model.npc.NPC;
import frogger.model.npc.Obstacle;
import frogger.model.npc.Turtle;
import frogger.model.npc.WetTurtle;
import frogger.util.RandomSpriteGenerator;

//a lot of redundant classes,
//use switch to determine class, then get the constructor
//new way: switch to create nw object then clone;
//TODO better to return hashmap or object? 
//is hashmap considered encapsulation 
//WHICH USES HashMap<Actor,Integer> for Actor and distance init.
//TODO THIS IS FACTORY METHOD
//this ALLOWS SUBCLASS TO CHOOSE TYPE OF OBJECT TO CREATE
//JAVATPOINT

/**
 * This class implements factory method.  This class contains the creation 
 * code for {@link NPC} subclass objects, creating {@code NPC} objects of 
 * different subclasses based on the lane number passed as constructor argument. 
 * <p>
 * This follows original Frogger implementation where type of {@code NPC} for each lane 
 * is fixed. However, one can easily modify this by tweaking  {@link #initLaneElement} and 
 * using {@link RandomSpriteGenerator} as shown in the method {@link #initRandomLaneElement}
 * 
 * Switch cases are used to instantiate a new object based on the lane number passed, 
 * then {@link #createNPC()} is called to create clones of that object using the 
 * PROTOTYPE method. 
 * 
 * @see NPC
 *
 */

public final class Lane {
	
	/** the y position of each lane */
	private static final int[] LANES_Y = {649, 599, 546, 492, 376, 332, 280, 217, 171};
	
	/** the lane number to create */
	private int laneNum;
	
	/** the non-player character of this {@code Lane}*/
	private NPC npc;
	
	/** the distance between each {@link #npc} object */
	private double distance;
	
	private ArrayList<NPC> laneElements = new ArrayList<NPC>();
	
	
	
	/**
	 * This method is a public constructor which initializes the game
	 * element according to the given lane number
	 * @param i lane number
	 */
	public Lane(int i) {
		
		this.laneNum = i;
		initLaneElement();
	}
	
	/**
	 * This method selects the class of element to be constructed according
	 * to the given lane number. In the original Frogger game, this is fixed. 
	 * However, if you would like to customise it you can do so using a rng
	 * in this function.
	 */
	private void initLaneElement() {

		switch (laneNum) {
			case 1 -> {
				npc = new Obstacle(OBSTACLE_TYPE.TRUCK1);// 720; //800
				System.out.println("lane 1 height " + npc.getHeight());
			}
			
			case 2, 4 -> {
				npc = new Obstacle(OBSTACLE_TYPE.CAR);//650
				System.out.println("lane 2,4 height " + npc.getHeight());
			}
			
			case 3 -> {
				npc = new Obstacle(OBSTACLE_TYPE.TRUCK2);//780
				System.out.println("lane 3 height " + npc.getHeight());
			}
			
			case 5 -> {
				npc = ((int)Math.round(Math.random())==0) ? new Turtle() : new WetTurtle();//730
				System.out.println("lane 5 height " + npc.getHeight());
			}
			
			case 6, 9 -> {
				npc = new Log(LOG_TYPE.SHORT);// 750; //800
				System.out.println("lane 6,9 height " + npc.getHeight());
			}
			
			case 7 -> {
				npc = new Log(LOG_TYPE.LONG);//900
				System.out.println("lane 7 height " + npc.getHeight());
			}
			
			case 8 -> {
				
				npc = new WetTurtle();//730
				System.out.println("lane 8 height " + npc.getHeight());
			}
			
			default -> throw new IllegalArgumentException("Lane " + laneNum + "does not exist. Element not created.");
		}
		
		this.distance = npc.getWidth() + Main.STAGE_W;
	}
	

	
	
	public void initRandomLaneElement(String str) {
		
		if(str.equals("river")) {
			npc = RandomSpriteGenerator.INSTANCE.getRandomRoadSprite();
			
		} else if (str.equals("road")) {
			npc = RandomSpriteGenerator.INSTANCE.getRandomRiverSprite();
		}
		
	}
	

	
	
	/**
	 * This method allows customising speed and number of elements
	 * in this {@code Lane} object
	 * @param num  number of elements
	 * @param speed  speed of elements
	 * @return this {@code Lane} object
	 */
	public Lane addElements(int num, double speed) { 
		int xPos = 0;
		for(int i=0; i<num;i++) {
			
			createNPC();
			npc.setSpeed(speed);
			npc.setX(xPos);
			xPos += distance/num;
			laneElements.add(npc);		
		}
		
		return this;
	}
	
	/**
	 * This method returns list of elements in this {@code Lane}
	 * @return {@code ArrayList<Actor>} of elements in this 
	 * {@code Lane} object
	 */
	public ArrayList<NPC> getElementsList(){
		return laneElements;
	}

	/**
	 * This method creates {@link NPC} by cloning the {@code NPC} object 
	 * initialised in {@link #initLaneElement()}
	 */
	private boolean createNPC() {
	
		npc = npc.clone();
		npc.setY(LANES_Y[laneNum-1]);
		return true;

	}
	

}



///**
// * This method returns distance between each {@link #npc} object in a lane
// * @return  {@code integer} value of distance between each {@link NPC}
// * object
// */
//protected int getDistance() {
//	return this.distance;
//}

