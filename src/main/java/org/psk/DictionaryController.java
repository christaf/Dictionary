package org.psk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

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

    public void initialize() {
        model = new DictionaryModel("tmp.txt");
        hintListView.setOnMouseClicked(this::handleHintListViewClick);
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
        if (event.getClickCount() == 1) {
            String clickedHintWord = hintListView.getSelectionModel().getSelectedItem();
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
