package frogger.util;

import frogger.constant.FilePath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * This class is used to read the designated high score file and extract
 * specific information. For example, I have implemented {@link #readTop10}
 * method to extract top 10 scores of a specific level from the file. Refer to
 * {@link ScreenLoader#loadTop10Score} method for example implementation.
 */

public enum HighScoreReader {

    /** singleton instance of this class */
    INSTANCE;

    /** the .txt file used to store high scores */
    private File file = new File(FilePath.HIGH_SCORE_FILE);

    /** the list of top 10 scores */
    private List<String> top10 = new ArrayList<String>();

    /**
     * This method reads the top 10 scores of a particular level
     * by filtering through the entries of the high score file
     * @param level  current level
     */
    public void readTop10(int level){
        try {
            Scanner scanner = new Scanner(file);
            List<String> scores = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //search for specific level
                if(line.contains("Level " + level)) {
                    scores.add(line.split(":",3)[1]);
                    System.out.println(line);
                }

                Collections.sort(scores);
                int size = scores.size()>10 ? scores.size()-10 : 0;
                top10 = scores.subList(size, scores.size());
                top10.sort((score1,score2)-> -score1.compareTo(score2));

            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * This method returns the list of top 10 scores for this
     * level
     * @return  list of top 10 scores for the given level
     */
    public List<String> getTop10(){
        return top10;
    }
}
