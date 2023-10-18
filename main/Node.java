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
        next = n;
    }

    // get next node
    public Node getNext() {
        return next;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Node))
            return false;
        Node node = (Node) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }

     // method to check if the node is the last node
     public boolean isLast() {
        return true;
    }

    // method to check if the node is the first node
    public boolean isFirst() {
        return false;
    }

    // method to check if the node is the first node
    public boolean isNotFirst() {
        return true;
    }

    // method to check if the node is the last node
    public boolean isNotLast() {
        return false;
    }

    // method to print the node
    public void print() {
        
    }

    // method to change value of node to a new value
    public void changeValue(String newValue) {
        
    }

    // method to clone a node
    public Node clone() {
        return null;
    }

    // method to check if the node is equal to another node
    public boolean isEqual(Node node) {
        return false;
    }
}
