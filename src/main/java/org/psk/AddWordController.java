package org.psk;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddWordController {

    public TextField addWordTextField;
    public TextField addWordsTranslationTextField;
    public TextField removeWordTextField;
    public Button changeDirectoryButton;
    private DictionaryModel dictionaryModel;

    public void setup(DictionaryModel dictionaryModel) {
        this.dictionaryModel = dictionaryModel;

        handleCurrentDictionaryTextField(dictionaryModel.getDictionaryState().getDescription());

        addButton.setOnMouseClicked(this::handleAddButtonAction);
        changeDirectoryButton.setOnMouseClicked(this::handleChangeDictionaryButton);
        removeButton.setOnMouseClicked(this::handleRemoveButtonAction);

    }

    @FXML
    private TextField currentDictionaryTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private void handleAddButtonAction(MouseEvent event) {
        String word = addWordTextField.getText();
        String translation = addWordsTranslationTextField.getText();
        dictionaryModel.currentDictionary.addWord(word);
        if (translation != null)
                dictionaryModel.insertTranslation(word, translation);
        addWordTextField.clear();
        addWordsTranslationTextField.clear();
        dictionaryModel.saveDictionary("tmp.txt");
    }

    @FXML
    private void handleChangeDictionaryButton(MouseEvent event) {
        dictionaryModel.setCurrentDictionary();
        handleCurrentDictionaryTextField(dictionaryModel.getDictionaryState().getDescription());
    }

    @FXML
    private void handleCurrentDictionaryTextField(String currentDictionaryName) {
        currentDictionaryTextField.setText(currentDictionaryName);
    }

    @FXML
    private void handleRemoveButtonAction(MouseEvent event) {
        String wordToRemove = removeWordTextField.getText();
        dictionaryModel.currentDictionary.removeWord(wordToRemove);
        removeWordTextField.clear();
        dictionaryModel.saveDictionary("tmp.txt");
    }

}
