public class Link {
    private String targetName = null;
    private String linkName = null;
    private Directory currentDir = null;
    private Directory directory = null;

    public Link(String targetName, String linkName, Directory currentDir){
        this.targetName = targetName;
        this.linkName = linkName;
        this.currentDir = currentDir;

        for(Directory dir : currentDir.getChildren()){
            if(dir.getName().equals(targetName)){
                this.directory = dir;
            }
        }

        if(directory == null)
            System.out.println("Illegal command.");
    }

    public String getLinkName(){
        return linkName;
    }

    public String getTargetName(){
        return targetName;
    }

    public Directory getTargetDirectory(){
        return directory;
    }
}
