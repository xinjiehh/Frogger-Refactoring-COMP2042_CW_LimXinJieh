package frogger.util;

import java.util.List;

import frogger.model.Frog;
import frogger.model.PlayerAvatar;
import frogger.model.npc.Swamp;
import javafx.scene.Node;


/**
 * This class adds all the game elements (NPC and frog) for each level
 *
 */

public class GameGenerator {
	
	/** the y position of the {@code Swamp} object */
	private final int SWAMP_Y = 96;
	
	/** player object of the game */
	private PlayerAvatar player; 
	
	/** the list of all the characters in the game */
	private List<Node> characterList;
		
	/**
	 * This method initializes this object by loading elements
	 * from {@link LevelGenerator} and initializing {@link #characterList}
	 * and {@link #player}
	 * 
	 * @param level  the {@code integer} value of the current level
	 */
	public GameGenerator(int level) { 
		
		LevelGenerator loader = new LevelGenerator(level);
		characterList = loader.getAllElements();
		initSwamp();
		initFrog();

	}
	
	/**
	 * This method initializes Frog object and adds it to
	 * list of game characters
	 */
	private void initFrog() {
		player = new Frog(300, 706.467);//679.8 + FrogModel.movement;
		characterList.add(player);
	}
	
	/**
	 * This method returns the list which contains
	 * all the game elements
	 * @return  List of Nodes of all the game characters
	 */
	public List<Node> getList(){
		return characterList;
	}
	
	/**
	 * This method returns this Frog object
	 * @return  this Frog object
	 */
	public PlayerAvatar getPlayer() {
		return this.player;
	}
	
	
	/**
	 * This method initializes the {@link Swamp} objects
	 * and adds it to {@link #characterList}
	 */
	private void initSwamp() {
		
		int initialXPos = 12;
		for(int i=0; i<5;i++) {
			int xPos = initialXPos + i*127;
			characterList.add(new Swamp(xPos,SWAMP_Y));
			//swamp.add(new Swamp(xPos,SWAMP_Y));
		}
		
		
	
	}

	

	
}
