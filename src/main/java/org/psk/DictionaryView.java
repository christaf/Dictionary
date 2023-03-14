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

    private final TextField searchField;
    private final ListView<String> wordList;

    public DictionaryView() {
        // Create the search UI
        searchField = new TextField();
        searchField.setPromptText("Enter a word to search");

        Button addButton = new Button("Add Word");

        HBox searchBox = new HBox(10, searchField, addButton);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        wordList = new ListView<>();
        wordList.setPrefWidth(200);

        VBox searchPane = new VBox(10, searchBox, wordList);
        searchPane.setPadding(new Insets(10));

        // Create the add word UI
        TextField addField = new TextField();
        addField.setPromptText("Enter a word to add");

        Button saveButton = new Button("Save Word");

        Button cancelButton = new Button("Cancel");

        VBox addPane = new VBox(10, addField, saveButton, cancelButton);
        addPane.setPadding(new Insets(10));

    }

    public Scene createSearchUI(DictionaryController controller) {
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> controller.searchWords(searchField.getText()));

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        wordList.setItems(FXCollections.observableArrayList());
        VBox searchPane = new VBox(10, searchBox, wordList);
        searchPane.setPadding(new Insets(10));

        Scene scene = new Scene(searchPane);
        return scene;
    }


    public Scene createAddWordUI(DictionaryController controller) {
        TextField addField = new TextField();
        addField.setPromptText("Enter a word to add");

        Button saveButton = new Button("Save Word");
        saveButton.setOnAction(event -> controller.saveWord(addField.getText()));

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> controller.handleCancelAction());

        VBox addPane = new VBox(10, addField, saveButton, cancelButton);
        addPane.setPadding(new Insets(10));

        Scene scene = new Scene(addPane);
        return scene;
    }


    public void displaySearchResults(ObservableList<String> results) {
        wordList.setItems(results);
    }

}

