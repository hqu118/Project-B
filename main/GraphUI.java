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
        return false;
    }

    public void setFileStatusTrue() {
        fileStatus = true;
    }

    public void setFileStatusFalse() {
        fileStatus = false;
    }

    // Other getters and setters
    public void setFileName(String file) {
        if (file == null) {
           return;
        } 

        if (file.equals("NULL")) {
            fileName = file;
        } else {
            fileName = createFileName(file);
        }
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
        return "src/main/" + file;
    }

    private void listSetMembers() {
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
        sb.append("}");
        System.out.println("The set elements are: " + sb.toString());
    }

    private String concatenateElements(Enumeration<String> elements) {
        StringBuilder sb = new StringBuilder();
        while (elements.hasMoreElements()) {
            sb.append(elements.nextElement());
            if (elements.hasMoreElements()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    // get the commands
    public String getCommand() {
        System.out.print("Enter a command: ");
        return scanner.nextLine();
    }

}