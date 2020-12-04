package frogger.model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import frogger.constant.GameOver;
import frogger.controller.GameController;
import frogger.model.npc.Digit;
import frogger.util.GameGenerator;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;



/**
 * This class implements the MVC pattern. It is the Model for GameScreen 
 *  
 *
 */

public class GameModel {
	
	private static final int SCORE_X = 550; 
	private static final int SCORE_Y = 10;
	private static final int MAX_LEVEL = 10;
	
	/** list of played levels and their corresponding scores stored in Map as key-value pair */
	private static List<Map.Entry<Integer, Integer>> levelScoreList = new ArrayList<>();
	
	/** list of level numbers in order */
	private List<String> levels = new ArrayList<String>();
	
	/** list of scores in order */
	private List<String> scores = new ArrayList<String>();
	
	/** current level */
	private int levelNum; 
	
	/** current player character */
	private Frog frog; 
	
	/** array of score digits to add to view */
	private LinkedList<Node> scoreDigit = new LinkedList<Node>(); 
	
	/** index for animation */
	private int animCtr = 0;
	
	/** list of all the characters / game elements to be displayed on view */
	private List<Node> elementList;
	
	/** the state of the game */
	private GameOver state;
	
	/** used to generate the characters / game elements */
	private GameGenerator generator;
	

	/** flag if bonus is played */
	private boolean hasBonus = false;
	
	/** timer responsible for playing the bonus animation */
	private AnimationTimer bonusTimer;
	
	/**
	 * This is the public constructor for this object
	 * @param level  the current level
	 */
	public GameModel(int level) { 
		
		this.levelNum = level;		
		initElements();
		createBonusTimer();
		setScore(frog.getScore());
		GameController.INSTANCE.addToView(elementList);
		
	}

	
	/**
	 * This method returns the list of game elements
	 * @return {@link List} of game element objects ({@link Node}) 
	 */
	public List<Node> getList(){
		return elementList;
	}
	
	/**
	 * This method returns the {@link Frog} object used in this 
	 * {@link GameModel} object
	 * @return  the {@link Frog} object
	 */
	public Frog getFrog() {
		return this.frog;
	}
	
	/**
	 * This method returns the {@link GameOver} state of the
	 * game
	 * 
	 * @return  {@link GameOver#WIN} when all levels are completed,
	 * {@link GameOver#LOSE} when all lives are lost, {@link GameOver#NEXT} 
	 * when all frogs reach the swamp 
	 */
	public GameOver getState() {
		return state;
	}
	
	/**
	 * This method handles the actions to be taken when 
	 * <u1> 
	 * <li> all five Frogs have reached the swamp, or</li>
	 * <li> the Frog object dies and loses all its lives</li>
	 * </u1>
	 * <p>
	 * 
	 * @param state {@link GameOver#LOSE} when all lives are lost, {@link GameOver#NEXT} 
	 * when all frogs reach the swamp 
	 */
	public void handleDoneLevel(GameOver state) {
		frog.setNoMove(true);
		updateScoreList();
		pauseAllTimer();
		this.state = (state==GameOver.LOSE) ? GameOver.LOSE : 
			(levelNum<MAX_LEVEL) ? GameOver.NEXT : GameOver.WIN;
	}
	
	/**
	 * This method starts the {@link #bonusTimer} and 
	 * sets {@link #hasBonus} to true
	 */
	public void playBonus() {
		hasBonus=true;
		bonusTimer.start();
	}
	
	
	/**
	 * This method stops all unfinished timers
	 */
	public void pauseAllTimer() {
		if(hasBonus)
			bonusTimer.stop();
	}
	
	
	/**
	 * This method starts all unfinished timers
	 */
	public void continueAllTimer() {
		if(hasBonus) 
			bonusTimer.start();
		
	}


	
	/**
	 * This method returns the progress message according to 
	 * the state of game 
	 * @return  String to notify users of the next action
	 */
	public String getProgressMessage() {
		String msg = (state==GameOver.LOSE)? "Game Over" : 
			(levelNum<MAX_LEVEL)? "Next level!" : 
			"Congratulations, you won the game!";
		return msg;
		
	}
	
	/**
	 * This method returns the list of scores arranged indescending 
	 * order
	 * 
	 * @return  {@link List} of scores as {@code String} objects
	 */
	public List<String> getScores(){
		return scores;
	}
	
	/**
	 * This method returns the list of levels corresponding to the
	 * order of scores
	 * @return  {@link List} of levels as {@code String} objects
	 */
	public List<String> getLevel(){
		return levels;
	}
	

	/**
	 * This method clears the score list {@link #levelScoreList}
	 */
	public void resetScoreList() {
		levelScoreList.clear();
	}

    

    /**
	 * This method renders score digit from right to left. 
	 * Each loop renders one digit, points decrease by 10 
	 * with each while loop
	 * @param score  user's current score
	 */
	public void setScore(int score) { 
		
		//clear previous score from screen
		if(scoreDigit.size()!=0) {
			GameController.INSTANCE.removeFromView(scoreDigit);
			scoreDigit.clear();
		}

		//add current score to array
		if (score==0) {
			scoreDigit.add(new Digit(0, SCORE_X, SCORE_Y));
			  
		} else {
			
			int xShift = 0; 
			int number = score; //TODO added because dont want to modify params bad programming practice
			while (number > 0) {
				int ones = number % 10;  //ones = points - quotient * 10; TODO changed
				scoreDigit.add(new Digit(ones, SCORE_X - xShift, SCORE_Y));
				number/=10;
				xShift+=30; //shift digit to the left with each while loop
			}
		}
		//add score array to view
		GameController.INSTANCE.addToView(scoreDigit);

	}
	
	/**
	 * This method initializes {@link #generator}, {@link #frog} and 
	 * {@link #elementList} fields for this instance
	 */
	private void initElements() {
		this.generator = new GameGenerator(levelNum); //strict mvc pattern
		this.frog = generator.getFrog();
		this.elementList = generator.getList();
	}
	
	/**
	 * This method adds the current results as an entry to {@link #levelScoreList} 
	 * with the level number as key and score as value. Then, the entries in 
	 * {@link #levelScoreList} are sorted by descending order of scores (values).
	 * 
	 * The sorted key-value pairs are added to the lists {@link #levels} and 
	 * {@link #scores} respectively as {@code String} objects
	 * 
	 */
	private void updateScoreList() {
    	levelScoreList.add(new AbstractMap.SimpleEntry<Integer, Integer>(levelNum, frog.getScore()));
    	levelScoreList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
    	
    	 for (Map.Entry<Integer, Integer> entry : levelScoreList) {
         	levels.add(entry.getKey().toString());
         	scores.add(entry.getValue().toString());
         }
    	 
	}
	
	/**
	 * This method creates an {@code AnimationTimer} 
	 * object responsible for the bonus animation that
	 * pops up when the {@link Frog} object catches a
	 * fly
	 */
	private void createBonusTimer() {
	       
		bonusTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				showBonus(now);
			}
		};	

    }
	
	
	/**
	 * This method contains the logic for bonus animation
	 * (MVC pattern)
	 * @param now  the timestamp of the current frame given 
	 * in nanoseconds. 
	 */
	private void showBonus(long now) {
		
		if(now%11==0) {
			animCtr+=1;
		}
		
		if (animCtr==1) {
			GameController.INSTANCE.setBonusVisible(true);
		
		}
		
		if(animCtr==5) {
			animCtr=0;
			GameController.INSTANCE.setBonusVisible(false);
			hasBonus=false;
			bonusTimer.stop();
			
		}
	}
	
	

	
}


//phase out
//private String[] getAlertMessage() {
//	String title = (state==GameOver.LOSE)? "Game Over" : 
//					(levelNum<MAX_LEVEL)? "Next level!" : 
//					"Congratulations, you won the game!";
//
//	String header = "Your current score: " + frog.getScore();
//	scoreString = "";
//	//print scores of each level in descending order
//    for (Map.Entry<Integer, Integer> entry : levelScoreList) {
//    	scoreString = scoreString + "Level " + entry.getKey() + "\t: " + entry.getValue() + "\n";
//    	System.out.println("level is " + entry.getKey() + ": " + entry.getValue());
//    }
//    
//    String[] array = {title, header, scoreString};
//    return array;
//	
//}

