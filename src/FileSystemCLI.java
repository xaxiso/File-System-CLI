/*
mkdir a
cd a
mkdir b
cd b
mkdir c
cd ..
cd ..
rm b
cd c
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static utils.Inputs.in;

public class FileSystemCLI {
    private Directory root = new Directory("/");  // TODO
    private Directory currentDir = root;
    private String currentName = "/";

    public void start() {
        while (true) {
            printCurrentPath();
            String command = in.nextLine();
            if (command.equals("exit")) {
                break;
            }
            executeCommand(command);
        }
    }

    private void printCurrentPath() {
         System.out.println("Current path: " + currentName);
    }

    public void executeCommand(String command) {
        String[] segments = command.split("\\s");

        try {
            String commandName = segments[0];
            performCommand(commandName, segments);
        } catch (Exception err) {
            System.err.println("(Debug) Error: " + err.getMessage());
            System.out.println("Illegal command.");
        }
    }

    private void performCommand(String commandName, String[] segments) {
        switch (commandName) {
            case "cd":
                changeDirectory(segments[1]);
                break;
            case "mkdir":
                makeDirectory(segments[1]);
                break;
            case "touch":
                touch(segments[1], segments[2]);
                break;
            case "rm":
                remove(segments[1]);
                break;
            case "cat":
                concatenate(segments[1]);
                break;
            case "ls":
                list();
                break;
            case "search":
                search(segments[1]);
                break;
            case "ln":
                link(segments[1], segments[2]);
                break;
        }
    }

    private void changeDirectory(String directoryName) {
        if(directoryName.equals("..")){
            if(currentDir.getParent().getName().equals("/")){
                currentName = currentDir.getParent().getName();
                currentDir = currentDir.getParent();
            }
            else if(currentDir.getParent().getParent() != null) {
                currentName = currentName.replaceAll("/" + currentDir.getName() + "$", "");
                currentDir = currentDir.getParent();
            }
            else{
                currentName = currentName.replace(currentDir.getName(), "");
                currentDir = currentDir.getParent();
            }
        } else if(!currentDir.getChildren().isEmpty() && currentDir.findDirectory(currentDir, directoryName, 0) != null){
//                System.out.println("CD: " + currentDir.findDirectory(currentDir, directoryName, 0));
            currentDir = currentDir.findDirectory(currentDir, directoryName, 0);
            if(currentName != "/")
                currentName += "/";
            currentName += directoryName;
        }
        else {
            System.out.println("Illegal command.");
            return;
        }
//        System.out.println("currentDir parent: " + currentDir.getParent().getName());
    }

    private void makeDirectory(String directoryName) {
        if(currentDir.getFile(directoryName) != null) {
            System.out.println("Illegal command.");
            return;
        }

        for(Directory i : currentDir.getChildren()){
            if(i.getName().equals(directoryName)) {
                System.out.println("Illegal command.");
                return;
            }
        }

        Directory newNode = new Directory(directoryName);
        currentDir.addChild(newNode);
    }

    private void touch(String fileName, String content) {
        if(currentDir.getFile(fileName) != null) {
            System.out.println("Illegal command.");
            return;
        }

        for(Directory i : currentDir.getChildren()){
            if(i.getName().equals(fileName)) {
                System.out.println("Illegal command.");
                return;
            }
        }

        currentDir.addFile(fileName, content);
    }

    private void remove(String childName) {
        if(currentDir.findDirectory(currentDir, childName, 0) != null || currentDir.getFile(childName) != null) {
            if (currentDir.findDirectory(currentDir, childName, 0) != null) {
                Directory currentChild = currentDir.findDirectory(currentDir, childName, 0);
//            currentDir.removeSubTree(currentChild);

                currentDir.removeChild(currentChild);
            }

            if (currentDir.getFile(childName) != null)
                currentDir.removeFile(currentDir.getFile(childName));
        }
        else{
            System.out.println("Illegal command.");
        }

    }

    private void concatenate(String fileName) {
        if(currentDir.getFile(fileName).getContent() == null){
            System.out.println("Illegal command.");
            return;
        }
        else
            System.out.println(currentDir.getFile(fileName).getContent());
    }

    private void list() {
        List<String> tempList = new LinkedList<>();

        for(Directory child: currentDir.getChildren())
            tempList.add(child.getName());

        for(File file : currentDir.getAllFiles())
            tempList.add(file.getName());

        Collections.sort(tempList);

        for(String i : tempList)
            System.out.println(i);
    }

    private void link(String targetName, String linkName) {
        // TODO
    }

    private void search(String keyword) {
        if(currentDir.getName().indexOf(keyword) > -1)
            System.out.println(currentDir.getName());

        currentDir.findKeyDirectory(currentDir, keyword);
    }

}
