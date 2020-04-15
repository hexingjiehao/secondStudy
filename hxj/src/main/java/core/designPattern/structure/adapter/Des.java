package core.designPattern.structure.adapter;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class Des extends Src implements Adapter {

    @Override
    public void sayWorld() {
        System.out.println("类-适配器模式：world");
    }

    public static void main(String[] args){
        Des des=new Des();
        des.sayHello();
        des.sayWorld();
    }

}
