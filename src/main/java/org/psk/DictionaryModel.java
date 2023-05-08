package org.psk;

import java.util.ArrayList;
import java.util.List;

public class DictionaryModel {
    private final List<String> firstLanguageWords;
    private final List<String> secondLanguageWords;

    public DictionaryModel() {
        firstLanguageWords = new ArrayList<>();
        secondLanguageWords = new ArrayList<>();
    }

    public void addWord(String firstLanguageWord, String secondLanguageWord) {
        firstLanguageWords.add(firstLanguageWord);
        secondLanguageWords.add(secondLanguageWord);
    }

    public List<String> getFirstLanguageWords() {
        return firstLanguageWords;
    }

    public List<String> getSecondLanguageWords() {
        return secondLanguageWords;
    }
}
