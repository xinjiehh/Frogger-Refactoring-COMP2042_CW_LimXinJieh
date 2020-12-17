package frogger.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is the designated controller for the scoreview fxml.
 * This class contains methods to initialize the data shown on the 
 * score screen.
 *
 */
public class ScoreScreenController {
	
	/** variable holding the level list data to be displayed on screen */
	@FXML private ListView<String> levelList;
	
	/** variable storing the score list data to be displayed on screen */
	@FXML private ListView<String> scoreList;
	
	/** variable holding the list of levels arranged in descending order of corresponding scores */
	private List<String> levels = new ArrayList<>();
	
	/** variable holding the list of levels arranged in decending order */
	private List<String> scores = new ArrayList<>();
	
	/**
	 * This is the entrance method used to initialize the data displayed
	 * on the score view
	 * @param levelData  {@code List<String>} which contain levels arranged according to scores
	 * @param scoreData  {@code List<String>} which contain scores arranged in user specified order
	 */
	public void initView(List<String> levelData, List<String> scoreData) {
		initData(levelData, scoreData);
		initListView(levels, levelList);
		initListView(scores, scoreList);
	}
	
	/**
	 * This method initializes the field {@link #levels} and {@link #scores}
	 * with data obtained from the caller method
	 * @param levelData  {@code List<String>} which contain levels arranged according to scores
	 * @param scoreData  {@code List<String>} which contain scores arranged in user specified order
	 * by developer
	 */
	private void initData(List<String> levelData, List<String> scoreData) {
		levels.add("LEVEL");
		scores.add("SCORE");
	    levels.addAll(levelData);
	    scores.addAll(scoreData);
	}
	
	
	/**
	 * This method initializes the {@code ListView} objects with data that
	 * is in the form of {@code String} objects
	 * @param stringList  {@code List<String>} which contain the data to be inserted
	 * @param listView  the {@link ListView} to be initialized
	 */
	private void initListView(List<String> stringList, ListView<String> listView) {
	    ObservableList<String> observableList = FXCollections.observableArrayList();
	    observableList.addAll(stringList);
	    listView.setItems(observableList);
	    listView.setCellFactory(this::newCellFactory);
	    
	  }

	/**
	 * This method simulates the argument type of the calling method,
	 * which is a {@code Callback} that takes in a {@code ListView} of
	 * {@code String} objects and returns a {@code ListCell} of the same
	 * {@code String} objects
	 * 
	 * @param param  this {@code ListView<String>} object
	 * @return cell factory to use in this ListView
	 */
	private ListCell<String> newCellFactory(ListView<String>param){

		return new ListCell<String>() {
			@Override
	  		public void updateItem(String item, boolean empty) {

	  			super.updateItem(item, empty);

	  			if (item==null || empty)
	  				setText(null);
	  			else
	  				setText(item);
		    }
		  };
	  }

}