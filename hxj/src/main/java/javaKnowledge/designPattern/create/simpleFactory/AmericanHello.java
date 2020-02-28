package javaKnowledge.designPattern.create.simpleFactory;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class AmericanHello implements CommonInterface {
    @Override
    public void sayHello() {
        System.out.println("工厂方法模式：美国你好");
    }
}
