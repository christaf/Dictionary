package org.psk;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.Queue;

public class Dictionary {
    public final HashSet<Node> root = new HashSet<>();

    public void addTranslation(String word, String translation) {
        Node end = findEnd(word);
        if (end != null)
            end.translations.add(translation);
    }

    public void addWord(String word) {
//        TODO Add parent references ...
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
                    child.parent = currentNode;
                    break;
                }
            }
            if (nextNode == null) {
                nextNode = new Node(word.charAt(i));
                currentNode.children.add(nextNode);
            }
            nextNode.parent = currentNode;
            currentNode = nextNode;
        }
        currentNode.isEndOfWord = true;
        if (currentNode.translations == null) {
            currentNode.translations = new LinkedList<>();
        }
    }

    public void printAllWords() {
        for (Node child : root) {
            StringBuilder sb = new StringBuilder();
            sb.append(child.value);
            printAllWordsHelper(child, sb);
        }
    }

    private void printAllWordsHelper(Node node, StringBuilder sb) {
        if (node.isEndOfWord) {
            if(sb.toString().equals(""))
                return;
            System.out.println(sb.toString());
        }
        for (Node child : node.children) {
            sb.append(child.value);
            printAllWordsHelper(child, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public void printSucessors(String word) {
        Node end = findEnd(word);
        StringBuilder stringBuilder = new StringBuilder();
        if (end == null) {
            return;
        }
        while(end != null){
            stringBuilder.append(end.value);
            end = end.parent;
        }
        System.out.println(stringBuilder.toString());
    }


    public void insert(String word, String translation) {
        addWord(word);
        addTranslation(word, translation);
    }

    public Queue<String> findTranslationsQueueByWord(String word) {
        if (isWord(word)) System.out.println("is a word");
        if (isPartOfWord(word)) {
            System.out.println("is a part of word");
        }

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
        Node end = findEnd(word);
        if (end == null)
            return false;
        return (end.isEndOfWord);
    }

    public boolean isPartOfWord(String word) {
        Node end = findEnd(word);
        if (end == null)
            return false;
        HashSet<Node> children = end.children;
        return !children.isEmpty();
    }

    public boolean exploreNode() {
//        TODO support function for searching for phrases
        return false;
    }

    public Queue<Node> wordsThatStartsWithPhrase(String word) {
        Node end = findEnd(word);
        Queue<Node> otherPhrases = new LinkedList<>();

        if (end == null) return otherPhrases;


        return null;
    }
}

