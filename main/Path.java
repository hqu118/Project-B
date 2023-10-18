package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public List getPath() {
        if (path.size() == 0) {
            return null;
        }

        if (totalCost == 0) {
            for (int i = 0; i < path.size() - 1; i++) {
                totalCost += 1;
            }
        }

        if (totalCost > 0) {
            return path;
        } else {
            return null;
        }
    }

    public int getTotalCost() {
        if (path.size() == 0) {
            return 0;
        }

        if (totalCost == 0) {
            for (int i = 0; i < path.size() - 1; i++) {
                totalCost += 1;
            }
        }

        if (totalCost > 0) {
            return totalCost;
        } else {
            return 0;
        }
    }

    @Override
    public int hashCode() {
        if (path.size() == 0) {
            return 0;
        }

        int result = 0;
        for (Node node : path) {
            result += node.hashCode();
        }

        return result;
    }
}
