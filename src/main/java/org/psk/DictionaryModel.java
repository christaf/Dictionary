package org.psk;

import com.sun.scenario.effect.impl.sw.java.JSWBoxBlurPeer;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;


public class DictionaryModel {


    private DictionaryState dictionaryState;

    public void setDictionaryState(DictionaryState dictionaryState) {
        this.dictionaryState = dictionaryState;
    }

    public DictionaryState getDictionaryState() {
        return dictionaryState;
    }

    public Dictionary currentDictionary;
    private final Dictionary dictionaryEnglishPolish = new Dictionary();
    private final Dictionary dictionaryPolishEnglish = new Dictionary();

    public void setCurrentDictionary() {
        if (currentDictionary == dictionaryEnglishPolish) {
            currentDictionary = dictionaryPolishEnglish;
            this.dictionaryState = DictionaryState.POLISH_ENGLISH;
        } else {
            currentDictionary = dictionaryEnglishPolish;
            this.dictionaryState = DictionaryState.ENGLISH_POLISH;
        }
    }

    public Dictionary getCurrentDictionary() {
        return currentDictionary;
    }

    public DictionaryModel(String filename) {
        readDictionary(filename);
        setCurrentDictionary();
    }

    public void readDictionary(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String mainWord = parts[0].trim();
                    for (int i = 1; i < parts.length; i++) {
                        String translation = parts[i].trim();
                        dictionaryEnglishPolish.insert(mainWord, translation);
                        dictionaryPolishEnglish.insert(translation, mainWord);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDictionary(String filename) {

        try ( FileWriter writer = new FileWriter(filename)) {
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (Node child : dictionaryEnglishPolish.root) {
                StringBuilder sb = new StringBuilder();
                sb.append(child.value);
                saveDictionaryHelper(child, sb, bufferedWriter);

            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveDictionaryHelper(Node node, StringBuilder sb, BufferedWriter bufferedWriter) {
        if (node.isEndOfWord) {
            if (sb.toString().equals("") || sb.toString().equals("\n") || sb.toString().equals(" "))
                return;
            String mainWord = sb.toString();
            StringBuilder lineBuilder = new StringBuilder();
            lineBuilder.append(mainWord);

            Queue<String> translations = dictionaryEnglishPolish.findTranslationsQueueByWord(mainWord);
            for (String translation : translations) {
                lineBuilder.append(",");
                lineBuilder.append(translation);
            }

            try {
                System.out.println(lineBuilder.toString());
                bufferedWriter.write(lineBuilder.toString());
                bufferedWriter.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        for (Node child : node.children) {
            sb.append(child.value);
            saveDictionaryHelper(child, sb, bufferedWriter);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public void editWord(String oldWord, String newWord) {
        Queue<String> toUpdate = currentDictionary.findEndOfWord(oldWord).translations;
        setCurrentDictionary();
        for (String translation : toUpdate) {
            currentDictionary.addTranslation(translation, newWord);
            currentDictionary.removeTranslation(translation, oldWord);
        }
        setCurrentDictionary();
        currentDictionary.replaceWord(oldWord, newWord);
    }

    public Queue<String> findTranslationQueue(String word) {
        return currentDictionary.findTranslationsQueueByWord(word);
    }

    public void insertTranslation(String word, String translation) {
        currentDictionary.findEndOfWord(word).translations.add(translation);
    }

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
