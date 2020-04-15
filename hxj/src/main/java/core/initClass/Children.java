package core.initClass;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xiongjie on 2018/11/12.
 */
public class Children extends Parent {

    {
        System.out.println("执行Children->{}");
    }
    static{
        System.out.println("执行Children->static{}");
    }

    public Children() {
        System.out.println("执行Children()");
    }

    public static void main(String[] args){
        //1.类的初始化顺序
        Parent parent=new Children();
        parent=new Children();

        //2.数字类字符串的排序代码
        String str="2,4,6,1,9,34";
        String[] arr=str.split(",");
        List<String> list= Arrays.asList(arr);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                int num1=Integer.parseInt(s);
                int num2=Integer.parseInt(t1);

                if( num1>num2  ){
                    return 1;
                }else if( num1<num2 ){
                    return -1;
                }
                return 0;
            }
        });
        System.out.println(list.toString());
    }

}
