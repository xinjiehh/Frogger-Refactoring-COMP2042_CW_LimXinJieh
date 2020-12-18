package frogger.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.reflections.Reflections;

import frogger.constant.variation.LOG_TYPE;
import frogger.constant.variation.OBSTACLE_TYPE;
import frogger.constant.WaterSprite;
import frogger.model.npc.Log;
import frogger.model.npc.NPC;
import frogger.model.npc.Obstacle;
import frogger.model.npc.Turtle;
import frogger.model.npc.WetTurtle;

/**
 * This method allows random generation of {@link NPC} such as {@link 
 * Obstacle}, {@link Log}, {@link Turtle} and {@link WetTurtle}. To add 
 * a river {@code NPC}, you have to add the corresponding name in switch-case 
 * in {@link #getRandomWaterSprite()}. To add new road {@code NPC}, you have
 * to add another entry in {@link OBSTACLE_TYPE} enum and modify {@link 
 * #getRandomLandSprite()}
 */

public enum RandomSpriteGenerator {
	/** singleton instance for this class */
	INSTANCE;
	
	/** list of variations of obstacle */
	private static final OBSTACLE_TYPE[] OBSTACLES = OBSTACLE_TYPE.values();
	
	/** list of variations of logs */
	private static final LOG_TYPE[] LOGS = LOG_TYPE.values();
	
	/** list of river sprites, which are {@link NPC} objects that implement {@link WaterSprite} */
	private List<String> waterSprites = null;
	
	/**
	 * This method return a new road {@link NPC} by selecting from
	 * {@link OBSTACLE_TYPE} enum
	 * @return new road {@link NPC} 
	 */
	public NPC getRandomLandSprite() {
		Random rand = new Random();
		return new Obstacle(OBSTACLES[rand.nextInt(OBSTACLES.length)]);
	}
	
	/**
	 * This method return a new river {@link NPC} by using 
	 * a random number generator to select from the classes 
	 * that implement {@link WaterSprite}. Reflections is
	 * used to get the corresponding classes.
	 * 
	 * @return new river {@link NPC} 
	 */
	public NPC getRandomWaterSprite() {
		
		if(waterSprites ==null)
			initWaterSpriteList();

		Random rand = new Random();
		String sprite = waterSprites.get(rand.nextInt(waterSprites.size()));
		
		return switch (sprite) {
			case "Log" -> getRandomLog();
			case "Turtle" -> new Turtle();
			case "WetTurtle" -> new WetTurtle();
			default -> throw new IllegalArgumentException("Unexpected value: " + sprite);
		};
		
	}

	/**
	 * This method initializes the list of river sprites by getting
	 * the classes that implements {@link WaterSprite}
	 */
	private void initWaterSpriteList() {
		Reflections reflections = new Reflections("frogger.model.npc");
		Set<Class<? extends WaterSprite>> classes = reflections.getSubTypesOf(WaterSprite.class);
		waterSprites = new ArrayList<String>();
		classes.forEach(c -> waterSprites.add(c.getSimpleName()));
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
