public class File {
    String fileName;
    String content;

    public File(String fileName, String content){
        this.fileName = fileName;
        this.content = content;
    }

    public String getName(){
        return fileName;
    }

    public String getContent(){
        return content;
    }
}
