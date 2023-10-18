package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class GraphUI {

    private String fileName;
    // This is set to true if a valid file has been opened
    private boolean fileStatus;
    // These store the lines, the elements of the set and the elements of the
    // relation
    // when the file is successfully opened
    private final List<String> fileLines;
    private final List<String> setElements;
    private final List<String> relationElements;
    private final List<String> weightElements;

    public static Scanner scanner = new Scanner(System.in);

    public GraphUI() {
        topicScreen();
        fileName = "NULL";
        fileLines = new ArrayList<>();
        fileStatus = false;
        setElements = new ArrayList<>();
        relationElements = new ArrayList<>();
        weightElements = new ArrayList<>();
    }

    private void topicScreen() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("The Graph Calculator. To know available commands, please type 'help'");
        System.out.println("----------------------------------------------------------------------");
    }

    private boolean processFile() {
        fileLines.clear();
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                setFileStatusFalse();
                return false;
            } else {
                setFileStatusTrue();
            }
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                fileLines.add(s.nextLine());
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File reading error");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void setFileStatusTrue() {
        fileStatus = true;
    }

    public void setFileStatusFalse() {
        fileStatus = false;
    }

    // Other getters and setters
    public void setFileName(String file) {
        fileName = createFileName(file);
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getWeightElements() {
        return weightElements;
    }

    public boolean getFileStatus() {
        return fileStatus;
    }

    public List<String> getSetElements() {
        return setElements;
    }

    public List<String> getRelationElements() {
        return relationElements;
    }

    private String createFileName(String file) {
        String line = System.getProperty("user.dir");
        System.out.println("The current directory is " + line);
        line = line + File.separator + "testcases" + File.separator + file;
        System.out.println("The full path name is: " + line);
        return line;
    }

    private void listSetMembers() {
        System.out.println("The set elements are: " + concatenateElements(Collections.enumeration(setElements)));
    }

    private String concatenateElements(Enumeration<String> elements) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        while (elements.hasMoreElements()) {
            String nextEl = (String) elements.nextElement();
            sb.append(nextEl);
            if (elements.hasMoreElements()) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    // get the commands
    public String getCommand() {
        System.out.print(">>");
        return scanner.nextLine();
    }

    // open the file
    public void open(String file) {
        setFileName(file);

        if (!processFile()) {
            System.out.println("File doesn't exist");
            System.out.println("Enter a valid file name");
        } else {
            // refresh the vectors every time a new file is opened
            setElements.clear();
            relationElements.clear();
            weightElements.clear();
            createSetElements();
            makeTokensGraph();
        }
    }

    public void list() {
        listSetMembers();
        listRelationMembers();
        listWeightMembers();
    }

    private void listRelationMembers() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String rel : relationElements) {
            sb.append("(");
            sb.append(rel);
            sb.append(")");
        }
        sb.append("}");
        System.out.println("The relational elements are: " + sb.toString());

    }

    private void listWeightMembers() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int size = weightElements.size();
        for (String wt : weightElements) {
            sb.append(wt);
            size--;
            if (size > 0) {
                sb.append(",");
            }
        }
        sb.append("}");
        System.out.println("The weight elements are: " + sb.toString());

    }

    protected void listShortestPath(Path path) {
        if (path == null) {
            throw new RuntimeException(" target is not reachable from source");
        }

        int size = path.getPath().size();
        // path has to be at least size 2 or more
        if (size == 0) {
            throw new RuntimeException("Shortest path is Empty or has no cost");
        } else if (size == 1) {
            System.out.println(path.toString());
            System.out.println("Enter another *path source target* ...");
            // System.exit(0);
        } else {
            System.out.println("The shortest path is: " + path.toString());
        }
    }

    protected void listEdgeGivenWeight(Edge edge, int weight) {
        if (edge != null) {
            System.out.println("The edge searched having weight " + weight + " is: " + edge.getSource().getValue()
                    + "-->" + edge.getTarget().getValue());
        } else {
            System.out.println("There is no such edge with weight: " + weight);
        }
    }

    protected void listWeightGivenEdge(Node source, Node target, int weight) {
        if (weight != -1) {
            System.out.println("Given the edge from source " + source.getValue() + " target " + target.getValue()
                    + " has weight: " + weight);
        } else {
            System.out.println(
                    "There is no such edge with source : " + source.toString() + " target: " + target.toString());

        }

    }

    private void createSetElements() {
        String parts[] = fileLines.get(0).split("//");
        String tokens[] = parts[1].split(",");
        int i = 0;
        while (i < tokens.length) {
            tokens[i] = tokens[i].trim();
            setElements.add(tokens[i]);
            ++i;
        }
    }

    private String[] makeTokensGraphEdge(String line) {
        String out[] = line.split("->");
        out[0] = out[0].trim();
        out[1] = out[1].trim();

        int i;
        if (out[1].contains(";") && !(out[1].contains("["))) {
            i = out[1].indexOf(";");
            out[1] = out[1].substring(0, i);
        } else if (out[1].contains("[")) {
            i = out[1].indexOf("[");
            out[1] = out[1].substring(0, i);
        }
        out[0] = out[0].trim();
        out[1] = out[1].trim();
        return out;
    }

    private String makeTokensGraphWeight(String line) {

        if (line.contains("[")) {
            String out[] = line.split("label=\"");
            // out[0] = out[0].trim();
            out[1] = out[1].trim();

            int i;
            i = out[1].indexOf("\"");
            out[1] = out[1].substring(0, i);
            out[1] = out[1].trim();
            return out[1];
        } else {
            return "";
        }
    }

    private void makeTokensGraph() {

        for (String line : fileLines) {
            line = line.trim();
            if (!line.equals("digraph testgraph{") && !line.equals("}") && line.charAt(0) != '/') {
                String adjNodes[] = makeTokensGraphEdge(line);

                String pair = adjNodes[0].trim() + "," + adjNodes[1].trim();
                // This will remove all white spaces between a , b tutple
                pair = pair.replaceAll("\\s+", "");
                relationElements.add(pair);
                String wt = makeTokensGraphWeight(line);
                if (!wt.equals("")) {
                    weightElements.add(wt);
                }
            }
        }
    }

    // method to print the help menu
    public void help() {
        System.out.println("You can either *open* a file or *list* an opened file or *exit* the program");
        System.out.println("Once a valid file is open you can *search* in the graph for a given edge or weight");
    }

    // method to print the exit menu
    public void exit() {
        System.out.println("Exiting the program");
        System.exit(0);
    }

    // method to print the search menu
    public void search() {
        System.out.println("You can search for an edge or a weight");
        System.out.println("Enter *edge source target* or *weight source target*");
    }

    // method to print the path menu
    public void path() {
        System.out.println("You can search for the shortest path");
        System.out.println("Enter *path source target*");
    }

    // method to print the invalid command menu
    public void invalidCommand() {
        System.out.println("Invalid command");
        System.out.println("Enter a valid command");
    }

    // method to print the invalid file menu
    public void invalidFile() {
        System.out.println("Invalid file name");
        System.out.println("Enter a valid file name");
    }

    // method to print the invalid check menu
    public void invalidCheck() {
        System.out.println("Invalid check command: specify -r / -s/ -t ");
    }
}
