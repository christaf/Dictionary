package org.psk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddWordController{

    private DictionaryModel dictionaryModel;
    public void setDictionaryModel(DictionaryModel dictionaryModel) {
        this.dictionaryModel = dictionaryModel;
    }
    public void initialize(){
        addButton.setOnMouseClicked(this::handleAddButtonAction);
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
    private void handleAddButtonAction(MouseEvent event) {
        dictionaryModel.currentDictionary.printAllWords();
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
