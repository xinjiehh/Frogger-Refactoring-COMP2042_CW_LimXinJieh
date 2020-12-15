package frogger.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.reflections.Reflections;

import frogger.constant.LOG_TYPE;
import frogger.constant.OBSTACLE_TYPE;
import frogger.constant.RiverSprite;
import frogger.model.npc.Log;
import frogger.model.npc.NPC;
import frogger.model.npc.Obstacle;
import frogger.model.npc.Turtle;
import frogger.model.npc.WetTurtle;

/**
 * This method allows random generation of {@link NPC} such as {@link 
 * Obstacle}, {@link Log}, {@link Turtle} and {@link WetTurtle}. To add 
 * a river {@code NPC}, you have to add the corresponding name in switch-case 
 * in {@link #getRandomRiverSprite()}. To add new road {@code NPC}, you have 
 * to add another entry in {@link OBSTACLE_TYPE} enum and modify {@link 
 * #getRandomRoadSprite()}
 */

public enum RandomSpriteGenerator {
	
	INSTANCE;
	
	/** list of variations of obstacle */
	private static final OBSTACLE_TYPE[] OBSTACLES = OBSTACLE_TYPE.values();
	
	/** list of variations of logs */
	private static final LOG_TYPE[] LOGS = LOG_TYPE.values();
	
	/** list of river sprites, which are {@link NPC} objects that implement {@link RiverSprite} */
	private List<String> riverSprites = null;
	
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
	 * a random number generator to select from the classes 
	 * that implement {@link RiverSprite}. Reflections is
	 * used to get the corresponding classes.
	 * 
	 * @return new river {@link NPC} 
	 */
	public NPC getRandomRiverSprite() {
		
		if(riverSprites==null)
			initRiverSpriteList();

		Random rand = new Random();
		String sprite = riverSprites.get(rand.nextInt(riverSprites.size()));
		
		return switch (sprite) {
			case "Log" -> getRandomLog();
			case "Turtle" -> new Turtle();
			case "WetTurtle" -> new WetTurtle();
			default -> throw new IllegalArgumentException("Unexpected value: " + sprite);
		};
		
	}

	/**
	 * This method initializes the list of river sprites by getting
	 * the classes that implements {@link RiverSprite}
	 */
	private void initRiverSpriteList() {
		Reflections reflections = new Reflections("frogger.model.npc");
		Set<Class<? extends RiverSprite>> classes = reflections.getSubTypesOf(frogger.constant.RiverSprite.class);
		riverSprites = new ArrayList<String>();
		classes.forEach(c -> {riverSprites.add(c.getSimpleName());});
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

//RiverSpriteType[] sprites = RiverSpriteType.values();
//Random rand = new Random();
//RiverSpriteType sprite = sprites[rand.nextInt(sprites.length)];
///**
// * This method returns either {@link Turtle} or {@link WetTurtle}
// * using random number generator
// * @return turtle {@link NPC}
// */
//private static NPC getRandomTurtle() {
//	return ((int)Math.round(Math.random())==0) ? new WetTurtle() : new Turtle();
//}
