package org.psk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DictionaryView {

    private ListView<String> wordList;
    //TODO BUTTON I TEXT FIELDY TU WRZUCIC
    //try to add buttons with their getters and setters eventOnAction so that we can manip
    //so that we can manipulate the content of our view :)
    public Scene createSearchUI(DictionaryController controller) {
        TextField searchField = new TextField();
        searchField.setPromptText("Enter a word to search");

        Button addButton = new Button("Add Word");

        HBox searchBox = new HBox(10, searchField, addButton);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        wordList = new ListView<>();
        wordList.setPrefWidth(200);

        VBox searchPane = new VBox(10, searchBox, wordList);
        searchPane.setPadding(new Insets(10));


        return new Scene(searchPane);
    }


    public Scene createAddWordUI(DictionaryController controller) {

        // Create the add word UI
        TextField addField = new TextField();
        addField.setPromptText("Enter a word to add");

        Button saveButton = new Button("Save Word");

        Button cancelButton = new Button("Cancel");

        VBox addPane = new VBox(50, addField, saveButton, cancelButton);
        addPane.setPadding(new Insets(10));

        addPane.setStyle("-fx-background-color: #F8F8F8;");

        return new Scene(addPane);
    }


    public void displaySearchResults(ObservableList<String> results) {
        wordList.setItems(results);
    }

}
