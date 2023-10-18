package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Path {
    private List<Node> path;
    private int totalCost;

    public Path(int totalCost, Node... nodes) {
        this(totalCost, Arrays.asList(nodes));
    }

    public Path(int totalCost, List<Node> list) {
        this.path = new ArrayList<>(list);
        this.totalCost = totalCost;
    }
}
