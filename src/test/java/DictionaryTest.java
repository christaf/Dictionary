import org.junit.Test;
import org.psk.Dictionary;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Queue;

import static org.junit.Assert.*;

public class DictionaryTest {

    @Test
    public void testConstructor() {
        Dictionary dictionary = new Dictionary();
        assertNotNull(dictionary);
    }

    @Test
    public void testInsertAndSearch_SingleTranslation() {
        Dictionary dictionary = new Dictionary();
        String word = "hello";
        String translation = "hola";

        // Insert the word and translation
        dictionary.insert(word, translation);

        // Search for the word and verify the translation
        Queue<String> translations = dictionary.search(word);
        assertNotNull(translations);
        assertEquals(1, translations.size());
        assertEquals(translation, translations.poll());
    }

    @Test
    public void testInsertAndSearch_MultipleTranslations() {
        Dictionary dictionary = new Dictionary();
        String word = "hello";
        String translation1 = "hola";
        String translation2 = "bonjour";

        // Insert the word and translations
        dictionary.insert(word, translation1);
        dictionary.insert(word, translation2);

        // Search for the word and verify the translations
        Queue<String> translations = dictionary.search(word);
        assertNotNull(translations);
        assertEquals(2, translations.size());
        assertEquals(translation1, translations.poll());
        assertEquals(translation2, translations.poll());
    }

    @Test
    public void testSearch_NonExistentWord() {
        Dictionary dictionary = new Dictionary();

        // Search for a non-existent word
        Queue<String> translations = dictionary.search("goodbye");
        assertNull(translations);
        dictionary.insert("kot", "cat");
        translations = dictionary.search("goodbye");
        assertNull(translations);
    }

    @Test
    public void testIsPartOfWord() {
        Dictionary dict = new Dictionary();
        dict.insert("hello", "hola");
        assertTrue(dict.isPartOfWord("hel"));
        assertFalse(dict.isPartOfWord("hol"));
        assertFalse(dict.isPartOfWord("abc"));
    }

    @Test
    public void testInsertPrefix() {
        Dictionary dict = new Dictionary();
        dict.insert("hello", "hola");
        dict.insert("help", "ayuda");
        assertTrue(dict.isWord("hello"));
        assertTrue(dict.isWord("help"));
        assertFalse(dict.isWord("he"));
        assertTrue(dict.isPartOfWord("he"));
    }
    @Test
    public void testInsertSpecialChars() {
        Dictionary dict = new Dictionary();
        dict.insert("cliché", "stereotype");
        Queue<String> translations = dict.search("cliché");
        assertNotNull(translations);
        assertEquals(1, translations.size());
        assertEquals("stereotype", translations.peek());
    }
    @Test
    public void testPrintAllWords() {
        Dictionary dictionary = new Dictionary();
        dictionary.insert("apple", "manzana");
        dictionary.insert("banana", "plátano");
        dictionary.insert("orange", "naranja");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        dictionary.printAllWords();

        String expectedOutput = "apple\nbanana\norange\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testPrintAllWordsWithNoWords() {
        Dictionary dictionary = new Dictionary();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        dictionary.printAllWords();

        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
    }

}
