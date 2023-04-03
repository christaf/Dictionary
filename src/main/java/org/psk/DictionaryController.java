package org.psk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DictionaryController {

    private static DictionaryController instance;

    private static final String fileName = "dictionary.txt";
    private final DictionaryModel model;
    private final DictionaryView view;
    private final Dictionary dictionary;
    private final Stage stage;

    public static DictionaryController getInstance(Stage stage, Dictionary dictionary) {
        if (instance == null) {
            instance = new DictionaryController(stage, dictionary);
        }
        return instance;
    }

    private DictionaryController(Stage stage, Dictionary dictionary) {

        model = new DictionaryModel(fileName);
        view = new DictionaryView();

        this.stage = stage;
        this.dictionary = dictionary;
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
