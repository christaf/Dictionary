package org.psk;

import java.util.HashSet;
import java.util.Queue;

public class Node {
    char value;
    boolean isEndOfWord;
    Queue<String> translations = null;
    HashSet<Node> children = new HashSet<>();

    Node parent = null;

    public Node(char value) {
        this.value = value;
    }

    public Node(char value, Node parent) {
        this.value = value;
        this.parent = parent;
    }
}
