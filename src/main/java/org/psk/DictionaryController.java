package org.psk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class DictionaryController {

    private final DictionaryModel model;
    private final DictionaryView view;

    public DictionaryController(Stage stage) {
        model = new DictionaryModel();
        view = new DictionaryView();

        // Set up event handlers
        view.createSearchUI(this);
        view.createAddWordUI(this);

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
