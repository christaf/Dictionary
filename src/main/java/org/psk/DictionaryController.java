package org.psk;

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
        //this.searchTextField
        // TODO: Add initial data to model, if any
        // ...
        // Bind the list views to the data in the model
        //firstLanguageListView.setItems(FXCollections.observableList(model.getFirstLanguageWords()));
       // secondLanguageListView.setItems(FXCollections.observableList(model.getSecondLanguageWords()));
    }

    @FXML
    public void updateSearchListView() {
        String searchText = searchTextField.getText();
        System.out.println(searchText);
        Queue<String> result = this.model.search(searchText);
        if(result == null){
            return;
        }
        for(String s: result){
            System.out.println(s);
        }
        // TODO: Implement search functionality
        // ...
    }

    @FXML
    public void switchLanguage() {
        // TODO: Implement switching of languages
        // ...
    }
}
