package org.psk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddWordController{

    private DictionaryModel dictionaryModel;
    public void setDictionaryModel(DictionaryModel dictionaryModel) {
        this.dictionaryModel = dictionaryModel;
    }
    @FXML
    private TextField currentDictionaryTextField;
    @FXML
    private Button addTranslationButton;

    @FXML
    private Button addButton;

    @FXML
    private Button removeTranslationButton;

    @FXML
    private Button removeButton;

    // ...

    @FXML
    private void handleAddButtonAction(ActionEvent event) {

    }

    @FXML
    private void handleAddTranslationButtonAction(ActionEvent event) {

    }

    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {

    }

    @FXML
    private void handleRemoveTranslationButtonAction(ActionEvent event) {

    }

}
