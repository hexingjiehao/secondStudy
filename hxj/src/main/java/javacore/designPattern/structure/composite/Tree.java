package javacore.designPattern.structure.composite;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class Tree {

    private Node node;

    public Tree(String name) {
        this.node = new Node(name);
    }

    public void add(Node node){
        this.node.setNode(node);
    }

    public String show(){
        String res="";
        while(node!=null) {
            res+=node.getName()+"|";
            node=node.getNode();
        }
        return res;
    }

    public static void main(String[] args){

        Tree tree=new Tree("xiongjie");
        tree.add(new Node("hexingjie"));
        tree.add(new Node("helloworld"));
        System.out.println(tree.show());
    }

}
