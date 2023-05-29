package org.psk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class DictionaryModel {

    private final Dictionary dictionary = new Dictionary();
    private final List<String> firstLanguageWords;
    private final List<String> secondLanguageWords;

    public DictionaryModel(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    dictionary.insert(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        firstLanguageWords = new ArrayList<>();
        secondLanguageWords = new ArrayList<>();
        dictionary.printAllWords();
    }

    public void addWord(String firstLanguageWord, String secondLanguageWord) {
        firstLanguageWords.add(firstLanguageWord);
        secondLanguageWords.add(secondLanguageWord);
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
        return dictionary.findTranslationsQueueByWord(word);
    }

    public void insertTranslation(String word, String translation) {
        dictionary.findEnd(word).translations.add(translation);
    }

    public void printSucessors(String word){
        if(word != null && !word.equals(""))
            dictionary.printSucessors(word);
    }
}
