package frogger.util;

import frogger.constant.FilePath;
import frogger.constant.settings.Settings;
import frogger.controller.GameController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This class creates a new high score file in two different ways,
 * either upon completion of all the levels with the entries sorted
 * in descending order of scores, or after each level with the
 * entries sorted in ascending order of level number
 *
 * @see HighScoreReader
 * @see GameController#showScoreDisplay()
 *
 */
public class HighScoreFile {
	File myObj = new File(FilePath.HIGH_SCORE_FILE);

	//https://www.w3schools.com/java/java_files_create.asp
	//https://stackoverflow.com/questions/13729625/overwriting-txt-file-in-java

	/**
	 * V1: This method is used to generate high score file upon completion of all levels.
	 * The level-score entries will be sorted in descending value of scores.
	 * @param scores  {@code String} containing the scores arranged in a particular
	 * order
	 * @param levels  level number corresponding to the score
	 */
	public HighScoreFile(List<String> scores, List<String> levels) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
	    try {
	      if (myObj.createNewFile()) {
	        
	    	  System.out.println("File " + myObj.getName() + " created" );
	      
	      } else {
	        
	    	  System.out.println("File already exists.");

	    	  try {
	    	      FileWriter f2 = new FileWriter(myObj, true);

	    	      f2.write("Your high score as of " + dtf.format(now)
	    	      		+ "\n");

	    	      for(int i=0;i<scores.size();i++){
	    	      	f2.write("Level " + levels.get(i) + "\t: " + scores.get(i) + "\n");
				  }

	    	      f2.write("\n\n\n");
	    	      f2.close();
				  System.out.println("My High Score.txt updated");

	    	      
	    	  } catch (IOException e) {
	    		  System.out.println("An error occurred in writing to file.");
	    	      e.printStackTrace();
	    	  }
	      }
	    
	    } catch (IOException e) {
	      System.out.println("An error occurred in creating/writing to file.");
	      e.printStackTrace();
	    }
		
	}

	/**
	 * V2: This method is used to generate high score file at the start of the game,
	 * then at the end of each level call {@code addToFile} to add the individual
	 * level number and score to file.
	 */
	public HighScoreFile() {

		File myObj;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		try {

			myObj = new File(FilePath.HIGH_SCORE_FILE);

			if (myObj.createNewFile()) {

				System.out.println("File " + myObj.getName() + " created" );

			} else {

				System.out.println("File already exists.");

				try {
					FileWriter f2 = new FileWriter(myObj, true);

					f2.write("Your high score as of " + dtf.format(now)
							+ "\n");
					f2.close();
					System.out.println("My High Score.txt updated");


				} catch (IOException e) {
					System.out.println("An error occurred in writing to file.");
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			System.out.println("An error occurred in creating/writing to file.");
			e.printStackTrace();
		}

	}

	/**
	 * This method allows user to add level number and score to file individually
	 * @param level  level number
	 * @param score  score of the corresponding level
	 */
	public void addToFile(int level, int score){

		try {
			FileWriter f2 = new FileWriter(myObj, true);
			f2.write("Level " + level + "\t: " + score + "\n");

			if(level==Settings.MAX_LEVEL)
				f2.write("\n\n\n");

			f2.close();

		} catch (IOException e) {
			e.printStackTrace();
		}



	}
}
