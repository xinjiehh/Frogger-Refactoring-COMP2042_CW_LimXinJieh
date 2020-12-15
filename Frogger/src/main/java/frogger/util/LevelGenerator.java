package frogger.util;

import frogger.model.Lane;
import frogger.model.LevelTemplate;

/**
 * This class contains a fixed algorithm used to generate levels
 *
 */
public class LevelGenerator extends LevelTemplate {
	/** current level of the game */
	private int level;
	
	/** speed increment of all {@link NPC} */
	private double speed = 0;
	
	/** the number of race cars in lane 4 */
	private int raceCar = 1;
	
	/** the number of logs in lane 7 */
	private int longLog = 2;
	
	/** the number of turtles in lane 5 */
	private int turtle5 = 4;
	
	/** the number of turtles in lane 8 */
	private int turtle8 = 3;
	
	public LevelGenerator(int level) {
		super();
		this.level = level;
		initializeLanes();
		

	}
	
	/**
	 * This method initialize the each lane with different
	 * number and speed of elements. Element type is fixed
	 * for each lane. However, you can easily change this in
	 * {@link Lane} class.
	 * 
	 * increase speed for even number levels
	 * for 4,8    : increase number of race cars
	 * for 3,6,9  : decrease number of turtles
	 * for 5,7    : decrease number of turtles
	 * 
	 */
	@Override
	public void initializeLanes() {
		
		speed += (level%2==0) ? 0.3 : 0;
		turtle5 -= (level%4==0) ? 1 : 0;
		raceCar += (level%3==0) ? 1 : 0; 
		turtle8 -= (level==5 || level == 7) ? 1 : 0;
		

		addLane(1).initLaneElements(3,1+speed); //adds to gameElements list
		addLane(2).initLaneElements(4,-1+speed);
		addLane(3).initLaneElements(2,1+speed);
		addLane(4).initLaneElements(raceCar,5+speed);
		addLane(5).initLaneElements(turtle5,-1+speed);
		addLane(6).initLaneElements(3,0.75+speed);
		addLane(7).initLaneElements(longLog,-2+speed);
		addLane(8).initLaneElements(turtle8,-2+speed);
		addLane(9).initLaneElements(3,0.75+speed);

	}


}

