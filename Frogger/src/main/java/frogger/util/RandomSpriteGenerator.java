package frogger.util;

import java.util.Random;

import frogger.constant.LOG_TYPE;
import frogger.constant.OBSTACLE_TYPE;
import frogger.model.npc.Log;
import frogger.model.npc.NPC;
import frogger.model.npc.Obstacle;
import frogger.model.npc.Turtle;
import frogger.model.npc.WetTurtle;

/**
 * This method allows random generation of {@link NPC} such as {@link 
 * Obstacle}, {@link Log}, {@link Turtle} and {@link WetTurtle}. To add 
 * a river {@code NPC}, you have to add another enum to {@link RiverSprite}
 * and modify the method {@link #getRandomRiverSprite()}. To add new road 
 * {@code NPC}, you have to add another entry in {@link OBSTACLE_TYPE} 
 * enum and modify {@link #getRandomRoadSprite()}
 */

public enum RandomSpriteGenerator {
	
	INSTANCE;
	
	/**This enum represents the type of river {@link NPC} there are */
	enum RiverSprite {
		LOG,
		TURTLE;
	}
	
	private static final OBSTACLE_TYPE[] OBSTACLES = OBSTACLE_TYPE.values();
	private static final LOG_TYPE[] LOGS = LOG_TYPE.values();
	
	/**
	 * This method return a new road {@link NPC} by selecting from
	 * {@link OBSTACLE_TYPE} enum
	 * @return new road {@link NPC} 
	 */
	public NPC getRandomRoadSprite() {
		Random rand = new Random();
		return new Obstacle(OBSTACLES[rand.nextInt(OBSTACLES.length)]);
	}
	
	/**
	 * This method return a new river {@link NPC} by using 
	 * a random number generator to select from {@link RiverSprite}
	 * enum
	 * @return new river {@link NPC} 
	 */
	public NPC getRandomRiverSprite() {
		RiverSprite[] sprites = RiverSprite.values();
		Random rand = new Random();
		RiverSprite sprite = sprites[rand.nextInt(sprites.length)];
		
		switch(sprite) {
		case LOG:
			return getRandomLog();
		case TURTLE:
			return getRandomTurtle();
		default: 
			return null;
		} 
		
	}
	
	/**
	 * This method returns either {@link Turtle} or {@link WetTurtle}
	 * using random number generator
	 * @return turtle {@link NPC}
	 */
	private static NPC getRandomTurtle() {
		return ((int)Math.round(Math.random())==0) ? new WetTurtle() : new Turtle();
	}
	
	/**
	 * This method returns a {@link Log} object by using random 
	 * number generator to select from {@link LOG_TYPE}
	 * @return log {@link NPC}
	 */
	private static NPC getRandomLog() {
		Random rand = new Random();
		return new Log(LOGS[rand.nextInt(LOGS.length)]);
	}



}
