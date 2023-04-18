package org.psk;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DictionaryView {
    private EventHandler<ActionEvent> addHandler;
    private EventHandler<ActionEvent> saveHandler;
    private EventHandler<ActionEvent> cancelHandler;
    private EventHandler<ActionEvent> searchHandler;
    private ListView<String> wordList;

    private TextField searchField;
    private Button addButton;
    private TextField addField;
    private Button saveButton;
    private Button searchButton;
    private Button cancelButton;

    public Scene createSearchUI(DictionaryController controller) {
        searchField = new TextField();
        searchField.setPromptText("Enter a word to search");

        addButton = new Button("Add Word");
        addButton.setOnAction(e -> addHandler.handle(e)); // set the event handler
        searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchHandler.handle(e));
        HBox searchBox = new HBox(10, searchField,searchButton, addButton);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        wordList = new ListView<>();
        wordList.setPrefWidth(200);

        VBox searchPane = new VBox(10, searchBox, wordList);
        searchPane.setPadding(new Insets(10));

        return new Scene(searchPane);
    }


    public Scene createAddWordUI(DictionaryController controller) {
        addField = new TextField();
        addField.setPromptText("Enter a word to add");

        saveButton = new Button("Save Word");
        saveButton.setOnAction(e -> saveHandler.handle(e)); // set the event handler

        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> cancelHandler.handle(e)); // set the event handler

        VBox addPane = new VBox(50, addField, saveButton, cancelButton);
        addPane.setPadding(new Insets(10));

        addPane.setStyle("-fx-background-color: #F8F8F8;");

        return new Scene(addPane);
    }

    public void attachSearchFieldListener(ChangeListener<String> listener) {
        searchField.textProperty().addListener(listener);
    }
    public void displaySearchResults(ObservableList<String> results) {
        System.out.println(results);
        wordList.setItems(results);
    }

    public void attachSearchHandlers(EventHandler<ActionEvent> searchHandler) {
        //addButton.setOnAction(searchHandler);
        searchButton.setOnAction(searchHandler);
    }

    public void attachAddHandlers(EventHandler<ActionEvent> saveHandler, EventHandler<ActionEvent> cancelHandler) {
        saveButton.setOnAction(saveHandler);
        cancelButton.setOnAction(cancelHandler);
    }

    public TextField getAddField() {
        return this.addField;
    }
    public String getSearchText(){
        return this.addField.getText();
    }

    public ListView getWordList(){
        return wordList;
    }

    public void setAddHandler(EventHandler<ActionEvent> handler) {
        addHandler = handler;
    }

    public void setSaveHandler(EventHandler<ActionEvent> handler) {
        saveHandler = handler;
    }

    public void setCancelHandler(EventHandler<ActionEvent> handler) {
        cancelHandler = handler;
    }

    public void setSearchHandler(EventHandler<ActionEvent> handler) {
        searchHandler = handler;
    }
}