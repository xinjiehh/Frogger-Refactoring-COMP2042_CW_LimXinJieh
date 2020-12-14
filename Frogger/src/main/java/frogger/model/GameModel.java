package frogger.model;

import frogger.constant.EndGame;
import frogger.constant.Settings;
import frogger.controller.GameController;
import frogger.model.npc.Digit;
import frogger.model.npc.Swamp;
import frogger.util.GameGenerator;
import frogger.util.animation.SpriteAnimationTemplate;
import javafx.scene.Node;

import java.util.*;


/**
 * This class implements the MVC pattern. It is the Model for GameScreen 
 * containing all the data (game state etc) and logic (handling game over
 * etc). This class also follows the Observer pattern by implementing the
 * Subject interface. Any updates to the {@link #isPaused} will notify the
 * subscribers (in this case, classes implementing {@link SpriteAnimationTemplate})
 * to pause/play {@code Transition} accordingly
 *
 */

public class GameModel implements Subject {
	
	private static final int SCORE_X = 550; 
	private static final int SCORE_Y = 10;
	
	/** list of played levels and their corresponding scores stored in Map as key-value pair */
	private static List<Map.Entry<Integer, Integer>> levelScoreList = new ArrayList<>();
	
	/** list of level numbers in order */
	private List<String> levels = new ArrayList<String>();
	
	/** list of scores in order */
	private List<String> scores = new ArrayList<String>();
	
	/** current level */
	private int levelNum = 0; 
	
	/** current player character */
	private PlayerAvatar player; 
	
	/** array of score digits to add to view */
	private LinkedList<Node> scoreDigit = new LinkedList<Node>(); 
	
	/** list of all the characters / game elements to be displayed on view */
	private List<Node> elementList;
	
	/** the state of the game */
	private EndGame state;
	
	/** used to generate the characters / game elements */
	private GameGenerator generator;
	
	private String scoreString = "";
	
	private boolean isPaused = false;
	
	/**
	 * This is the public constructor for this object
	 * @param level  the current level
	 */
	public GameModel() { 
		addEvent("sprite","pause","level");
		//replaced by Observer pattern
		//GameController.INSTANCE.addToView(elementList); 

	}
	
	public void newLevel() {
		this.levelNum+=1;
		notify("level",this);
		Swamp.resetCtr();
		initElements();
		System.out.println("This is level "+ levelNum);
	}

	
	/**
	 * This method returns the list of game elements
	 * @return {@link List} of game element objects ({@link Node}) 
	 */
	public List<Node> getList(){
		return elementList;
	}
	
	/**
	 * This method returns the {@link PlayerAvatar} object used in this 
	 * {@link GameModel} object
	 * @return  the {@link PlayerAvatar} object
	 */
	public PlayerAvatar getPlayer() {
		return this.player;
	}
	
	/**
	 * This method returns the {@link EndGame} state of the
	 * game
	 * 
	 * @return  {@link EndGame#WIN} when all levels are completed,
	 * {@link EndGame#LOSE} when all lives are lost, {@link EndGame#NEXT} 
	 * when all {@code PlayerAvatar} reach the swamp 
	 */
	public EndGame getState() {
		return state;
	}
	
	/**
	 * This method handles the actions to be taken when 
	 * <u1> 
	 * <li> all five {@code PlayerAvatar} have reached the swamp, or</li>
	 * <li> the {@code PlayerAvatar} object dies and loses all its lives</li>
	 * </u1>
	 * <p>
	 * 
	 * @param state {@link EndGame#LOSE} when all lives are lost, {@link EndGame#NEXT} 
	 * when all {@code PlayerAvatar} reach the swamp 
	 */
	public void handleDoneLevel(EndGame state) {
		player.setNoMove(true);
		updateScoreList();
		pauseAllTimer();
		
		if(state==EndGame.LOSE) {
			this.state=state;
			resetGame();
			
		} else {
			this.state = 
				(levelNum<Settings.MAX_LEVEL) ? EndGame.NEXT : 
					EndGame.WIN;
			//new HighScoreFile(scoreString);
		}
	}
	
	public String getScoreString() {
		return scoreString;
	}

	/**
	 * This method stops all timers/animations
	 */
	public void pauseAllTimer() {
		isPaused = true;
		notify("pause", this);
	}
	
	public int getLevel() {
		return levelNum;
	}

	
	/**
	 * This method starts all unfinished timers/animations
	 */
	public void continueAllTimer() {
		isPaused = false;
		notify("pause", this);
		
	}
	
	/**
	 * This method returns property {@code isPaused} which 
	 * determines if the game is playing/paused
	 * 
	 * @return  true if the game is paused
	 */
	public boolean getIsPaused() {
		return isPaused;
	}
	

	
	/**
	 * This method returns the progress message according to 
	 * the state of game 
	 * @return  String to notify users of the next action
	 */
	public String getProgressMessage() {
		return (state==EndGame.LOSE)? "Game Over" :
			(levelNum < Settings.MAX_LEVEL)? "Next level!" :
			"Congratulations, you won the game!";
		
	}
	
	/**
	 * This method returns the list of scores arranged in descending
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
	public List<String> getLevelList(){
		return levels;
	}
	

	/**
	 * This method resets the game data by clearing the score 
	 * list {@link #levelScoreList}, reset {@link Swamp} counters
	 * and set level number to 0
	 */
	public void resetGame() {
		Swamp.resetCtr();
		levelNum=0;
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
			int number = score; 
			while (number > 0) {
				int ones = number % 10; 
				scoreDigit.add(new Digit(ones, SCORE_X - xShift, SCORE_Y));
				number/=10;
				xShift+=30; //shift digit to the left with each while loop
			}
		}
		
		GameController.INSTANCE.addToView(scoreDigit);

	}
	
	/**
	 * This method initializes {@link #generator}, {@link #player} and 
	 * {@link #elementList} fields for this instance
	 */
	private void initElements() {
		this.generator = new GameGenerator(levelNum); //strict mvc pattern
		this.player = generator.getPlayer();
		this.elementList = generator.getList();
		setScore(player.getScore());
		notify("sprite", this);
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
		scoreString = "";
    	levelScoreList.add(new AbstractMap.SimpleEntry<Integer, Integer>(levelNum, player.getScore()));
    	levelScoreList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
    	
    	 for (Map.Entry<Integer, Integer> entry : levelScoreList) {
    		scoreString = scoreString + "Level " + entry.getKey() + "\t: " + entry.getValue() + "\n";
         	levels.add(entry.getKey().toString());
         	scores.add(entry.getValue().toString());
         }
    	 
	}
	


	
}

///**
// * This method creates an {@code AnimationTimer} 
// * object responsible for the bonus animation that
// * pops up when the {@link PlayerAvatar} object catches a
// * fly
// */
//private void createBonusTimer() {
//       
//	bonusTimer = new AnimationTimer() {
//		@Override
//		public void handle(long now) {
//			showBonus(now);
//		}
//	};	
//
//}


///**
// * This method contains the logic for bonus animation
// * (MVC pattern)
// * @param now  the timestamp of the current frame given 
// * in nanoseconds. 
// */
//private void showBonus(long now) {
//	
//	if(now%11==0) {
//		animCtr+=1;
//	}
//
//	if(animCtr==5) {
//		animCtr=0;
//		hasBonus=false;
//		notify("bonus",this);
//		bonusTimer.stop();
//		
//	}
//}



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

