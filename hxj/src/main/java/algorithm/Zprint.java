package algorithm;

import java.util.ArrayDeque;
import java.util.Queue;

public class Zprint {

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.left.left=new TreeNode(4);
        root.left.right=new TreeNode(5);
        root.right=new TreeNode(3);

        Print(root);
    }

    /**
     * Z字形打印二叉树
     * @param root
     */
    public static void Print(TreeNode root) {
        if(root==null){
            return ;
        }
        Queue queue=new ArrayDeque();
        dfs(root,1,queue);
        while (!queue.isEmpty()){
            System.out.print(queue.poll()+" ");
        }
    }

    public static void dfs(TreeNode root,int h,Queue queue) {
        if(root==null){
            return ;
        }
        queue.add(root.val);
        if(h%2==0){
            dfs(root.left,h+1,queue);
            dfs(root.right,h+1,queue);
        }else{
            dfs(root.right,h+1,queue);
            dfs(root.left,h+1,queue);
        }
    }
}
