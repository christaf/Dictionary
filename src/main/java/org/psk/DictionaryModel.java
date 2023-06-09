package org.psk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DictionaryModel {

    public Dictionary currentDictionary;
    private final Dictionary dictionaryEnglishPolish = new Dictionary();
    private final Dictionary dictionaryPolishEnglish = new Dictionary();
    private final List<String> firstLanguageWords;
    private final List<String> secondLanguageWords;

    public void setCurrentDictionary() {
        if (currentDictionary == dictionaryEnglishPolish) {
            currentDictionary = dictionaryPolishEnglish;
        } else {
            currentDictionary = dictionaryEnglishPolish;
        }
    }

    public Dictionary getCurrentDictionary() {
        return currentDictionary;
    }

    public DictionaryModel(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    for (int i = 1; i < parts.length; i++) {
                        dictionaryEnglishPolish.insert(parts[0].trim(), parts[i].trim());
                        dictionaryPolishEnglish.insert(parts[i].trim(), parts[0].trim());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        firstLanguageWords = new ArrayList<>();
        secondLanguageWords = new ArrayList<>();
//        dictionaryPolishEnglish.printAllWords();
//        dictionary.printAllWords();
    }

    public void addWord(String firstLanguageWord, String secondLanguageWord) {
        currentDictionary.addWord(firstLanguageWord);
        currentDictionary.addTranslation(firstLanguageWord, secondLanguageWord);
    }

    public void editWord(String oldWord, String newWord) {

    }

    public List<String> getFirstLanguageWords() {
        return firstLanguageWords;
    }

    public List<String> getSecondLanguageWords() {
        return secondLanguageWords;
    }

    public Queue<String> findTranslationQueue(String word) {
        return currentDictionary.findTranslationsQueueByWord(word);
    }

    public void insertTranslation(String word, String translation) {
        currentDictionary.findEndOfWord(word).translations.add(translation);
    }

    /***
     * Function for debugging
     */

    public void printSuccessors(String word) {
        if (word != null && !word.equals(""))
            currentDictionary.printSuccessors(word);
    }

    public void printOtherPhrases(String word) {
        Queue<String> result = new LinkedList<>(currentDictionary.wordsThatStartsWithPhrase(word));
        if (result.isEmpty()) return;
        for (String phrases : result) {
            System.out.println(phrases);
        }
    }

    public Queue<String> findOtherPhrases(String word) {
        Queue<String> result = new LinkedList<>(currentDictionary.wordsThatStartsWithPhrase(word));
        if (result.isEmpty()) return null;
        return result;
    }
}
