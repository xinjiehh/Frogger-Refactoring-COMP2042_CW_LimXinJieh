package frogger.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class creates a new high score file to output high scores
 * upon completion of all the levels
 *
 */
public class HighScoreFile {
	

	//https://www.w3schools.com/java/java_files_create.asp
	//https://stackoverflow.com/questions/13729625/overwriting-txt-file-in-java
	public HighScoreFile(String message) {
		
		File myObj;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
	    try {
	    	
	      myObj = new File("My High Score.txt");
	      
	      if (myObj.createNewFile()) {
	        
	    	  System.out.println("File " + myObj.getName() + " created" );
	      
	      } else {
	        
	    	  System.out.println("File already exists.");

	    	  try {
	    	      FileWriter f2 = new FileWriter(myObj, true);
	    	      f2.write("Your high score as of " + dtf.format(now) 
	    	      		+ "\n" + message + "\n\n");
	    	      f2.close();
	    	      
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
