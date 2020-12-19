package frogger.constant;

import frogger.model.PlayerAvatar;
import frogger.model.npc.NPC;

/**
 * This class contains all the paths for the media files used in
 * this project for easy modification. 
 */

public class FilePath {
	
	/** default high score file */
	public static final String HIGH_SCORE_FILE = "My High Score.txt";
	/** default resource path */
	public static final String RESOURCE_PATH = "src/main/resources/";
	/** file path for bonus image */
	public static final String BONUS = "/misc/100.png";
	
	//BACKGROUND
	/** default background package path */
	public static final String BG_PATH = "/background/";
	/** default game background image */
	public static final String GAMEBACKGROUND = BG_PATH + "GameBackground2.png";
	/** default menu background image */
	public static final String MENUBACKGROUND = BG_PATH + "MenuBackground.png";


	//NPC
	/** default package path for {@link NPC} images */
	public static final String NPC_PATH = "/npc/";
	
	//NPC: SWAMP
	/** default package path for swamp images */
	public static final String SWAMP_PATH = NPC_PATH + "swamp/";

	/** image of swamp with fly */
	public static final String S_FLY = FilePath.SWAMP_PATH + "FlyEnd.png";

	/** image of empty swamp */
	public static final String SWAMP = FilePath.SWAMP_PATH + "End.png";

	/** image of crocodile fully occupying swamp */
	public static final String S_CROC1 = FilePath.SWAMP_PATH + "CrocEnd1.png";

	/** image of crocodile half occupying swamp */
	public static final String S_CROC2 = FilePath.SWAMP_PATH + "CrocEnd2.png";

	/** image of fly in swamp */
	public static final String S_FROG = FilePath.SWAMP_PATH + "FrogEnd.png";
	
	//NPC: LOG
	/** image of long log */
	public static final String L_LOG = NPC_PATH +  "logLong.png";

	/** image of medium log */
	public static final String M_LOG = NPC_PATH +  "logMedium.png";

	/** image of short log */
	public static final String S_LOG = NPC_PATH +  "logShort.png";
	
	//NPC: Obstacle
	/** image of short truck */
	public static final String TRUCK_SHORT = NPC_PATH + "truckShort.png";

	/** image of long truck */
	public static final String TRUCK_LONG = NPC_PATH + "truckLong.png";

	/** image of car */
	public static final String CAR = NPC_PATH + "car1Left.png";
	
	//NPC: TURTLE
	/** turtle image */
	public static final String TURTLE1 = NPC_PATH + "TurtleAnimation1.png";

	/** wet turtle image */
	public static final String TURTLE2_WET = NPC_PATH + "TurtleAnimation2Wet.png";

	/** wet turtle image */
	public static final String TURTLE3_WET = NPC_PATH + "TurtleAnimation3Wet.png";

	/** wet turtle image */
	public static final String TURTLE4_WET = NPC_PATH + "TurtleAnimation4Wet.png";

	/** turtle image */
	public static final String TURTLE2_DRY = NPC_PATH + "TurtleAnimation2.png";

	/** turtle image */
	public static final String TURTLE3_DRY = NPC_PATH + "TurtleAnimation3.png";



	//FROG
	/** default package path for frog images */
	public static final String FROG_PATH = "/frog/";

	/** frog stationary image */
	public static final String FROG_UP = FROG_PATH + "froggerUp.png";

	/** frog jumping image */
	public static final String FROG_UPJUMP = FROG_PATH + "froggerUpJump.png";
	


	//FONT
	/** default font package path */
	public static final String FONT_PATH = RESOURCE_PATH + "font/";
	/** path of default font file */
	public static final String DEFAULT_FONT = FONT_PATH + "JoystixMonospace.ttf";



	//MISC
	/** default package path for button images */
	public static final String BUTTON_PATH = "button/";
	
	/** default package path for digit images **/
	public static final String DIGIT_PATH = "/digit/";



	//FXML
	/** default package path for fxml files **/
	public static final String FXML_PATH = "/view/";

	/** path for selection screen fxml */
	public static final String SELECTION_FXML = FXML_PATH + "selection.fxml";

	/** path for score screen fxml */
	public static final String SCORE_FXML = FXML_PATH + "scoreview.fxml";

	/** path for progress screen fxml */
	public static final String PROGRESS_FXML = FXML_PATH + "progress.fxml";

	/** path for info screen fxml */
	public static final String INFO_FXML = "/view/info.fxml";



	//AUDIO
	/** path for selection screen fxml */
	public static final String AUDIO_PREFIX = "src/main/resources/audio/";

	/** path for theme song audio */
	public static final String THEME_AUDIO = AUDIO_PREFIX + "theme-song.mp3";

	/** path for {@link PlayerAvatar} movement audio */
	public static final String HOP_AUDIO = AUDIO_PREFIX + "hop.wav";

	/** path for drowning audio */
	public static final String WATERDEATH_AUDIO = AUDIO_PREFIX + "plunk.wav";

	/** path for vehicle death */
	public static final String CARDEATH_AUDIO = AUDIO_PREFIX + "squash.wav";
	
	
			
}
