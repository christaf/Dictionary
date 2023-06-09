package org.psk;

import java.util.*;

public class Dictionary {

    private static class nodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    public TreeSet<Node> root = new TreeSet<Node>(new nodeComparator());

    public void addTranslation(String word, String translation) {
        Node end = findEnd(word);
        if (end != null)
            end.translations.add(translation);
    }

    public void addWord(String word) {
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
            if (sb.toString().equals("") || sb.toString().equals("\n") || sb.toString().equals(" "))
                return;
            System.out.println(sb.toString());
        }
        for (Node child : node.children) {
            sb.append(child.value);
            printAllWordsHelper(child, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public void printSuccessors(String word) {
        Node end = findEnd(word);
        StringBuilder stringBuilder = new StringBuilder();
        if (end == null) {
            return;
        }
        while (end != null) {
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
        Node endOfTheWord = findEnd(word);
        if (endOfTheWord == null) return null;

        boolean isEndOfTHeWord = endOfTheWord.isEndOfWord;
        if(!isEndOfTHeWord) return null;

        Queue<String> result = endOfTheWord.translations;
        if (result != null) {
            if(result.isEmpty()) return null;
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

