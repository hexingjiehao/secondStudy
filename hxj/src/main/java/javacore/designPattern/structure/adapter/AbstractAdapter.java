package javacore.designPattern.structure.adapter;

/**
 * Created by xiongjie on 2018/11/18.
 */
public abstract class AbstractAdapter implements Adapter {

    @Override
    public void sayHello() {
        System.out.println("接口-适配器模式：抽象方法Hello");
    }

    @Override
    public void sayWorld() {
        System.out.println("接口-适配器模式：抽象方法World");
    }
}
