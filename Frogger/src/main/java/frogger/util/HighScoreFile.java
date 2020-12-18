package frogger.util;

import frogger.constant.FilePath;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * This class creates a new high score file to output high scores
 * upon completion of all the levels
 *
 */
public class HighScoreFile {
	

	//https://www.w3schools.com/java/java_files_create.asp
	//https://stackoverflow.com/questions/13729625/overwriting-txt-file-in-java

	/**
	 *
	 * @param scores  {@code String} containing the scores arranged in a particular
	 * order
	 * @param levels  level number corresponding to the score
	 */
	public HighScoreFile(List<String> scores, List<String> levels) {
		
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
 
}
