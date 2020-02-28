package javaKnowledge.designPattern.create.simpleFactory;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class JapanHello implements CommonInterface {
    @Override
    public void sayHello() {
        System.out.println("普通简单工厂：日本你好");
    }
}
