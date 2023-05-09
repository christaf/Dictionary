package org.psk;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DictionaryController {
    private DictionaryModel model;

    @FXML
    private ListView<String> firstLanguageListView;

    @FXML
    private ListView<String> secondLanguageListView;

    @FXML
    private TextField searchTextField;

    public void initialize() {
        model = new DictionaryModel("tmp.txt");
        // TODO: Add initial data to model, if any
        // ...
        // Bind the list views to the data in the model
        //firstLanguageListView.setItems(FXCollections.observableList(model.getFirstLanguageWords()));
       // secondLanguageListView.setItems(FXCollections.observableList(model.getSecondLanguageWords()));
    }

    @FXML
    public void updateSearchListView() {
        String searchText = searchTextField.getText();
        // TODO: Implement search functionality
        // ...
    }

    @FXML
    public void switchLanguage() {
        // TODO: Implement switching of languages
        // ...
    }
}
