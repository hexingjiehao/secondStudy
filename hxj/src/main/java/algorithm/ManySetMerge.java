package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiongjie on 2018/11/13.
 * 实现多个有序集合[left,right]，插入一个新集合后，进行集合合并
 */
public class ManySetMerge {

    public static void main(String[] args){
        List<Border> list=new ArrayList<>();
        list.add(new Border(1,4));
        list.add(new Border(6,9));
        list.add(new Border(13,15));

        Border border=new Border(-3,-1);
        manySetInsert(list,border);
    }

    //类似于插入排序
    public static void manySetInsert(List<Border> list,Border border){
        List<Border> result=new ArrayList<>();
        if(list==null || list.size()==0){
            result.add(border);
        }else{

            Border last=null;
            boolean flag=false;
            for(Border b:list){

                int bleft=b.left; int bright=b.right;
                int vleft=border.left; int vright=border.right;

                //判断两个箱子是否能合并，关键是有序的
                Border cur=null;
                if(flag==true){
                    result.add(b);cur=b;
                } else if( vleft>bright && flag==false ){
                    result.add(b);cur=b;
                }else if( vright>=bleft && flag==false) {
                    int max=bright>vright? bright:vright;
                    int min=bleft < vleft ?bleft:vleft;
                    cur=new Border(min,max);
                    result.add(cur);
                    flag=true;
                }else if(flag==false){
                    result.add(border);result.add(b); cur=b;flag=true;
                }

                if(last!=null){
                    //和上一个进行合并
                    int lastMax=last.right;
                    int curMin=cur.left;
                    if(curMin<=lastMax){
                        int lastMin=last.left;
                        int curMax=cur.right;
                        result.remove(cur);
                        result.remove(last);

                        last=new Border(lastMin,curMax);
                        result.add(last);
                    }
                }

                if(last==null){
                    last=cur;
                }

            }

            if(flag==false){
                result.add(border);
            }

        }

        System.out.println(result.toString());
    }


    //内部类封装对象
    static class Border{
        int left;
        int right;

        public Border(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Border{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

}
