package org.psk;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String DICTIONARY_FILE_NAME = "dictionary.txt";
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private List<String> words = new ArrayList<>();
    private ObservableList<String> foundWords = FXCollections.observableArrayList();

    private TextField searchField;
    private ListView<String> wordList;

    @Override
    public void start(Stage primaryStage) {
        // Read the dictionary from the file
        readDictionary();

        // Create the search UI
        searchField = new TextField();
        searchField.setPromptText("Enter a word to search");
        searchField.setOnKeyReleased(event -> searchWords());

        Button addButton = new Button("Add Word");
        addButton.setOnAction(event -> addWord("dupa"));

        HBox searchBox = new HBox(10, searchField, addButton);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        wordList = new ListView<>(foundWords);
        wordList.setPrefWidth(200);

        VBox searchPane = new VBox(10, searchBox, wordList);
        searchPane.setPadding(new Insets(10));

        // Create the add word UI
        Label addLabel = new Label("Enter a word to add:");
        TextField addField = new TextField();
        addField.setPromptText("Enter a word to add");

        Button saveButton = new Button("Save Word");
        saveButton.setOnAction(event -> saveWord(addField.getText()));

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> primaryStage.setScene(searchScene(primaryStage)));

        GridPane addGrid = new GridPane();
        addGrid.setVgap(10);
        addGrid.setHgap(10);
        addGrid.setPadding(new Insets(10));
        addGrid.add(addLabel, 0, 0);
        addGrid.add(addField, 1, 0);
        addGrid.add(saveButton, 0, 1);
        addGrid.add(cancelButton, 1, 1);

        VBox addPane = new VBox(10, addGrid);
        addPane.setPadding(new Insets(10));

        // Create the main scene
        Scene scene = searchScene(primaryStage);

        // Show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary");
        primaryStage.show();
    }

    private Scene addScene(Stage stage) {
        TextField addField = new TextField();
        addField.setPromptText("Enter a word to add");

        Button saveButton = new Button("Save Word");
        saveButton.setOnAction(event -> {
            String word = addField.getText().toLowerCase();
            if (word.matches("[a-z]+")) {
                addWord(word);
                stage.setScene(searchScene(stage));
            } else {
                addField.setText("");
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> stage.setScene(searchScene(stage)));

        GridPane addGrid = new GridPane();
        addGrid.setVgap(10);
        addGrid.setHgap(10);
        addGrid.setPadding(new Insets(10));
        addGrid.add(new Label("Enter a word to add:"), 0, 0);
        addGrid.add(addField, 1, 0);
        addGrid.add(saveButton, 0, 1);
        addGrid.add(cancelButton, 1, 1);

        VBox addPane = new VBox(10, addGrid);
        addPane.setPadding(new Insets(10));

        return new Scene(addPane);
    }

    private void readDictionary() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DICTIONARY_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addWord(String word) {
        words.add(word.toLowerCase());
        writeDictionary();
    }

    private void writeDictionary() {
        try (FileWriter writer = new FileWriter(DICTIONARY_FILE_NAME)) {
            for (String word : words) {
                writer.write(word + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveWord(String word) {
        if (word.matches("[a-z]+")) {
            addWord(word);
            searchWords();
        }
    }

    private void searchWords() {
        foundWords.clear();
        String search = searchField.getText().toLowerCase();
        if (search.length() > 0) {
            for (String word : words) {
                if (word.startsWith(search) || word.endsWith(search)) {
                    foundWords.add(word);
                }
            }
        }
    }


    private Scene searchScene(Stage stage) {
        foundWords.clear();
        return new Scene(searchPane(stage));
    }

    private VBox searchPane(Stage stage) {
        return new VBox(10, searchBox(stage), wordList);
    }

    private HBox searchBox(Stage stage) {
        return new HBox(10, searchField, addButton(stage));
    }

    private Button addButton(Stage stage) {
        Button addButton = new Button("Add Word");
        addButton.setOnAction(event -> stage.setScene(addScene(stage)));
        return addButton;

    }


    public static void main(String[] args) {
        launch(args);
    }
}