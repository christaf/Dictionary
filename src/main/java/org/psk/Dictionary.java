package org.psk;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.Queue;

public class Dictionary {
    private final HashSet<Node> root = new HashSet<>();

    private static class Node {
        char value;
        boolean isEndOfWord;
        Queue<String> translations = null;
        HashSet<Node> children = new HashSet<>();

        public Node(char value) {
            this.value = value;
        }
    }


    public void insert(String word, String translation) {
        Node currentNode = null;
        for (Node child : root) {
            if (child.value == word.charAt(0)) {
                currentNode = child;
                break;
            }
        }
        if (currentNode == null) {
            currentNode = new Node(word.charAt(0));
            root.add(currentNode);
        }
        for (int i = 1; i < word.length(); i++) {
            Node nextNode = null;
            for (Node child : currentNode.children) {
                if (child.value == word.charAt(i)) {
                    nextNode = child;
                    break;
                }
            }
            if (nextNode == null) {
                nextNode = new Node(word.charAt(i));
                currentNode.children.add(nextNode);
            }
            currentNode = nextNode;
        }
        currentNode.isEndOfWord = true;
        if (currentNode.translations == null) {
            currentNode.translations = new LinkedList<>();
        }
        currentNode.translations.offer(translation);
    }


    public Queue<String> search(String word) {
        Node currentNode = null;
        for (Node child : root) {
            if (child.value == word.charAt(0)) {
                currentNode = child;
                break;
            }
        }
        if (currentNode == null) {
            return null;
        }
        for (int i = 1; i < word.length(); i++) {
            Node nextNode = null;
            for (Node child : currentNode.children) {
                if (child.value == word.charAt(i)) {
                    nextNode = child;
                    break;
                }
            }
            if (nextNode == null) {
                return null;
            }
            currentNode = nextNode;
        }
        if (currentNode.isEndOfWord) {
            return currentNode.translations;
        }
        return null;
    }

    public boolean isWord(String word){
        Node currentNode = null;
        for (Node child : root) {
            if (child.value == word.charAt(0)) {
                currentNode = child;
                break;
            }
        }
        if (currentNode == null) {
            return false;
        }
        for (int i = 1; i < word.length(); i++) {
            Node nextNode = null;
            for (Node child : currentNode.children) {
                if (child.value == word.charAt(i)) {
                    nextNode = child;
                    break;
                }
            }
            if (nextNode == null) {
                return false;
            }
            currentNode = nextNode;
        }
        return currentNode.isEndOfWord;
    }

    public boolean isPartOfWord(String word){
        Node currentNode = null;
        for (Node child : root) {
            if (child.value == word.charAt(0)) {
                currentNode = child;
                break;
            }
        }
        if (currentNode == null) {
            return false;
        }
        for (int i = 1; i < word.length(); i++) {
            Node nextNode = null;
            for (Node child : currentNode.children) {
                if (child.value == word.charAt(i)) {
                    nextNode = child;
                    break;
                }
            }
            if (nextNode == null) {
                return false;
            }
            currentNode = nextNode;
        }
        return currentNode.children != null;
    }
}

