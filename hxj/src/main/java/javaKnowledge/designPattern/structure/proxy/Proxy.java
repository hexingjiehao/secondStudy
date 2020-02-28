package javaKnowledge.designPattern.structure.proxy;

import javaKnowledge.designPattern.create.simpleFactory.CommonInterface;
import javaKnowledge.designPattern.structure.decorator.Source;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class Proxy implements CommonInterface {

    private Source source;

    public Proxy() {
        this.source = new Source();
    }

    @Override
    public void sayHello() {
        System.out.println("代理者模式：开始");
        source.sayHello();
        System.out.println("代理者模式：结束");
    }

    public static void main(String[] args){
        CommonInterface res=new Proxy();
        res.sayHello();
    }

}
