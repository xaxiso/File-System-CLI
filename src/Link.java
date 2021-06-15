public class Link {
    private String linkName;
    private String targetName;
    private Directory targetDirectory;

    public Link(String linkName, String targetName, Directory currentDir){
        this.linkName = linkName;
        this.targetName = targetName;


    }

    public String getLinkName(){
        return linkName;
    }

    public String getTargetName(){
        return targetName;
    }

    public Directory getTargetDirectory(Directory currentDir){
        if(currentDir.findDirectory(currentDir, targetName, 0) == null)
            return null;
        else
            return currentDir.findDirectory(currentDir, targetName, 0);
    }
}