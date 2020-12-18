package algorithm;

import java.util.ArrayList;

/**
 * Created by xiongjie on 2020/4/7.
 */

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}

public class FindPathSum {

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.left.left=new TreeNode(4);
        root.left.left.left=new TreeNode(3);
        root.left.left.right=new TreeNode(5);
        root.left.right=new TreeNode(5);
        root.left.right.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.right.left=new TreeNode(6);
        root.right.right=new TreeNode(7);

        ArrayList<ArrayList<Integer>> list=FindPath(root,10);
        int len=list.size();
        for (int i=0;i<len;i++){
            ArrayList<Integer> tmp=list.get(i);
            int len2=tmp.size();
            for (int j=0;j<len2;j++){
                System.out.print(tmp.get(j)+" ");
            }
            System.out.println("第"+i+"个数组打印完毕");
        }
    }

    /**
     * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
     * @param root
     * @param target
     * @return
     */
    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        dfs(root,0,target,new ArrayList<Integer>(),list);
        list.sort((a,b)->{return a.size()>b.size()?-1:1;});
        return list;
    }

    //递归
    public static void dfs(TreeNode root, int curSum, int target,ArrayList<Integer> path,ArrayList<ArrayList<Integer>> list) {
        if(root==null){
            return;
        }
        path.add(root.val);

        if(root.left!=null){
            dfs(root.left,curSum+root.val,target,path,list);
        }
        if(root.right!=null){
            dfs(root.right,curSum+root.val,target,path,list);
        }
        if(root.left==null && root.right==null){
            curSum+=root.val;
            if(curSum==target){
                ArrayList<Integer> res= (ArrayList<Integer>) path.clone();
                list.add(res);
            }
        }
        path.remove(path.size()-1);
    }

}
