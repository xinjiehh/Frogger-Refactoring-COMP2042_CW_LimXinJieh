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
	
	@FXML private ListView<String> levelList;
	@FXML private ListView<String> scoreList;
	private List<String> levels = new ArrayList<>();
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
	 * @see ListView#setCellFactory(Callback)
	 * @see <a href="https://stackoverflow.com/questions/25246496/
	 * javafx-custom-list-cell-updateitem-being-called-a-lot">Stack Overflow
	 * question</a>
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


//listView.setCellFactory(new Callback<ListView<String>,ListCell<String>>(){
//
//	@Override
//	public ListCell<String> call(ListView<String> param) {
//		ListCell<String> cell = new ListCell<String>() {
//            @Override
//            public void updateItem(String item, boolean empty) {
//                
//                super.updateItem(item, empty);
//                if (empty || item == null) setText(null);
//                else setText(item);
//            }
//        };
//
//        return cell;
//	}
//	
//});
//
//listView.setCellFactory( (param) -> {
//	
//	ListCell<String> cell = new ListCell<String>() {
//		@Override
//		public void updateItem(String item, boolean empty) {
//			super.updateItem(item, empty);
//            if (empty || item == null) setText(null);
//            else setText(item);
//        }
//    };
//
//    return cell;
//	
//	
//});
