package main;

public class Edge {
    private Node source;
    private Node target;
    private int weight;
    private Edge next;

    public Edge(Node source, Node target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public Edge(Node source, Node target) {
        this(source, target, 0);
    }
}