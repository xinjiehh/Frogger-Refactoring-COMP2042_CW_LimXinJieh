package frogger.model;

import frogger.model.npc.NPC;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * This class implements template method. 
 * This class contains all the necessary methods required to create a level.
 * Following the classic Frogger game, this allows customisation of speed and
 * number of elements in each lane. For customisation of type of elements in 
 * each lane, refer to {@link Lane}
 *
 */

public abstract class LevelTemplate {
	
	//contains all the game characters
	protected ArrayList<Node> levelElements = new ArrayList<Node>();
	
	protected Lane lane;
	protected int laneNum;
	
	//useful when removing NPC by lanes, replace level elements
	protected List<List<NPC>> laneElementsList = new LinkedList<List<NPC>>();
	
	//list of swamp objects
	protected ArrayList<Actor> swamps = new ArrayList<Actor>();
	
	/**
	 * Method to be overriden by subclass to define 
	 */
	public abstract void initializeLanes();
	
	public LevelTemplate addLane(int laneNum) {
		this.laneNum = laneNum;
		this.lane = new Lane(laneNum);
		return this;
	}
	
	/**
	 * Add elements to {@link Lane} object, then add the generated elements 
	 * to the list of level elements 
	 * @param num number of elements to add to the lane
	 * @param speed speed of the elements in the lane
	 */
	public void initLaneElements(int num, double speed) {
		this.lane.addElements(num,speed);
		levelElements.addAll(this.lane.getElementsList());
		laneElementsList.add(laneNum-1, this.lane.getElementsList());		
	}
	
	
	/**
	 * @return list of all the game elements
	 */
	public ArrayList<Node> getAllElementsNode(){
		return this.levelElements;
	}
	
	

	/**
	 * This method allows getting NPC by lane
	 * @param i lane number
	 * @return a list of NPC in a specified lane
	 */
	public List<NPC> getLaneElements(int i){
		return laneElementsList.get(i-1);
		
	}
	
	/**
	 * This method allows removal of NPC by lane, useful 
	 * when changing certain lanes in certain levels
	 * @param i lane number
	 * 
	 */
	public void removeLaneElements(int i) {
		laneElementsList.remove(i-1);
	}
	
	
	/**
	 * This method gets the list of all the elements, arranged in lanes
	 * @return a list of lists of elements in different lanes
	 */
	public List<List<NPC>> getAllLaneElements(){
		return laneElementsList;
		
	}



}