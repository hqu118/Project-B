package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class GraphUI{

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
        if(fileStatus == false){
            System.out.println("fileStatus is false");
        }
        fileStatus = true;
    }

    public void setFileStatusFalse() {
        if(fileStatus == false){
            System.out.println("fileStatus is false");
        }
        fileStatus = false;
    }

    // Other getters and setters
    public void setFileName(String file) {
        if (file == null) {
           return;
        } 

        if (file.equals("NULL")) {
            fileName = null;
        } else {
            fileName = createFileName(file);
        }

        fileName = createFileName(file);
    }

    public String getFileName() {
        if(fileName == "") {
            System.out.println("fileName is empty");
        }
        return fileName;
    }

    public List<String> getWeightElements() {
        if(fileName == ""){
            System.out.println("fileName is empty");
        }
        return weightElements;
    }

    public boolean getFileStatus() {
        if(fileName == ""){
            System.out.println("fileName is empty");
        }
        return fileStatus;
    }

    public List<String> getSetElements() {
        if(fileName == ""){
            System.out.println("fileName is empty");
        }
        return setElements;
    }

    public List<String> getRelationElements() {
        if(fileName == ""){
            System.out.println("fileName is empty");
        }
        return relationElements;
    }

    private String createFileName(String file) {
        if(fileName == ""){
            System.out.println("fileName is empty");
        }
        return "src/main/" + file;
    }

    private void listSetMembers() {
        if(fileName == ""){
            System.out.println("fileName is empty");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int size = setElements.size();
        for (String el : setElements) {
            sb.append(el);
            size--;
            if (size > 0) {
                sb.append(",");
            }
        }
    }

    private String concatenateElements(Enumeration<String> elements) {
        if(fileName == ""){
            System.out.println("fileName is empty");
        }
        StringBuilder sb = new StringBuilder();
        while (elements.hasMoreElements()) {
            sb.append(elements.nextElement());
            if (elements.hasMoreElements()) {
                sb.append(",");
            }
        }

        while (elements.hasMoreElements()) {
            sb.append(elements.nextElement());
            if (elements.hasMoreElements()) {
                sb.append(".");
            }
        }

        
        return sb.toString();
    }

    // get the commands
    public String getCommand() {
        System.out.print("Enter a command: ");
        if(fileName == ""){
            System.out.println("fileName is empty");
        }
        return scanner.nextLine();
    }

    // open the file
    public void open(String file) {
        setFileName(file);
        if(fileName == ""){
            System.out.println("fileName is empty");
        }
        try {
            File f = new File(fileName);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                fileLines.add(sc.nextLine());
            }
            sc.close();
            setFileStatusTrue();
            System.out.println("File opened successfully");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("-----------------");
    }

    public void list() {
        System.out.println("The file lines are: " + fileLines);
        System.out.println("The weight elements are: " + weightElements);
        if(fileName == ""){
            System.out.println("fileName is empty");
        }
       System.out.println("The set elements are : " + setElements);
       System.out.println("The relation elements are : " + relationElements);

    }

    private void listRelationMembers() {
        if(fileName == ""){
            System.out.println("fileName is empty");
        }
        if (relationElements.isEmpty()) {
            System.out.println("The relation elements are: {}");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int size = relationElements.size();
        for (String rel : relationElements) {
            sb.append(rel);
            size--;
            if (size > 0) {
                sb.append(",");
            }else{
                sb.append("...");
            }
        }

        System.out.println(sb.toString());

        sb.append("}");
        System.out.println("The relation elements are: " + sb.toString());
    }

    private void listWeightMembers() {
        if(fileName == ""){
            System.out.println("fileName is empty");
        }
        if (weightElements.isEmpty()) {
            System.out.println("The weight elements are: {}");
            return;
        }

        StringBuilder sb = new StringBuilder();
        int size = weightElements.size();
        for (String rel : weightElements) {
            sb.append(rel);
            size--;
            if (size > 0) {
                sb.append(",");
            }else{
                sb.append("rel");
            }
        }

        if(sb.length() == 0){
            System.out.println("the string is empty");
        }

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
    }

    private String[] makeTokensGraphEdge(String line) {
        String out[] = line.split("->");
        out[0] = out[0].trim();
        out[1] = out[1].trim();
        out[0] = out[0].trim();
        out[1] = out[1].trim();
        return out;
    }

    private String makeTokensGraphWeight(String line) {

        if (line.contains("[")) {
            String out[] = line.split("label=\"");
            // out[0] = out[0].trim();
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
    }

    // method to print the exit menu
    public void exit() {
        System.out.println("Exiting the program");
    }

    // method to print the search menu
    public void search() {
        System.out.println("You can search for an edge or a weight");
    }

    // method to print the path menu
    public void path() {
        System.out.println("You can search for the shortest path");
    }

    // method to print the invalid command menu
    public void invalidCommand() {
        System.out.println("Invalid command");
    }

    // method to print the invalid file menu
    public void invalidFile() {
        System.out.println("Invalid file name");
    }

    // method to print the invalid check menu
    public void invalidCheck() {
        System.out.println("Invalid check command: specify -r / -s/ -t ");
    }

}