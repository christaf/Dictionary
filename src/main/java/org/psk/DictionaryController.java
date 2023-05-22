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
    public void updateSearchListView() {
//        TODO Przenieść to do modelu
        ObservableList<String> items = FXCollections.observableArrayList();
        String searchText = searchTextField.getText();
        if (searchText.equals("")) return;
        System.out.println(searchText);
        Queue<String> result = this.model.findTranslationQueue(searchText);
        if (result == null) {
            return;
        }
        for (String s : result) {
            System.out.println(s);
            items.add(s);
        }
        secondLanguageListView.setItems(items);
        System.out.println(secondLanguageListView.getItems());

    }

    @FXML
    public void switchLanguage() {
        // TODO: Implement switching of languages

    }
}
