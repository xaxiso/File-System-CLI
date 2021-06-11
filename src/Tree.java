import java.util.LinkedList;

public class Tree {
    private Directory root;

    public Tree(Directory root){
        this.root = root;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public Directory getRoot(){
        return root;
    }

    public void setRoot(Directory root){
        this.root = root;
    }

    public boolean exists(String name){
        return find(root, name);
    }

    public boolean find(Directory node, String name){
        boolean res = false;
        if(node.getName().equals(name)){
            return true;
        }
        else{
            for(Directory child: node.getChildren())
                if(find(child, name))
                    res = true;
        }

        return res;
    }

    public LinkedList<Directory> getPreOrderTraversal() {
        LinkedList<Directory> preOrder = new LinkedList<Directory>();
        buildPreOrder(root, preOrder);
        return preOrder;
    }

    private void buildPreOrder(Directory node, LinkedList<Directory> preOrder) {
        preOrder.add(node);
        for (Directory child : node.getChildren()) {
            buildPreOrder(child, preOrder);
        }
    }

}
