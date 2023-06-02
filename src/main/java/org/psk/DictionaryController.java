package org.psk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.util.Queue;

public class DictionaryController {
    public ToggleButton switchLanguageButton;
    private DictionaryModel model;

    @FXML
    private ListView<String> firstLanguageListView;

    @FXML
    private ListView<String> secondLanguageListView;

    @FXML
    private TextField searchTextField;

    public void initialize() {
        model = new DictionaryModel("tmp.txt");
    }

    @FXML
    public void onHintItemPressed(){

    }

    @FXML
    public void updateOnSearchInsertion() {
        String searchText = searchTextField.getText();
        updateSearchListView(searchText);
        updateHintListView(searchText);
    }

    @FXML
    public void updateSearchListView(String searchText) {
//        TODO Przenieść to do modelu ?

        if (searchText.equals("")) return;
//        TODO do sth with those edge cases
        secondLanguageListView.refresh();
        Queue<String> result = model.findTranslationQueue(searchText);
        ObservableList<String> items = FXCollections.observableArrayList();
        if (result != null)
            items.addAll(result);
        secondLanguageListView.setItems(items);

    }

    public void updateHintListView(String searchText) {

        if (searchText.equals("")) return;

        Queue<String> result = model.findOtherPhrases(searchText);
        if (result == null) return;
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(result);
        firstLanguageListView.setItems(items);
//        model.printOtherPhrases(searchText);
        firstLanguageListView.refresh();
    }

    @FXML
    public void switchLanguage() {
        // TODO: Implement switching of languages

    }
}
