package javacore.designPattern.structure.composite;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class Node {

    private String name;
    private Node node;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
