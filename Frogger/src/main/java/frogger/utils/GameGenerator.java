package frogger.utils;

import java.util.ArrayList;
import java.util.List;

import frogger.model.Frog;
import frogger.model.NPC.Actor;
import frogger.model.NPC.Swamp;
import javafx.scene.Node;
import levels.LevelGenerator;


/**
 * This class adds all the game elements (NPC and frog) for each level
 * @author Lim Xin Jieh (20082200)
 *
 */

public class GameGenerator {
	
	protected final int MAX_LEVEL = 10;
	private final int SWAMP_Y = 96;
	private Frog frog; 
	private List<Node> laneElementsList;
	private ArrayList<Actor> swamp = new ArrayList<Actor>();
		

	
	public GameGenerator(int level) { 
		
		LevelGenerator loader = new LevelGenerator(level);
		laneElementsList = loader.getAllElementsNode();
		initEnds();
		initFrog();

	}


	private void initFrog() {
		frog = new Frog();
		laneElementsList.add(frog);
	}
	
	
	public List<Node> getList(){
		return laneElementsList;
	}
	

	public Frog getFrog() {
		return this.frog;
	}

	private void initEnds() {
		
		int initialXPos = 12;
		for(int i=0; i<5;i++) {
			int xPos = initialXPos + i*127;
			laneElementsList.add(new Swamp(xPos,SWAMP_Y));
			swamp.add(new Swamp(xPos,SWAMP_Y));
		}
		
		
	
	}

	

	
}
