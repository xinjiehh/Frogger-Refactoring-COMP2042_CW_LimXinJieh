package frogger.constant;

/**
 * This class contains all the paths for the media files used in
 * this project for easy modification. 
 */

public class FilePath {

	public static final String HIGH_SCORE_FILE = "My High Score.txt";
	public static final String RESOURCE_PATH = "src/main/resources/";
	public static final String BONUS = "/misc/100.png";
	
	//BACKGROUND
	public static final String BG_PATH = "/background/";
	public static final String GAMEBACKGROUND = BG_PATH + "GameBackground2.png";
	public static final String MENUBACKGROUND = BG_PATH + "MenuBackground.png";


	//NPC
	public static final String NPC_PATH = "/npc/";
	
	//NPC: SWAMP
	public static final String SWAMP_PATH = "/npc/swamp/";
	public static final String S_FLY = FilePath.SWAMP_PATH + "FlyEnd.png";
	public static final String SWAMP = FilePath.SWAMP_PATH + "End.png";
	public static final String S_CROC1 = FilePath.SWAMP_PATH + "CrocEnd1.png";
	public static final String S_CROC2 = FilePath.SWAMP_PATH + "CrocEnd2.png";
	public static final String S_FROG = FilePath.SWAMP_PATH + "FrogEnd.png";
	
	//NPC: LOG
	public static final String L_LOG = NPC_PATH +  "logLong.png";
	public static final String M_LOG = NPC_PATH +  "logMedium.png";
	public static final String S_LOG = NPC_PATH +  "logShort.png";
	
	//NPC: Obstacle
	public static final String TRUCK1 = NPC_PATH + "truck1Right.png";
	public static final String TRUCK2 = NPC_PATH + "truck2Right.png";
	public static final String CAR = NPC_PATH + "car1Left.png";
	
	//NPC: TURTLE
	public static final String TURTLE1 = NPC_PATH + "TurtleAnimation1.png";
	public static final String TURTLE2_WET = NPC_PATH + "TurtleAnimation2Wet.png";
	public static final String TURTLE3_WET = NPC_PATH + "TurtleAnimation3Wet.png";
	public static final String TURTLE4_WET = NPC_PATH + "TurtleAnimation4Wet.png";
	public static final String TURTLE2_DRY = NPC_PATH + "TurtleAnimation2.png";
	public static final String TURTLE3_DRY = NPC_PATH + "TurtleAnimation3.png";

	
	//FROG
	public static final String FROG_PATH = "/frog/";
	public static final String FROG_UP = FROG_PATH + "froggerUp.png";
	public static final String FROG_UPJUMP = FROG_PATH + "froggerUpJump.png";
	
		
	//FONT
	public static final String FONT_PATH = RESOURCE_PATH + "font/";
	public static final String DEFAULT_FONT = FONT_PATH + "JoystixMonospace.ttf";
	public static final String BUTTON_PATH = "button/";
	
	
	public static final String DIGIT_PATH = "/digit/";
	
	//FXML
	public static final String FXML_PATH = "/view/";
	public static final String SELECTION_FXML = FXML_PATH + "selection.fxml";
	public static final String SCORE_FXML = FXML_PATH + "scoreview.fxml";
	public static final String PROGRESS_FXML = FXML_PATH + "progress.fxml";
	public static final String INFO_FXML = "/view/info.fxml";
	
	//AUDIO
	public static final String AUDIO_PREFIX = "src/main/resources/audio/";
	public static final String THEME_AUDIO = AUDIO_PREFIX + "theme-song.mp3";   
	public static final String HOP_AUDIO = AUDIO_PREFIX + "hop.wav";
	public static final String WATERDEATH_AUDIO = AUDIO_PREFIX + "plunk.wav";
	public static final String CARDEATH_AUDIO = AUDIO_PREFIX + "squash.wav";
	
	
			
}
