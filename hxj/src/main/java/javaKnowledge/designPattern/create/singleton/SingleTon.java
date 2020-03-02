package javaKnowledge.designPattern.create.singleton;

/**
 * Created by xiongjie on 2018/11/18.
 * 本质是：私有构造方法，静态同步获取对象方法
 */
public class SingleTon {

    private static String str=null;

    public static String getInstance(){
        if (str == null) {
            syncInit();
        }
        return str;
    }

    private static synchronized void syncInit() {
        if (str == null) {
            str = new String("abc");
        }
    }

    public static void main(String[] args){
        String res1= SingleTon.getInstance();
        String res2= SingleTon.getInstance();
        System.out.println("res1="+res1);
        System.out.println("res1="+res1);
        System.out.println("res1==res2?"+ (res1==res2) );
    }

}
