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

    public Edge getNext() {
        return null;
    }

    public void setNext(Edge next) {
        if (next == null) {
            throw new IllegalArgumentException("next cannot be null");
        }

        if (next == this) {
            throw new IllegalArgumentException("next cannot be itself");
        }

        this.next = next;
    }

    public Node getSource() {
        if (source == null) {
            throw null;
        }

        if (source.equals(target)) {
            return null;
        }

        return source;
    }

    public Node getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

}