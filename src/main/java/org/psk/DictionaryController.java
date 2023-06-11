package org.psk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.TextFieldListCell;
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
        hintListView.setEditable(true);
        hintListView.setOnMouseClicked(this::handleHintListViewClick);
        hintListView.setCellFactory(TextFieldListCell.forListView());

        hintListView.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
            @Override
            public void handle(ListView.EditEvent<String> stringEditEvent) {
                int indexOfEditedWord = hintListView.getEditingIndex();
                String oldWord = hintListView.getItems().get(indexOfEditedWord);
                String newWord = stringEditEvent.getNewValue();
                model.editWord(oldWord, newWord);
                hintListView.getItems().set(stringEditEvent.getIndex(), stringEditEvent.getNewValue());
            }
        });

        switchLanguageButton.setOnMouseClicked(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (switchLanguageButton.getText().equals("Polish->English")) {
                            switchLanguageButton.setText("English->Polish");
                        } else {
                            switchLanguageButton.setText("Polish->English");
                            switchLanguageButton.setText("Polish->English");
                        }
                    }
                }
        );

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
            if (clickedHintWord == null) return;

            searchTextField.setText(clickedHintWord);
            updateHintListView(clickedHintWord);
            updateTranslationsListView(clickedHintWord);
        }
    }


    @FXML
    public void switchLanguage() {
        model.setCurrentDictionary();
        hintListView.refresh();
        translationsListView.refresh();
    }
}
