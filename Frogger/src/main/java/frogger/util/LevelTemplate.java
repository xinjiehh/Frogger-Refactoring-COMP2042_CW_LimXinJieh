package frogger.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.Node;


/**
 * This class implements template method. 
 * This class contains all the necessary methods required to create a level.
 * Following the classic Frogger game, this allows customisation of speed and
 * number of elements in each lane. For customisation of type of elements in 
 * each lane, refer to {@link Lane}
 *
 */

public abstract class LevelTemplate {

	/** list containing all game characters */
	protected ArrayList<Node> levelElements = new ArrayList<Node>();

	/** this {@link Lane} object */
	protected Lane lane;

	/** number of the lane to be created */
	protected int laneNum;
	
	/** list of list of elements by lane */
	protected List<List<Node>> laneElementsList = new LinkedList<List<Node>>();
	
	/**
	 * Method to be overridden by subclass to define
	 */
	public abstract void initializeLanes();

	/**
	 * This method is used to create a lane
	 * @param laneNum  lane number of the lane to create
	 * @return  the lane created
	 */
	public LevelTemplate createLane(int laneNum) {
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
	 * This method returns the list of all the lane elements
	 * @return list of all the lane elements
	 */
	public ArrayList<Node> getAllElements(){
		return this.levelElements;
	}
	
	

	/**
	 * This method allows getting NPC by lane
	 * @param i lane number
	 * @return a list of NPC in a specified lane
	 */
	public List<Node> getLaneElements(int i){
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



}