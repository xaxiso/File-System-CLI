import static utils.Inputs.in;

public class FileSystemCLI {
    private Directory root;  // TODO
    private Directory currentDir;

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
        // System.out.println("Current path: " + currentDir.getPath());
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
        // TODO
    }

    private void makeDirectory(String directoryName) {
        // TODO
    }

    private void touch(String fileName, String content) {
        // TODO
    }

    private void remove(String childName) {
        // TODO
    }

    private void concatenate(String fileName) {
        // TODO
    }

    private void list() {
        // TODO
    }

    private void link(String targetName, String linkName) {
        // TODO
    }

    private void search(String keyword) {
        // TODO
    }

}
