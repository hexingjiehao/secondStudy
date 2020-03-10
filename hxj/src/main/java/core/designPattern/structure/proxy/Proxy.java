package core.designPattern.structure.proxy;

import core.designPattern.create.simpleFactory.CommonInterface;
import core.designPattern.structure.decorator.Source;

/**
 * Created by xiongjie on 2018/11/18.
 * 代理模式的本质：将继承接口，并将实现类作为自己的成员
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
