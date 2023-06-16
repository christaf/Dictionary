package org.psk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;

import java.util.Queue;

public class DictionaryController {
    private AddWordWindow addWordWindow;
    private boolean isEditingGUIOpen = false;
    @FXML
    public ToggleButton dictionaryStateButton;
    @FXML
    public Button changeSceneButton;
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
                model.saveDictionary("tmp.txt");
            }
        });

        dictionaryStateButton.setText(model.getDictionaryState().getDescription());
        dictionaryStateButton.setOnMouseClicked(this::updateDictionaryStateButton);
        changeSceneButton.setOnMouseClicked(this::handleEditionButton);

    }

    @FXML
    public void updateDictionaryStateButton(MouseEvent event){
        if(model.getDictionaryState().equals(DictionaryState.POLISH_ENGLISH)){
            dictionaryStateButton.setText(DictionaryState.POLISH_ENGLISH.getDescription());
        } else {
            dictionaryStateButton.setText(DictionaryState.ENGLISH_POLISH.getDescription());
        }
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

    private void handleEditionButton(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            if (addWordWindow == null) {
                openEditingGUI();
            }
        }
    }

    @FXML
    public void switchLanguage() {
        model.setCurrentDictionary();
        hintListView.refresh();
        translationsListView.refresh();
    }

    public void openEditingGUI() {
        if (addWordWindow == null) {
            addWordWindow = new AddWordWindow(model);
            addWordWindow.show();
            addWordWindow.setOnCloseRequest(event -> {
                isEditingGUIOpen = false;
                addWordWindow = null;
            });
        }
    }
}
