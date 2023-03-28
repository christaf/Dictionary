package org.psk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DictionaryController {

    private static DictionaryController instance;
    private final DictionaryModel model;
    private final DictionaryView view;
    private Dictionary dictionary;
    private final Stage stage;

    public static DictionaryController getInstance(Stage stage) {
        if (instance == null) {
            instance = new DictionaryController(stage);
        }
        return instance;
    }

    private DictionaryController(Stage stage) {

        model = new DictionaryModel();
        view = new DictionaryView();

        this.stage = stage;
        // Set up event handlers
        Scene searchScene = view.createSearchUI(this);
        Scene addScene = view.createAddWordUI(this);

        model.readDictionary();
        System.out.println(model.words);

        stage.setOnCloseRequest(event -> {
            // your code here to handle the close event
            //System.out.println("Closing the application...");
            model.writeDictionary();
        });

        stage.setScene(searchScene);
        //stage.setScene(addScene);
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
