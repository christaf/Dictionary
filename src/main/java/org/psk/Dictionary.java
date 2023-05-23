package org.psk;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dictionary {
    private final HashSet<Node> root = new HashSet<>();

    public void printAllWords() {
        for (Node child : root) {
            StringBuilder sb = new StringBuilder();
            sb.append(child.value);
            printAllWordsHelper(child, sb);
        }
    }

    private void printAllWordsHelper(Node node, StringBuilder sb) {
        if (node.isEndOfWord) {
            System.out.println(sb.toString());
        }
        for (Node child : node.children) {
            sb.append(child.value);
            printAllWordsHelper(child, sb);
            sb.deleteCharAt(sb.length() - 1);
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

    public Queue<String> search2(String word) {
        if (isWord(word)) System.out.println("is a word");
        if (isPartOfWord(word)) System.out.println("is a port of word");
        Queue<String> result = search(word);
        if (result != null) {
            for (String s : result) {
                System.out.println(s);
            }
        }
        return result;
    }

    public Node findEnd(String word) {
        Node currentNode = null;
        for (Node child : root) {
            if (child.value == word.charAt(0)) {
                currentNode = child;
                break;
            }
        }
        if (currentNode == null) return null;

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
            return currentNode;
        }
        return null;
    }

    public Queue<String> search(String word) {
        int counter = 0;
        Node endOfTheWord = findEnd(word);
        if (endOfTheWord == null) return null;
        Queue<String> result = new LinkedList<>(endOfTheWord.translations);
//        TODO zeby pokazywalo wszystkie ktore sie zaczynaja od tego sformulowania
//        counter += 1;
//        Node tmp = endOfTheWord;
//        Node prev = endOfTheWord;
//        while (tmp != null) {
//            if (counter > 10) break;
//            for (Node child : endOfTheWord.children) {
//                if (child.isEndOfWord) {
//                    result.addAll(child.translations);
//                    counter += 1;
//                }
//                tmp = child;
//            }
//            tmp = prev;
//        }
        return result;
    }

    public boolean isWord(String word) {
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

    public boolean isPartOfWord(String word) {
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

