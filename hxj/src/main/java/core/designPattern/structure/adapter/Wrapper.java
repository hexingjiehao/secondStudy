package core.designPattern.structure.adapter;

/**
 * Created by xiongjie on 2018/11/18.
 * 对象的适配器模式
 * 本质是：将对象作为参数传入，实现相同的接口
 */
public class Wrapper  implements Adapter {

    //持有对象实例
    private Src src;


    public Wrapper(Src src) {
        this.src = src;
    }

    @Override
    public void sayHello() {
        System.out.println("对象-适配器模式：hello");
    }

    @Override
    public void sayWorld() {
        System.out.println("对象-适配器模式：world");
    }

    public static void main(String[] args){
        Src src=new Src();
        Wrapper wrapper=new Wrapper(src);
        wrapper.sayHello();
        wrapper.sayWorld();
    }

}
