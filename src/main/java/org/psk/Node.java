package org.psk;

import java.util.HashSet;
import java.util.Queue;

public class Node {
    public char value;
    boolean isEndOfWord;
    Queue<String> translations = null;
    public HashSet<Node> children = new HashSet<>();

    public Node parent = null;

    public Node(char value) {
        this.value = value;
    }

    public Node(char value, Node parent) {
        this.value = value;
        this.parent = parent;
    }
}
