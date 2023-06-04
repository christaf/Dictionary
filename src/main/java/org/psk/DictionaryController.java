package org.psk;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.EventListener;
import java.util.Queue;

public class DictionaryController {
    public ToggleButton switchLanguageButton;
    private DictionaryModel model;

    @FXML
    private ListView<String> hintListView;

    @FXML
    private ListView<String> translationsListView;

    @FXML
    private TextField searchTextField;
    private long previousClickTime = 0;
    private String previousClickedHintWord = "";

    public void initialize() {
        model = new DictionaryModel("tmp.txt");
        hintListView.setEditable(true);
        hintListView.setOnMouseClicked(this::handleHintListViewClick);
        hintListView.setCellFactory(TextFieldListCell.forListView());

        hintListView.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> t) {
                hintListView.getItems().set(t.getIndex(), t.getNewValue());
                System.out.println("setOnEditCommit");
            }
        });

    }


    @FXML
    public void updateOnSearchInsertion() {
        String searchText = searchTextField.getText();
        updateHintListView(searchText);
        updateTranslationsListView(searchText);
    }

    @FXML
    public void updateTranslationsListView(String searchText) {

        translationsListView.refresh();
        if (searchText.equals("")) return;

        Queue<String> translationQueue = model.findTranslationQueue(searchText);
        ObservableList<String> items = FXCollections.observableArrayList();
        if (translationQueue != null) items.addAll(translationQueue);
        translationsListView.setItems(items);

    }

    public void updateHintListView(String searchText) {

        if (searchText.equals("")) return;

        Queue<String> otherPhrases = model.findOtherPhrases(searchText);
        ObservableList<String> items = FXCollections.observableArrayList();
        if (otherPhrases != null) items.addAll(otherPhrases);
        hintListView.setItems(items);
        hintListView.refresh();
    }

    private void handleHintListViewClick(MouseEvent event) {
        if (event.getClickCount() == 1) { // Check for single click
            String clickedHintWord = hintListView.getSelectionModel().getSelectedItem();
            if(clickedHintWord == null) return;
            // Calculate the time difference since the previous click
            long currentClickTime = System.currentTimeMillis();
            long clickTimeDifference = currentClickTime - previousClickTime;

            if (clickedHintWord.equals(previousClickedHintWord) && clickTimeDifference <= 1000) {
                // Same item clicked twice within a 1-second gap
//                model.editWord(clickedHintWord, "A");
                System.out.println("editing");
            }

            // Update the previous click information
            previousClickTime = currentClickTime;
            previousClickedHintWord = clickedHintWord;

            searchTextField.setText(clickedHintWord);
            updateHintListView(clickedHintWord);
            updateTranslationsListView(clickedHintWord);
        }
    }


    @FXML
    public void switchLanguage() {
        // TODO: Implement switching of languages

    }
}
