package algorithm.sortFind;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by xiongjie on 2018/11/21.
 */
public class BinaryTree {

    public static void main(String[] args) {
        Node root=initTree();

        PreOrderecursion(root);
        System.out.println("\n****上述是递归先序遍历****");
        InOrderecursion(root);
        System.out.println("\n****上述是递归中序遍历****");
        PostOrderecursion(root);
        System.out.println("\n****上述是递归后序遍历****");

        Node tmp=root;
        PreOrderWhile(tmp);
        System.out.println("\n****上述是while先序遍历****");
        tmp=root;
        InOrderWhile(tmp);
        System.out.println("\n****上述是while中序遍历****");
        tmp=root;
        PostOrderWhileOne(tmp);
        System.out.println("\n****上述是while后序遍历1****");
        tmp=root;
        PostOrderWhileTwo(tmp);
        System.out.println("\n****上述是while后序遍历2****");
        tmp=root;
        levelTraverse(tmp);
        System.out.println("\n****上述是层次遍历****");

        tmp=root;
        depthOrderTraverse(tmp);
        System.out.println("\n****上述是深度优先遍历****");

    }

    public static Node initTree() {
        Node Anode=new Node("A");
        Node Bnode=new Node("B");
        Node Cnode=new Node("C");
        Node Dnode=new Node("D");
        Node Enode=new Node("E");
        Node Fnode=new Node("F");
        Node Gnode=new Node("G");

        Anode.setLeft(Bnode);
        Anode.setRight(Cnode);
        Bnode.setLeft(Dnode);
        Bnode.setRight(Enode);
        Cnode.setLeft(Fnode);
        Cnode.setRight(Gnode);

        return Anode;
    }

    /**
     * 递归先序遍历
     * @param node
     */
    public static void PreOrderecursion(Node node){
        if (node == null) {
            return ;

        }
        System.out.print(node.getName());
        PreOrderecursion(node.getLeft());
        PreOrderecursion(node.getRight());
    }

    /**
     * 递归中序遍历
     * @param node
     */
    public static void InOrderecursion(Node node){
        if (node == null) {
            return ;
        }
        InOrderecursion(node.getLeft());
        System.out.print(node.getName());
        InOrderecursion(node.getRight());
    }

    /**
     * 递归后序遍历
     * @param node
     */
    public static void PostOrderecursion(Node node){
        if (node == null) {
            return ;
        }
        PostOrderecursion(node.getLeft());
        PostOrderecursion(node.getRight());
        System.out.print(node.getName());
    }


    /**
     * while先序遍历--stack
     * @param node
     */
    public static void PreOrderWhile(Node node){
        Stack stack=new Stack();
        while( node!=null || !stack.isEmpty()){

            if(node !=null){
                System.out.print(node.getName());
                stack.push(node);
                node=node.getLeft();
            }else{
                node= (Node) stack.pop();
                node=node.getRight();
            }
        }
    }


    /**
     * while中序遍历--stack
     * @param node
     */
    public static void InOrderWhile(Node node){

        Stack stack=new Stack();
        while( node!=null || !stack.isEmpty()){

            if(node !=null){
                stack.push(node);
                node=node.getLeft();
            }else{
                node= (Node) stack.pop();
                System.out.print(node.getName());
                node=node.getRight();
            }
        }

    }

    /**
     * while后序遍历--stack
     *  第一种思路：对于任一结点P，将其入栈，然后沿其左子树一直往下搜索。
     *  直到搜索到没有左孩子的结点，此时该结点出如今栈顶，可是此时不能将其出栈并訪问，因此其右孩子还为被訪问。
     * @param node
     */
    public static void PostOrderWhileOne(Node node){

        Stack stack=new Stack();
        while( node!=null || !stack.isEmpty()) {

            while (node != null) {
                node.setFirst(true);
                stack.push(node);
                node = node.getLeft();
            }
            if (!stack.empty()) {
                Node temp = (Node) stack.pop();
                if (temp.isFirst() == true) {
                    temp.setFirst(false);
                    stack.push(temp);
                    node = temp.getRight();
                } else{
                    System.out.print(temp.getName());
                    node=null;
                }
            }
        }
    }

    /**
     * while后序遍历--stack
     * 先将其入栈。假设P不存在左孩子和右孩子。则能够直接訪问它；或者P存在左孩子或者右孩子。
     * 可是其左孩子和右孩子都已被訪问过了。则相同能够直接訪问该结点。
     * 若非上述两种情况。则将P的右孩子和左孩子依次入栈
     * @param node
     */
    public static void PostOrderWhileTwo(Node node){
        Stack stack=new Stack();
        stack.push(node);
        Node tmp=null;

        while(!stack.empty()) {

            Node cur= (Node) stack.peek();
            if( (cur.getLeft()==null && cur.getRight()==null) ||
                (
                        tmp!=null && ( tmp==cur.getLeft() || tmp==cur.getRight())
                )
                    ) {
                System.out.print(cur.getName());
                stack.pop();
                tmp=cur;
            } else {
                if(cur.getRight()!=null) {
                    stack.push(cur.getRight());
                }
                if(cur.getLeft()!=null) {
                    stack.push(cur.getLeft());
                }
            }

        }
    }

    /**
     * 层次遍历
     * @param node
     */
    public static void levelTraverse(Node node) {
        if (node == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.getName());
            if (cur.getLeft() != null) {
                queue.offer(cur.getLeft());
            }
            if (cur.getRight() != null) {
                queue.offer(cur.getRight());
            }
        }
    }

    /**
     * 深度优先遍历
     * @param node
     */
    public static void depthOrderTraverse(Node node) {
        if (node == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.print(cur.getName());
            if (cur.getRight() != null) {
                stack.push(cur.getRight());
            }
            if (cur.getLeft() != null) {
                stack.push(cur.getLeft());
            }
        }
    }

}
