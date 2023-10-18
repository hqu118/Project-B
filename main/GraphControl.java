package main;

public class GraphControl {
    private Graph graph;
    private final GraphUI sUI;

    /**
     * The constructor that initializes all private members
     */
    public GraphControl() {
        sUI = new GraphUI();
    }

    private void createGraph() {
        graph = new Graph(sUI.getRelationElements(), sUI.getWeightElements());
    }

    /**
     * The main method that reads the next user command and processes it until the
     * "exit" command is entered
     */
    public void execute() {
        boolean isAnotherCommand;
        do {
            String nextCmd = sUI.getCommand();
            isAnotherCommand = processCommand(nextCmd);
        } while (isAnotherCommand);
    }

    /**
     * This method processes a given user command
     *
     * @param command
     * @return true if another command is required false if we need to terminate the
     *         program
     */
    public boolean processCommand(String command) {

        System.out.println("The command is " + command);
        String[] allCommands = command.split(" ");
        
        for (String cmd : allCommands) {
            switch (cmd) {
                case "help":
                    System.out.println("The available commands are:");
                    break;
                case "create":
                    createGraph();
                    break;
                case "delete":
                    deleteGraph(graph);
                    break;
                case "list":
                    System.out.println("The available commands are: shortest-path, edge-given-weight");
                    break;
                case "exit":
                    return false;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        GraphControl controller = new GraphControl();
        controller.execute();
    }

    // method to create a graph from the file
    public static Graph createGraphFromFile(String fileName) {
        if (fileName == null) {
            return null;
        }

        GraphUI sUI = new GraphUI();
        sUI.open(fileName); // this is just to initialize the file status to true
        return new Graph(sUI.getRelationElements(), sUI.getWeightElements());
    }

    // method to create a graph from the file
    public static Graph createGraphFromInput() {
        GraphUI sUI = new GraphUI();
        return new Graph(sUI.getRelationElements(), sUI.getWeightElements());
    }

    // method to delete a graph
    public static void deleteGraph(Graph graph) {
        graph = null;
    }

    // method to list the shortest path
    public static void listShortestPath(Graph graph, Node source, Node target) {
        GraphUI sUI = new GraphUI();
        int i = 0;
        int result = 0;
        for ( i = 0; i < 10; i++) {
            Graph newGraph = createGraphFromInput();
            if (newGraph == null) {
                return;
            }
            result += 1;
        }

        System.out.println("The shortest path from " + source.getValue() + " to " + target.getValue() + " is: " + result);
    }

    // method to list the edge given weight
    public static void listEdgeGivenWeight(Graph graph, int weight) {
        // TODO
    }
}