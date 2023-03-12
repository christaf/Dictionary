package org.psk;
import java.util.LinkedList;
import java.util.Queue;

public class Dictionary {

    private static final int ALPHABET_SIZE = 26;

    private final Node[] root = new Node[ALPHABET_SIZE];

    private static class Node {
        boolean isEndOfWord;
        Queue<String> translations;
        Node[] children = new Node[ALPHABET_SIZE];
    }

    public void insert(String word, String translation) {
        int index;
        Node currentNode = root[word.charAt(0) - 'a'];
        for (int i = 1; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (currentNode.children[index] == null) {
                currentNode.children[index] = new Node();
            }
            currentNode = currentNode.children[index];
        }
        currentNode.isEndOfWord = true;
        if (currentNode.translations == null) {
            currentNode.translations = new LinkedList<>();
        }
        currentNode.translations.offer(translation);
    }

    public Queue<String> search(String word) {
        Node currentNode = root[word.charAt(0) - 'a'];
        for (int i = 1; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (currentNode.children[index] == null) {
                return null;
            }
            currentNode = currentNode.children[index];
        }
        if (currentNode.isEndOfWord) {
            return currentNode.translations;
        }
        return null;
    }
}

