package javacore.designPattern.create.simpleFactory;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class FactoryMethod implements Provider {
    @Override
    public CommonInterface produce() {
        return new AmericanHello();
    }
}
