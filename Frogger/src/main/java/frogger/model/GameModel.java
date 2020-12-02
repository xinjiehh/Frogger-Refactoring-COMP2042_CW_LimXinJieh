package frogger.model;

import javafx.animation.AnimationTimer;
import frogger.controller.GameController;
import frogger.controller.GameController.GameOver;
import frogger.model.NPC.Digit;
import frogger.util.GameGenerator;
import javafx.scene.Node;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



/**
 * This class allows 
 * @author Lim Xin Jieh (20082200)
 *
 */

public class GameModel {
	private static final int SCORE_X = 550; //TODO changed
	private static final int SCORE_Y = 10; //TODO changed
	private static final int MAX_LEVEL = 10;
	private static List<Map.Entry<Integer, Integer>> levelScoreList = new ArrayList<>();
	
	private int levelNum; 
	private Frog frog; 
	
	private LinkedList<Node> scoreDigit = new LinkedList<Node>(); 
	
	private int animCtr = 0;
	private List<Node> listTest;
	private GameOver state;
	private GameGenerator loader;
	private List<String> levels = new ArrayList<String>();
	private List<String> scores = new ArrayList<String>();
	private boolean hasBonus = false;
	private AnimationTimer bonusTimer;
	
	
	public GameModel(int level) { 
		
		this.levelNum = level;		
		this.loader = new GameGenerator(levelNum); //strict mvc pattern
		this.frog = loader.getFrog();
		this.listTest = loader.getList();
		createBonusTimer();
		setScore(frog.getScore());
		GameController.INSTANCE.addToView(listTest);
		
	}
	
	public List<Node> getList(){
		return listTest;
	}
	
	public Frog getFrog() {
		return this.frog;
	}
	
	public GameOver getState() {
		return state;
	}
	
	public void handleDoneLevel() {
		updateScoreList();
		pauseAllTimer();
		state = (levelNum<MAX_LEVEL) ? GameOver.NEXT : GameOver.WIN;

	}
	
	public void handleGameOver() {
		frog.setNoMove(true);
		updateScoreList();
		pauseAllTimer();
		state = GameOver.LOSE;
	}
	
	
	
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
	
	
	private void createBonusTimer() {
	       
		bonusTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				showBonus(now);
			}
		};	

    }
	
	
	public String getProgressMessage() {
		String msg = (state==GameOver.LOSE)? "Game Over" : 
			(levelNum<MAX_LEVEL)? "Next level!" : 
			"Congratulations, you won the game!";
		return msg;
		
	}

	public List<String> getScores(){
		return scores;
	}
	
	public List<String> getLevel(){
		return levels;
	}
	
	
	private void updateScoreList() {
    	levelScoreList.add(new AbstractMap.SimpleEntry<Integer, Integer>(levelNum, frog.getScore()));
    	levelScoreList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
    	
    	 for (Map.Entry<Integer, Integer> entry : levelScoreList) {
         	levels.add(entry.getKey().toString());
         	scores.add(entry.getValue().toString());
         }
	}
	
	public void resetScoreList() {
		levelScoreList.clear();
	}

    

    /**
	 * renders score digit from right to left 
	 * each loop renders one digit, points decrease by 10 with each while loop
	 * @param score  user's current score
	 */
	public void setScore(int score) { //TODO changed param name
		
		//clear previous score from screen
		if(scoreDigit.size()!=0) {
			GameController.INSTANCE.removeFromView(scoreDigit);
			scoreDigit.clear();
		}

		//add current score to array
		if (score==0) {
			scoreDigit.add(new Digit(0, SCORE_X, SCORE_Y));
			  
		} else {
			
			int xShift = 0; //TODO changed name
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

