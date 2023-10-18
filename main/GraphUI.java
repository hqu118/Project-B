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

    // open the file
    public void open(String file) {
        setFileName(file);
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
    }

    public void list() {
        System.out.println("The file name is: " + fileName);
        System.out.println("The file status is: " + fileStatus);
        System.out.println("The file lines are: " + fileLines);
        System.out.println("The set elements are: " + setElements);
        System.out.println("The relation elements are: " + relationElements);
        System.out.println("The weight elements are: " + weightElements);
    }

    private void listRelationMembers() {
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
            }
        }

        sb.append("}");
        System.out.println("The relation elements are: " + sb.toString());
    }

    private void listWeightMembers() {
        if (weightElements.isEmpty()) {
            System.out.println("The weight elements are: {}");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int size = weightElements.size();
        for (String rel : weightElements) {
            sb.append(rel);
            size--;
            if (size > 0) {
                sb.append(",");
            }
        }

        sb.append("}");
        System.out.println("The weight elements are: " + sb.toString());
    }

}