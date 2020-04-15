package core.initClass;

/**
 * Created by xiongjie on 2018/11/12.
 */
public class Parent {

    {
        System.out.println("执行Parent->{}");
    }
    static{
        System.out.println("执行Parent->static{}");
    }

    public Parent() {
        System.out.println("执行Parent()");
    }


    public static void main(String[] args) {

    }

}
