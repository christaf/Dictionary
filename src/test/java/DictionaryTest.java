import org.junit.Test;
import org.psk.Dictionary;
import org.psk.Node;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        Queue<String> translations = dict.search("cliché");
        assertNotNull(translations);
        assertEquals(1, translations.size());
        assertEquals("stereotype", translations.peek());
    }
    @Test
    public void testPrintAllWords() {
        Dictionary dictionary = new Dictionary();
        dictionary.insert("apple", "manzana");

        ByteArrayOutputStream oneWord = new ByteArrayOutputStream();
        System.setOut(new PrintStream(oneWord));

        dictionary.printAllWords();

        String oneWordOutput = "apple\n";
//        assertEquals(oneWordOutput, oneWord.toString());

        dictionary.insert("banana", "plátano");
        dictionary.insert("orange", "naranja");

        ByteArrayOutputStream otherWords = new ByteArrayOutputStream();
        System.setOut(new PrintStream(otherWords));

        dictionary.printAllWords();

        String otherWordsOutput = "apple\nbanana\norange\n";
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
        Node lNode = getNodeByValue(Objects.requireNonNull(helloNode), 'l');
        assertEquals('h', Objects.requireNonNull(lNode).parent.value);
        assertEquals(helloNode, lNode.parent);

        // Test parent assignment for 'o' in "hello"
        Node oNode = getNodeByValue(helloNode, 'o');
        assertEquals('l', Objects.requireNonNull(oNode).parent.value);
        assertEquals(lNode, oNode.parent);

        // Test parent assignment for 'h' in "hey"
        Node heyNode = getNodeByValue(dictionary, 'h');
        assertEquals(null, heyNode.parent);

        // Test parent assignment for 'y' in "hey"
        Node yNode = getNodeByValue(heyNode, 'y');
        assertEquals('h', yNode.parent.value);
        assertEquals(heyNode, yNode.parent);
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
        for (Node node : parentNode.children) {
            if (node.value == value) {
                return node;
            }
        }
        return null;
    }
}
