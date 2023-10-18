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

        if (command == null) {
            System.out.println("Enter a valid command:");
            return true;
        }

        if (command.equals("")) {
            System.out.println("Enter a valid command:");
            return true;
        }

        if (command.equals("exit")) {
            System.out.println("We will exit now.. bye!!");
            return false;
        }

        if (command.equals("restart")) {
            System.out.println("Restarting the program..");
            return false;
        }

        System.out.println("The command is " + command);
        String[] parts = command.split(" ");

        if (parts.length == 0) {
            System.out.println("Enter a valid command:");
            return true;
        }

        if (parts.length > 3) {
            System.out.println("Enter a valid command:");
            return true;
        }

        if (parts.length == 3) {
            if (!parts[0].equals("path")) {
                System.out.println("Enter a valid command:");
                return true;
            }
        }

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

            case "help":
                System.out.println("You can either *open* a file or *list* an opened file or *exit* the program");
                System.out
                        .println("Once a valid file is open you can *search* in the graph for a given edge or weight");
                System.out.println("You can also determine the shortest path using the *path* command");
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
            case "open-check":
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
            case "help-check":
                System.out.println("You can either *open* a file or *list* an opened file or *exit* the program");
                System.out
                        .println("Once a valid file is open you can *search* in the graph for a given edge or weight");
                System.out.println("You can also determine the shortest path using the *path* command");
                break;
            case "list-check":
                if (sUI.getFileStatus()) {
                    sUI.list();
                }
                break;
            case "restart-check":
                System.out.println("Restarting the program..");
                return false;
            case "exit-check":
                System.out.println("We will exit now.. bye!!");
                return false;
            case "path":
                if (sUI.getFileStatus()) {
                    if (parts.length != 3) {
                        System.out.println("Incorrect arguments for path (i.e. path x y)");
                        break;
                    }
                    sUI.listShortestPath(graph.computeShortestPath(new Node(parts[1]), new Node(parts[2])));
                    createGraph();
                }
                break;

            case "search":
                if (sUI.getFileStatus()) {
                    switch (parts.length) {

                        case 2:
                            sUI.listEdgeGivenWeight(graph.searchEdgeByWeight(Integer.valueOf(parts[1])),
                                    Integer.valueOf(parts[1]));
                            break;
                        case 3:
                            sUI.listWeightGivenEdge(new Node(parts[1]), new Node(parts[2]),
                                    graph.searchWeightByEdge(new Node(parts[1]), new Node(parts[2])));
                            break;
                        default:
                            System.out.println(
                                    "Incorrect arguments for search: argument can be either a weight or an edge with source and target");
                    }
                    createGraph();

                }
                break;
            case "search-for-edge":
                if (sUI.getFileStatus()) {
                    switch (parts.length) {

                        case 2:
                            sUI.listEdgeGivenWeight(graph.searchEdgeByWeight(Integer.valueOf(parts[1])),
                                    Integer.valueOf(parts[1]));
                            break;
                        case 3:
                            sUI.listWeightGivenEdge(new Node(parts[1]), new Node(parts[2]),
                                    graph.searchWeightByEdge(new Node(parts[1]), new Node(parts[2])));
                            break;
                        default:
                            System.out.println(
                                    "Incorrect arguments for search: argument can be either a weight or an edge with source and target");
                    }
                    createGraph();
                }
                break;
            case "restart":
                System.out.println("Restarting the program..");
                return false;
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
        sUI.listShortestPath(graph.computeShortestPath(source, target));
    }

    // method to list the edge given weight
    public static void listEdgeGivenWeight(Graph graph, int weight) {
        GraphUI sUI = new GraphUI();
        sUI.listEdgeGivenWeight(graph.searchEdgeByWeight(weight), weight);
    }
}