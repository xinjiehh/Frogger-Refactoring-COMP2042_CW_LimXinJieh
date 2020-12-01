package frogger.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import java.util.ArrayList;
import java.util.List;
import frogger.model.GameModel;

public class ScoreViewController {

  @FXML private ListView<String> levelList;
  @FXML private ListView<String> scoreList;

  private List<String> levels = new ArrayList<>();
  private List<String> scores = new ArrayList<>();

  
  public void init(GameModel gameModel) {
    initData(gameModel);
    initListView(levels, levelList);
    initListView(scores, scoreList);
  }

  private void initData(GameModel gameModel) {
    levels.add("LEVEL");
    scores.add("SCORE");
    levels.addAll(gameModel.getLevel());
    scores.addAll(gameModel.getScores());
  }

  private void initListView(List<String> stringList, ListView<String> listView) {
    ObservableList<String> observableList = FXCollections.observableArrayList();
    observableList.addAll(stringList);
    listView.setItems(observableList);
    listView.setCellFactory(param -> new listViewCell());
  }

  private class listViewCell extends ListCell<String> {
    @Override
    protected void updateItem(String string, boolean empty) {
      super.updateItem(string, empty);
      if (empty || string == null) setText(null);
      else setText(string);
    }
  }
}
