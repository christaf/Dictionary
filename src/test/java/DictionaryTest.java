import org.junit.Test;
import org.psk.Dictionary;
import org.psk.Node;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Objects;
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
        Queue<String> translations = dictionary.findTranslationsQueueByWord(word);
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
        Queue<String> translations = dictionary.findTranslationsQueueByWord(word);
        assertNotNull(translations);
        assertEquals(2, translations.size());
        assertEquals(translation1, translations.poll());
        assertEquals(translation2, translations.poll());
    }

    @Test
    public void testSearch_NonExistentWord() {
        Dictionary dictionary = new Dictionary();

        // Search for a non-existent word
        Queue<String> translations = dictionary.findTranslationsQueueByWord("goodbye");
        assertNull(translations);
        dictionary.insert("kot", "cat");
        translations = dictionary.findTranslationsQueueByWord("goodbye");
        assertNull(translations);
    }

    @Test
    public void testIsPartOfWord() {
        Dictionary dict = new Dictionary();
        dict.insert("hello", "hola");
//        assertTrue(dict.isPartOfWord("hel"));
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
//        assertTrue(dict.isPartOfWord("he"));
    }

    @Test
    public void testInsertSpecialChars() {
        Dictionary dict = new Dictionary();
        dict.insert("cliché", "stereotype");
        Queue<String> translations = dict.findTranslationsQueueByWord("cliché");
        assertNotNull(translations);
        assertEquals(1, translations.size());
        assertEquals("stereotype", translations.peek());
    }

    @Test
    public void testPrintAllWords() {
//        TODO it may be better solution to the problem
//        public final static char CR  = (char) 0x0D;
//public final static char LF  = (char) 0x0A;
//
//public final static String CRLF  = "" + CR + LF;     // "" forces conversion to string
//
//String twoLines = "Line1" + CRLF + "Line2";   // 12 characters
        Dictionary dictionary = new Dictionary();
        dictionary.insert("apple", "manzana");

        ByteArrayOutputStream oneWord = new ByteArrayOutputStream();
        System.setOut(new PrintStream(oneWord));

        dictionary.printAllWords();

        String oneWordOutput = "apple\r\n";
        assertEquals(oneWordOutput, oneWord.toString());

        dictionary.insert("banana", "plátano");
        dictionary.insert("orange", "naranja");

        ByteArrayOutputStream otherWords = new ByteArrayOutputStream();
        System.setOut(new PrintStream(otherWords));

        dictionary.printAllWords();

        String otherWordsOutput = "apple\r\nbanana\r\norange\r\n";
        assertEquals(otherWordsOutput, otherWords.toString());
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

    @Test
    public void testParentAssignment() {
        Dictionary dictionary = new Dictionary();
        dictionary.addWord("hello");
        dictionary.addWord("world");
        dictionary.addWord("hey");

        // Test parent assignment for 'l' in "hello"
        Node helloNode = getNodeByValue(dictionary, 'h');
        Node eNode = getNodeByValue(helloNode, 'e');
        assertEquals('h', Objects.requireNonNull(eNode).parent.value);
        assertEquals(helloNode, eNode.parent);

        // Test parent assignment for 'h' in "hey"
        Node heyNode = getNodeByValue(dictionary, 'h');
        assertNull(heyNode.parent);

        // Test parent assignment for 'y' in "hey"
        Node yNode = dictionary.findEndOfWord("hey");
        assertEquals('e', yNode.parent.value);
        assertEquals(eNode, yNode.parent);
    }

    @Test
    public void testWordsThatStartsWithPhrase() {
        // Create an instance of the class containing the method to be tested
        Dictionary dictionary = new Dictionary();
        dictionary.addWord("apple");
        dictionary.addTranslation("apple", "jablko");

        dictionary.addWord("apples");
        dictionary.addTranslation("apples", "jablka");

        dictionary.addWord("applejuice");
        dictionary.addTranslation("applejuice", "jablkowysok");

        // Call the method and get the result
        Queue<String> result = dictionary.wordsThatStartsWithPhrase("apple");

        // Create a sample queue of expected phrases
        Queue<String> expectedPhrases = new LinkedList<>();
        expectedPhrases.add("apple");
        expectedPhrases.add("apples");
        expectedPhrases.add("applejuice");

        // Assert that the result matches the expected queue
        assertEquals(expectedPhrases, result);
    }


    @Test
    public void testRemoveWord() {
        // Create an instance of the class containing the method to be tested
        Dictionary dictionary = new Dictionary();
        dictionary.addWord("apple");
        dictionary.addTranslation("apple", "jablko");

        dictionary.addWord("apples");
        dictionary.addTranslation("apples", "jablka");

        dictionary.addWord("applejuice");
        dictionary.addTranslation("applejuice", "jablkowysok");

        dictionary.removeWord("apple");
        assertFalse(dictionary.isWord("apple"));
        assertTrue(dictionary.isWord("apples"));
        assertTrue(dictionary.isWord("applejuice"));
        assertTrue(dictionary.isPartOfWord("apple"));
    }

    @Test
    public void testRemoveTranslation() {
        Dictionary dictionary = new Dictionary();
        dictionary.addWord("apple");
        dictionary.addTranslation("apple", "jabłko");
//        dictionary.addTranslation("apple", "owoc");
        Queue<String> translationsBeforeRemoval = dictionary.findTranslationsQueueByWord("apple");
        dictionary.removeTranslation("apple", "jabłko");
        Queue<String> translationsAfterRemoval = dictionary.findTranslationsQueueByWord("apple");
        assertNotNull(translationsBeforeRemoval);
        assertNull(translationsAfterRemoval);
    }

    private Node getNodeByValue(Dictionary dictionary, char value) {
        for (Node node : dictionary.root) {
            if (node.value == value) {
                return node;
            }
        }
        return null;
    }

    private Node getNodeByValue(Node parentNode, char value) {
        if (parentNode == null) return null;
        for (Node node : parentNode.children) {
            if (node.value == value) {
                return node;
            }
        }
        return null;
    }
}
