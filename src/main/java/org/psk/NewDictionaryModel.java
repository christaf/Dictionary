package org.psk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * CLass NewDictionaryModel is responsible for managing the dictionary and searching for words.
 */
public class NewDictionaryModel {
    private final String DICTIONARY_FILE_NAME; // = "dictionary.txt";

    public NewDictionaryModel(String FileName) {
        DICTIONARY_FILE_NAME = FileName;
    }

    public final List<String> words = new ArrayList<>();

    public void readDictionary() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DICTIONARY_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(words);
    }

    public void addWord(String word) {
        words.add(word.toLowerCase());
        Collections.sort(words);
        writeDictionary();
    }

    protected void writeDictionary() {
        try (FileWriter writer = new FileWriter(DICTIONARY_FILE_NAME)) {
            for (String word : words) {
                writer.write(word + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> searchWords(String search) {
        List<String> foundWords = new ArrayList<>();
        if (search.length() > 0) {
            for (String word : words) {
                if (word.startsWith(search) || word.endsWith(search)) {
                    foundWords.add(word);
                }
            }
        }
        return foundWords;
    }
}
