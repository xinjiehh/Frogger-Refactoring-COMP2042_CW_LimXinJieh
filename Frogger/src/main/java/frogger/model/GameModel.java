package frogger.model;

import frogger.constant.DEATH;
import frogger.constant.EndGame;
import frogger.constant.settings.Settings;
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
 * to pause/play {@code Transition} accordingly. To sort the levels and scores,
 * first I use a {@code Map} to store the level and score as key-value pair.
 * As {@code Map} objects do not have a sort by value function, each level-score
 * pair is stored in its own map, and those maps are added to this {@link
 * #levelScoreList} so it can be sorted in descending order of values.
 *
 */

public class GameModel implements Subject {
	
	private static final int SCORE_X = 550; 
	private static final int SCORE_Y = 10;
	
	/** list of played levels and their corresponding scores stored in {@code Map} as key-value pair */
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
	
	/** flag for current game state, true if paused, false otherwise */
	private boolean isPaused = false;
	
	/**
	 * This is the public constructor for this {@code GameModel} object
	 */
	public GameModel() { 
		addEvent("add sprite","pause","level");
		//replaced by Observer pattern
		//GameController.INSTANCE.addToView(elementList); 

	}

	/**
	 * This method creates a new level by initializing the state of the game
	 * elements such as {@link Swamp} and {@link PlayerAvatar} objects
	 */
	public void newLevel() {
		this.levelNum+=1;
		notify("level",this);
		Swamp.resetCtr();
		initElements();
		initPlayer();
		System.out.println("This is level "+ levelNum);
	}
	public int getScore(){
		return player.getScore();
	}
	/**
	 * This method initializes this {@link PlayerAvatar} object and its
	 * properties.
	 */
	private void initPlayer() {
		player.setNoMove(false);
		player.addScoreListener((obs,oldV,newV)-> setScore(newV.intValue()));
	    //player.addLifeListener(this::updateLifeView);
		
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
	 * This method handles the actions to be taken when all five {@link
	 * PlayerAvatar} have reached the swamp, or the {@code PlayerAvatar}
	 * object dies and loses all its lives
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

	/**
	 * This method stops all timers/animations
	 */
	public void pauseAllTimer() {
		isPaused = true;
		notify("pause", this);
		player.setNoMove(true);
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
		if(player.getDeath()==DEATH.NULL)
			player.setNoMove(false);
		
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
	 * This method returns the list of scores arranged in descending
	 * order
	 * 
	 * @return  {@link List} of scores as {@code String} objects
	 */
	public List<String> getScoreList(){
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
		notify("add sprite", this);
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

		levels.clear();
		scores.clear();
    	levelScoreList.add(new AbstractMap.SimpleEntry<Integer, Integer>(levelNum, player.getScore()));
    	levelScoreList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
    	
    	 for (Map.Entry<Integer, Integer> entry : levelScoreList) {
    		levels.add(entry.getKey().toString());
         	scores.add(entry.getValue().toString());
         }
    	 
	}
	


	
}