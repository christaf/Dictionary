package org.psk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DictionaryController {

    private final DictionaryModel model;
    private final DictionaryView view;
    private final Stage stage;

    public DictionaryController(Stage stage) {
        model = new DictionaryModel();
        view = new DictionaryView();

        this.stage = stage;

        // Set up event handlers
        Scene searchScene = view.createSearchUI(this);
        Scene addScene = view.createAddWordUI(this);

        stage.setScene(searchScene);
        stage.setScene(addScene);
        stage.show();

    }

    public void searchWords(String searchTerm) {
        ObservableList<String> results = FXCollections.observableArrayList(model.searchWords(searchTerm));
        view.displaySearchResults(results);
    }

    public void saveWord(String word) {
        model.addWord(word);
        showSearchUI();
    }

    public void showSearchUI() {
        view.createSearchUI(this);
    }

    public void showAddWordUI() {
        view.createAddWordUI(this);
    }

    public void handleCancelAction() {
        showSearchUI();
    }

}
