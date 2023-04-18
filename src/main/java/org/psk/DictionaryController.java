package org.psk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

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
        Scene addScene = view.createAddWordUI(this);
        Scene searchScene = view.createSearchUI(this);

        view.attachSearchHandlers(e -> {
            String searchTerm = view.getSearchText();
            System.out.println(searchTerm);
            searchWords(searchTerm);
        });


        model.readDictionary();
        System.out.println(model.words);

        stage.setOnCloseRequest(event -> {
            // your code here to handle the close event
            //System.out.println("Closing the application...");
            model.writeDictionary();

        });

// set handlers
        view.setAddHandler(e -> stage.setScene(addScene));
        view.setSaveHandler(e -> saveWord());
        view.setCancelHandler(e -> showSearchUI());
        view.setSearchHandler(e -> searchWords(view.getSearchText()));

        stage.setScene(searchScene);
        stage.show();
    }

    public void searchWords(String searchTerm) {
        ArrayList<String> list = new ArrayList<String>(model.searchWords(searchTerm));
        System.out.println(list);
        ObservableList<String> results = FXCollections.observableArrayList(model.searchWords(searchTerm));
        System.out.println(results);
       view.displaySearchResults(results);
    }

    public void saveWord() {
        String word = view.getAddField().getText();
        model.addWord(word);
        showSearchUI();
    }

    public void showSearchUI() {
        stage.setScene(view.createSearchUI(this));
    }

    public void showAddWordUI() {
        stage.setScene(view.createAddWordUI(this));
    }

    public void handleCancelAction() {
        showSearchUI();
    }

}