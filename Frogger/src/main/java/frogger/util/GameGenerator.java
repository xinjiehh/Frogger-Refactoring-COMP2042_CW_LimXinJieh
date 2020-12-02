package frogger.util;

import java.util.ArrayList;
import java.util.List;

import frogger.model.Frog;
import frogger.model.NPC.Actor;
import frogger.model.NPC.Swamp;
import javafx.scene.Node;


/**
 * This class adds all the game elements (NPC and frog) for each level
 *
 */

public class GameGenerator {
	
	protected final int MAX_LEVEL = 10;
	private final int SWAMP_Y = 96;
	private Frog frog; 
	private List<Node> characterList;
	private ArrayList<Actor> swamp = new ArrayList<Actor>();
		
	
	public GameGenerator(int level) { 
		
		LevelGenerator loader = new LevelGenerator(level);
		characterList = loader.getAllElementsNode();
		initSwamp();
		initFrog();

	}
	
	/**
	 * This method initializes Frog object and adds it to
	 * list of game characters
	 */
	private void initFrog() {
		frog = new Frog();
		characterList.add(frog);
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
	public Frog getFrog() {
		return this.frog;
	}
	
	
	/**
	 * This method initializes the swamp objects
	 */
	private void initSwamp() {
		
		int initialXPos = 12;
		for(int i=0; i<5;i++) {
			int xPos = initialXPos + i*127;
			characterList.add(new Swamp(xPos,SWAMP_Y));
			swamp.add(new Swamp(xPos,SWAMP_Y));
		}
		
		
	
	}

	

	
}
