import java.util.Collections;
import java.util.LinkedList;

public class Directory {
    String name;
    private LinkedList<Directory> children;
    private Directory parent;
    private LinkedList<File> files;

    public Directory(String name){
        this.name = name;
        this.children = new LinkedList<>();
        this.files = new LinkedList<>();

        if(name.equals("/"))
            this.parent = this;
    }

    public void findKeyDirectory(Directory currentDir, String key){
        LinkedList<String> occurences = new LinkedList<>();

        treeDirSearch(currentDir, key, occurences);
    }

    public void treeDirSearch(Directory currentDir, String key, LinkedList<String> occurences) {
        if (currentDir.getChildren() == null && currentDir.getAllFiles() == null)
            return;

        LinkedList<String> allList = new LinkedList<>();

        for (Directory child : currentDir.getChildren())
            allList.add(child.getName());

        for(File file : currentDir.getAllFiles())
            allList.add(file.getName());

        Collections.sort(allList);

        for (String name : allList) {
            if(name.indexOf(key) > -1)
                System.out.println(name);
            for (Directory dir : currentDir.getChildren())
                if (dir.getName().equals(name))
                    treeDirSearch(dir, key, occurences);
        }
    }

    public void removeSubTree(Directory currentDir){
        if(currentDir == null)
            return;

        for(Directory child : currentDir.getChildren())
            removeSubTree(child);

        System.out.println("Delete: " + currentDir.getName());
        currentDir = null;
    }

    public void addFile(String fileName, String content){
        files.add(new File(fileName, content));
    }

    public File getFile(String fileName){
        for(File i : files){
            if(i.getName().equals(fileName))
                return i;
        }
        return null;
    }

    public LinkedList<File> getAllFiles(){
        return files;
    }

    public boolean removeFile(File fileToBeDeleted){
        LinkedList<File> list = getAllFiles();
        return list.remove(fileToBeDeleted);
    }

    public void addChild(Directory child){
        child.setParent(this);
        children.add(child);
    }

    public void setChildren(LinkedList<Directory> children) {
        for (Directory child : children)
            child.setParent(this);

        this.children = children;
    }

    public void removeChildren() {
        this.children.clear();
    }

    public Directory removeChildAt(int index) {
        return children.remove(index);
    }

    public boolean removeChild(Directory childToBeDeleted) {
        LinkedList<Directory> list = getChildren();
        return list.remove(childToBeDeleted);
    }

    public String getName() {
        return this.name;
    }

    public Directory getParent() {
        return this.parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public LinkedList<Directory> getChildren() {
        return this.children;
    }

    public Directory getChildAt(int index) {
        return children.get(index);
    }

    public Directory findDirectory(Directory currentDir, String name, int flag){
        if(currentDir == null)
            return null;

        if(currentDir.getName().equals(name) && flag != 0)
            return currentDir;
        else{
            flag++;
            Directory cur = null;
            for(Directory child: currentDir.getChildren())
                if((cur = findDirectory(child, name, flag)) != null)
                    return cur;
        }

        return null;
    }
}