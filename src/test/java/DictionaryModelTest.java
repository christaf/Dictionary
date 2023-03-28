import org.junit.Before;
import org.junit.Test;
import org.psk.DictionaryModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DictionaryModelTest {

    private DictionaryModel dictionary;

    @Before
    public void setUp() {
        dictionary = new DictionaryModel();
    }

    @Test
    public void testReadDictionary() throws IOException {
        File dictionaryFile = new File("dictionary.txt");
        if (!dictionaryFile.exists()) {
            dictionaryFile.createNewFile();
        }
        FileWriter writer = new FileWriter(dictionaryFile);
        writer.write("apple\nbanana\ncherry\ndate\n");
        writer.close();

        dictionary.readDictionary();

        assertEquals(4, dictionary.words.size());
        assertTrue(dictionary.words.contains("apple"));
        assertTrue(dictionary.words.contains("banana"));
        assertTrue(dictionary.words.contains("cherry"));
        assertTrue(dictionary.words.contains("date"));
    }

    @Test
    public void testAddWord() {
        dictionary.addWord("eggplant");

        assertEquals(1, dictionary.words.size());
        assertTrue(dictionary.words.contains("eggplant"));
    }

    @Test
    public void testSearchWords() {
        dictionary.words.add("apple");
        dictionary.words.add("banana");
        dictionary.words.add("cherry");
        dictionary.words.add("date");

        List<String> foundWords = dictionary.searchWords("a");

        assertEquals(2, foundWords.size());
        assertTrue(foundWords.contains("apple"));
        assertTrue(foundWords.contains("banana"));
    }
}
