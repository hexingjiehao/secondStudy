package javaKnowledge.designPattern.structure.decorator;

import javaKnowledge.designPattern.create.simpleFactory.CommonInterface;

/**
 * Created by xiongjie on 2018/11/18.
 *
 * AOP编程就是使用装饰者模式
 *
 * 本质是：自己实现接口，同时将接口作为自己的成员
 */
public class Decorator implements CommonInterface {

    private CommonInterface source;

    public Decorator(CommonInterface source) {
        this.source = source;
    }

    @Override
    public void sayHello() {
        System.out.println("装饰者模式：开始装饰");
        source.sayHello();
        System.out.println("装饰者模式：装饰结束");
    }

    public static void main(String[] args){
        CommonInterface source=new Source();
        CommonInterface res=new Decorator(source);
        res.sayHello();
    }

}
