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
        String[] parts = command.split(" ");

        switch (parts[0]) {
            case "open":
                if (parts.length == 1) {
                    System.out.println("Invalid file name");
                    return true;
                } else if (parts.length == 2) {
                    sUI.open(parts[1]);
                    createGraph();
                } else if (!sUI.getFileStatus()) {
                    System.out.println("File can't be opened");
                    System.out.println("Enter a valid file name");
                    return true;
                }
                break;
            case "list":
                if (sUI.getFileStatus()) {
                    sUI.list();
                }
                break;
            case "check":
                if (!sUI.getFileStatus()) {
                    System.out.println("First open a valid file ");
                    break;
                } else if (parts.length == 1) {
                    System.out.println("Invalid check command: specify -r / -s/ -t ");
                    return true;
                } else if (parts.length == 2) {
                    switch (parts[1]) {
                        case "-r":
                            break;

                        default:
                            System.out.println("Invalid check command entered .. try again");
                            break;
                    }
                }
                break;
            case "search":
                if (sUI.getFileStatus()) {
                    switch (parts.length) {

                        case 2:
                            sUI.listEdgeGivenWeight(graph.searchEdgeByWeight(Integer.valueOf(parts[1])),
                                    Integer.valueOf(parts[1]));
                            break;
                        default:
                            System.out.println(
                                    "Incorrect arguments for search: argument can be either a weight or an edge with source and target");
                    }
                    createGraph();

                }
                break;
            case "exit":
                System.out.println("We will exit now.. bye!!");
                return false;
            default:
                System.out.println("Enter a valid command:");
                return true;
        }
        return true;
    }

    public static void main(String[] args) {
        GraphControl controller = new GraphControl();
        controller.execute();
    }

    // method to create a graph from the file
    public static Graph createGraphFromFile(String fileName) {
        GraphUI sUI = new GraphUI();
        sUI.open(fileName);
        return new Graph(sUI.getRelationElements(), sUI.getWeightElements());
    }

    // method to create a graph from the file
    public static Graph createGraphFromInput() {
        GraphUI sUI = new GraphUI();
        sUI.open("input.txt"); // this is just to initialize the file status to true
        return new Graph(sUI.getRelationElements(), sUI.getWeightElements());
    }

    // method to delete a graph
    public static void deleteGraph(Graph graph) {
        graph = null;
    }

    // method to list the shortest path
    public static void listShortestPath(Graph graph, Node source, Node target) {
        GraphUI sUI = new GraphUI();
        // sUI.listShortestPath(graph.computeShortestPath(source, target));
    }

    // method to list the edge given weight
    public static void listEdgeGivenWeight(Graph graph, int weight) {
        // TODO
        GraphUI sUI = new GraphUI();
        sUI.listEdgeGivenWeight(graph.searchEdgeByWeight(weight), weight);
    }
}