package main;

import java.util.Objects;

public class Node {
    private String value;
    private Node next;

    public Node(String v) {
        value = v;
        next = null;
    }

    // set next node
    public void setNext(Node n) {
    }

    // get next node
    public Node getNext() {
        return null;
    }

    public String getValue() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Node{");
        return sb.toString();
    }
}
